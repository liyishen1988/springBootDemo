package com.lys.demo.controller;

import com.lys.demo.model.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public User selectUser() {
        User user = userService.selectUserById(1);
        return user;
    }

    @RequestMapping("/list")
    public List<User> list(){
        List<String> list = Arrays.asList("xioa","wangs");
        return userService.listUsers();
    }

}
