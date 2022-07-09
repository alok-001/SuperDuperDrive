package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class CredentialModel implements Serializable {
    private Integer credentialId;
    private Integer userId;
    private  String url;
    private String password;
    private String key;
    private String username;

    public CredentialModel(
            Integer credentialId,
            Integer userId,
            String url,
            String password,
            String key,
            String username
    ){
        this.credentialId = credentialId;
        this.password = password;
        this.key =key;
        this.url = url;
        this.username = username;
        this.userId = userId;
    }

    public CredentialModel() {

    }

    public Integer getCredentialid() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    @Override
    public String toString() {
        return "CredentialModel{" +
                "credentialId=" + credentialId +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setCredentialid(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
