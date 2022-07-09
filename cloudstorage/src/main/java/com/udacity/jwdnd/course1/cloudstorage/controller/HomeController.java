package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    @Autowired
    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService){
        this.credentialService = credentialService;
        this.encryptionService =encryptionService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }

    @GetMapping("/logout")
    public String logoutView(){
        return "redirect:/login?logout";
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Authentication authentication, Model model){
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        if (userId!= null){
            model.addAttribute("note", new NoteModel());
            model.addAttribute("credentialService", new CredentialService());
            model.addAttribute("credent", new CredentialModel());
            model.addAttribute("files", fileService.getAllFiles(userId));
            model.addAttribute("notes", noteService.getUserId(userId));
            model.addAttribute("encryptionService", encryptionService);
            model.addAttribute("credential", credentialService.getAllCredentials(userId));
        }
        return "home";
    }
}
