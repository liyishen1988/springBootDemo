package com.lys.demo.controller;

import com.lys.demo.dto.UserDTO;
import com.lys.demo.entity.Resource;
import com.lys.demo.entity.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Administrator on 2020/12/23.
 */
@RestController
@RequestMapping("/restLogin")
public class RestLoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Set<Resource> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);

    }


}
