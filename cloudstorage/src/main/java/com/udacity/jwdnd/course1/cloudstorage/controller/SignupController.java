package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    @Autowired
    public SignupController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public String signupPageView(){
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute UserModel userModel, Model model, RedirectAttributes redirectAttributes){
        String signUpErrorMsg = null;

        if (!userService.userExist(userModel.getModelusername())){
            signUpErrorMsg = "User Already Exist";
        }
        if (signUpErrorMsg==null){
            Integer row = userService.insert(userModel);
            if (row<0){
                signUpErrorMsg = "Error in signup";
                model.addAttribute("Signup error", signUpErrorMsg);
            }else {
                model.addAttribute("SignUpSuccessMsg", true);
            }
        }
        if (signUpErrorMsg == null) {
            redirectAttributes.addFlashAttribute("SuccessMessage","Sign Up Successfully");
            return "redirect:/login?success";
        }
        return "redirect:/login?error";
    }
}
