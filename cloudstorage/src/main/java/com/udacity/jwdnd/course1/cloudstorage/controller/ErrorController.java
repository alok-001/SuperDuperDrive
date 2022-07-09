package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest){
        Object stat = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (stat!=null){
            Integer statusCode = Integer.valueOf(stat.toString());
            if (statusCode== HttpStatus.NOT_FOUND.value()){
                return "error-404";
            }else if (statusCode==HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
