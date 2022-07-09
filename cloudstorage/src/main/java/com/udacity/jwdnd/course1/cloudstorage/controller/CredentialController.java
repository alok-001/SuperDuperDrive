package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Random;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private UserService userService;

    @Autowired
    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService){
        this.credentialService= credentialService;
        this.encryptionService= encryptionService;
        this.userService= userService;
    }



    @PostMapping("/add")
    public String addCredential(Authentication authentication, CredentialModel credential,
                                @RequestParam("url") String url,
                                @RequestParam("username")String username,
                                @RequestParam("password")String password){
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        Integer credentialId = credential.getCredentialid();
        CredentialModel credentialExist = credentialService.getCredentialId(credentialId);
        if (userId!= null){
            if (credentialId!= null){
                if (credentialExist.getCredentialid()==credentialId){
                    CredentialModel credentialModel = new CredentialModel();
                    credentialModel.setUrl(url);
                    credentialModel.setPassword(password);
                    credentialModel.setUsername(username);
                    credentialModel.setCredentialid(credentialId);
                    credentialModel.setUserid(userId);


                    int already = credentialService.updateCredential(credentialModel);
                    if (already!=0){
                        return "redirect:/result?success";
                    }
                }
            }else {
                CredentialModel credentialModel = new CredentialModel();
                credentialModel.setUsername(username);
                credentialModel.setUserid(userId);
                credentialModel.setUrl(url);
                credentialModel.setPassword(password);
                int addNew = credentialService.insert(credentialModel);
                if (addNew!=0){
                    return "redirect:/result?success";
                }
            }
        }
        return "redirect:/result?error";
    }


    @GetMapping("/delete/{credentialId:.+}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer credentialId){
        UserModel user = userService.getUser(authentication.getName());
        Integer userId = user.getModeluserId();
        CredentialModel credential = credentialService.getCredentialId(credentialId);
        if (userId!= null){
            if (credential.getCredentialid()==credentialId){
                credentialService.deleteCredential(credentialId);
                return "redirect:/result?success";
            }else {
                return "redirect:/result?error";
            }
        }
        return "redirect:/result?error";
    }


}
