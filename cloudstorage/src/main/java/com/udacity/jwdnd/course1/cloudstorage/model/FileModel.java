package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;
import java.util.Arrays;

public class FileModel implements Serializable {
    private Integer fileid;
    private Integer userId;
    private String filename;
    private String filesize;
    private byte[] filedata;
    private String filecontent;

    public FileModel(
            Integer fileid,
            String filename,
            String filecontent,
            String filesize,
            Integer userId,
            byte[] filedata
    ){
        this.filecontent = filecontent;
        this.fileid= fileid;
        this.filedata = filedata;
        this.filename = filename;
        this.filesize =filesize;
        this.userId = userId;
    }

    public FileModel() {

    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFileId() {
        return fileid;
    }

    public void setFileId(Integer fileid) {
        this.fileid = fileid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public String getFileContent() {
        return filecontent;
    }


    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileSize() {
        return filesize;
    }

    public void setFileContent(String filecontent) {
        this.filecontent = filecontent;
    }

    public void setFileData(byte[] filedata) {
        this.filedata = filedata;
    }

    public void setFileSize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getFileid() {
        return fileid;
    }

    public String getFilecontent() {
        return filecontent;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilecontent(String filecontent) {
        this.filecontent = filecontent;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "fileid=" + fileid +
                ", userId=" + userId +
                ", filename='" + filename + '\'' +
                ", filesize='" + filesize + '\'' +
                ", filedata=" + Arrays.toString(filedata) +
                ", filecontent='" + filecontent + '\'' +
                '}';
    }
}
