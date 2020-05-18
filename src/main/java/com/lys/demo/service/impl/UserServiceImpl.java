package com.lys.demo.service.impl;

import com.lys.demo.dao.UserDao;
import com.lys.demo.model.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User selectUserById(long userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
