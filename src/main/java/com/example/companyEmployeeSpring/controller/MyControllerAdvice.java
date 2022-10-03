package com.example.companyEmployeeSpring.controller;


import com.example.companyEmployeeSpring.entity.User;
import com.example.companyEmployeeSpring.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return currentUser.getUser();
        }
        return null;
    }

    @ModelAttribute(name="correntUrl")
    public String currentUrl(HttpServletRequest request){
        return request.getRequestURI();
    }


}
