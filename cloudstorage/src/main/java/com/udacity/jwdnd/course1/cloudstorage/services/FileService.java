package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FIleMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private FIleMapper fileMapper;

    Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    public FileService(FIleMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    public FIleMapper getFileMapper() {
        return fileMapper;
    }

    public void setFileMapper(FIleMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    //    Adding and inserting new file
    public void insert(MultipartFile multipartFile, Integer userId) throws IOException {

        if(multipartFile.getOriginalFilename().isEmpty()){
            FileModel file = new FileModel(null,
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    String.valueOf(multipartFile.getSize()),
                    userId,
                    multipartFile.getBytes());
            fileMapper.addFile(file);
        }

    }


    //    Get file by user id
    public List<FileModel> getuserfiles(int userId){
        return fileMapper.getUserId(userId);
    }
    //    Getting file by file id
    public FileModel getFileId(Integer fileId){
        return fileMapper.getFile(fileId);
    }

    //    Getting all file names by method
    public List<FileModel> getAllFiles(Integer userId){
        return fileMapper.getUserId(userId);
    }

    //    Deleting files by fileid
    public int deleteFile(Integer fileId){
        return fileMapper.deleteFile(fileId);
    }

    //    Updating file by fileid and filename because one id can contain many files so filename is required
    public int updateFile(FileModel fileModel){
        return fileMapper.updateFile(fileModel);
    }
}
