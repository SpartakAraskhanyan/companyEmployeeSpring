package com.example.companyEmployeeSpring.controller;


import com.example.companyEmployeeSpring.entity.Employee;
import com.example.companyEmployeeSpring.entity.Role;
import com.example.companyEmployeeSpring.entity.User;
import com.example.companyEmployeeSpring.repasitory.UserRepository;
import com.example.companyEmployeeSpring.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/register")
    public String addUser() {
        return "user";
    }

    @PostMapping("/user/register")
    public String addUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return "redirect:/";
    }
}
