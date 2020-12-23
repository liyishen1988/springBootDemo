package com.lys.demo.service;


import com.lys.demo.dto.UserDTO;
import com.lys.demo.entity.Resource;
import com.lys.demo.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User selectUserById(long userId);

    List<User> listUsers();

    void addUser(User user);

    void listUsersByLoginUser();

    Set<Resource> login(UserDTO userDTO);
}
