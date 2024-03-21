package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
        model.addAttribute("usrs", users);
        return "users/showAll";
    }
    
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session){
        Users user = (Users) session.getAttribute("session_user");
        if (user == null){
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/protected";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        // processing login
        String name = formData.get("name");
        String pwd = formData.get("password");
        List<Users> userlist = usersRepository.findByNameAndPassword(name, pwd);
        if (userlist.isEmpty()){
            return "users/login";
        }
        else {
            // success
            Users user = userlist.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "users/protected";
        }
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "/users/login";
    }
}
