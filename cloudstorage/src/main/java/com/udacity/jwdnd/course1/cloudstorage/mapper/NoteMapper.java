package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface NoteMapper {
    @Insert("INSERT INTO NOTES(userId, notetitle, notedescription) VALUES(#{userId}, #{notetitle}, #{notedescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(NoteModel note);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    NoteModel getNoteId(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<NoteModel> getUserId(Integer userId);

    @Select("SELECT * FROM NOTES")
    ArrayList<NoteModel> getAllNotes(Integer userId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    int deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int updateNote(NoteModel noteModel);
}
