package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {
    
    @Autowired
    private UserRepository usersRepository;

    public UsersController() {
        // users.add(new Users("John", "password", 30));
        // users.add(new Users("Jane", "password", 25));
        // users.add(new Users("Doe", "password", 35));

    }

    @GetMapping("/users/all")
    public String getAllUsers(Model model) {
        System.out.println("Hello from all users");
        List<Users> users = usersRepository.findAll(); // db
        model.addAttribute("users", users);
        return "users/all";
    }
    
}
