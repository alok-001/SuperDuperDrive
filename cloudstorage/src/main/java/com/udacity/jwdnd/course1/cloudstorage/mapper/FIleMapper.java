package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface FIleMapper {

    @Insert("INSERT INTO FILES(userId, filename, filesize, filedata, contenttype) VALUES(#{userId}, #{filename}, #{filesize}, #{filedata}, #{filecontent})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int addFile(FileModel file);
    //    Creating a new File

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileModel getFile(Integer fileId);
//    Getting File by fileId

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<FileModel> getUserId(Integer userId);
//    Getting File by userId

//    @Select("SELECT * FROM FILES")
//    ArrayList<FileModel> getAllFiles(Integer userId);
////    Getting all the filesName avialable

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);
//    Deleting FILES by fileId

    @Update("UPDATE FILES SET filename = #{filename}, filesize = #{filesize}, filedata = #{filedata}, filecontent = #{filecontent}WHERE fileId = #{fileId}")
    int updateFile(FileModel fileModel);
//    Updating FILES by fileId (editing)
}

