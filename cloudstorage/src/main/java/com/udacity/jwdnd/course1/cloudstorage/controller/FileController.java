package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    private UserService userService;
    private FileService fileService;

    @Autowired
    public FileController(UserService userService, FileService fileService){
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String createFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        FileModel fileModel = new FileModel();
        if (userId!=null){
            if (multipartFile.isEmpty()){
                model.addAttribute("errormsg", true);
            }
            fileService.insert(multipartFile, userId);
            model.addAttribute("success", true);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId){
        Integer delete = fileService.deleteFile(fileId);
        if (delete==null){
            return "redirect:/result?error";
        }else {
            return "redirect:/result?success";
        }
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable Integer fileId){
        FileModel fileModel = fileService.getFileId(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename = "+ fileModel.getFilename())
                .contentLength(fileModel.getFiledata().length)
                .body(new ByteArrayResource(fileModel.getFiledata()));
    }
}
