package com.lys.demo.controller;

import com.lys.demo.model.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
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

    @RequestMapping("/add")
    public void addUser(){
        User user = new User("zhangyuy",23,1);
        user.setBirthday(new Date());
        user.setRole("学生");
        user.setEmail("xxxxxx");
        user.setMobile("1888888888");
        userService.addUser(user);
    }

}
