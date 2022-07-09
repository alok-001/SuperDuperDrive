package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class NoteModel implements Serializable {
    private Integer noteid;
    private Integer userId;
    private String notetitle;
    private String notedescription;

    public NoteModel(
            Integer noteid,
            Integer userId,
            String notetitle,
            String notedescription
    ){
        this.notedescription = notedescription;
        this.notetitle = notetitle;
        this.noteid =noteid;
        this.userId = userId;
    }

    public NoteModel() {

    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }
}
