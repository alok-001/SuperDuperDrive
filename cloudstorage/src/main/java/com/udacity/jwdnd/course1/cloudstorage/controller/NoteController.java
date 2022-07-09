package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    private UserService userService;
    private NoteService noteService;
    private EncryptionService encryptionService;

    public NoteController(UserService userService, NoteService noteService, EncryptionService encryptionService){
        this.encryptionService = encryptionService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/delete/{noteid:.+}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteid) {
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        NoteModel note = noteService.getNote(noteid);
        if (userId != null) {
            if (note.getNoteid().equals(noteid)) {
                int delete = noteService.deleteNote(noteid);
                if (delete==1){
                    return "redirect:/result?success";
                }
            } else {
                return "redirect:/result?error";
            }
        }
        return "redirect:/result?error";
    }

    @PostMapping("/add")
    public String newNote(Authentication authentication, NoteModel note){
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        Integer noteId = note.getNoteid();
        NoteModel noteExist = noteService.getNote(noteId);
        if (userId!= null){
            if (noteId!= null){
                if (noteExist.getNoteid().equals(noteId)){
                    int update = noteService.updateNote(note);
                    if (update!=0){
                        return "redirect:/result?success";
                    }
                }
            }else {
                note.setUserId(userId);
                noteService.insert(userId, note);
                return "redirect:/result?success";
            }
        }
        return "redirect:/result?error";
    }
}
