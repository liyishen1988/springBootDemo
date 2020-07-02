package com.lys.demo.controller;


import com.lys.demo.dto.UserInputDTO;
import com.lys.demo.entity.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("user/api")
@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping
    public void addUser(UserInputDTO userDTO){
        User user = converUserDTO(userDTO);
        BeanUtils.copyProperties(userDTO,user);
        userService.addUser(user);
    }

    private User converUserDTO(UserInputDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        return user;
    }

}
