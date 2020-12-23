package com.lys.demo.service;


import com.lys.demo.entity.User;

import java.util.List;

public interface UserService {

    public User selectUserById(long userId);

    List<User> listUsers();

    void addUser(User user);

    void listUsersByLoginUser();
}
