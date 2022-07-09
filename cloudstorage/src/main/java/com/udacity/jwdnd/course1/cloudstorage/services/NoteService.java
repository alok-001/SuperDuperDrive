package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    public NoteService(NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    //    Inserting or adding to the note
    public void insert(Integer userId, NoteModel noteModel){
        NoteModel note = new NoteModel(null, userId, noteModel.getNotetitle(), noteModel.getNotedescription());
        noteMapper.addNote(note);
    }

    //   Getting note by note id
    public NoteModel getNote(Integer noteId){
        return noteMapper.getNoteId(noteId);
    }

    //    Getting note by userid
    public List<NoteModel> getUserId(Integer userId){
        return noteMapper.getUserId(userId);
    }


    //    Deleting note by note id
    public int deleteNote(Integer noteId){
        return noteMapper.deleteNote(noteId);
    }

    //    Updating note by note
    public int updateNote(NoteModel noteModel){
        return noteMapper.updateNote(noteModel);
    }


}
