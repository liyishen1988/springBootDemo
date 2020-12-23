package com.lys.demo.service.impl;

import com.lys.demo.dao.ResourceDao;
import com.lys.demo.dao.UserDao;
import com.lys.demo.dto.UserDTO;
import com.lys.demo.entity.Resource;
import com.lys.demo.entity.User;
import com.lys.demo.filter.RequestContext;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    ResourceDao resourceDao;

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

    @Override
    public void listUsersByLoginUser() {
        User user = RequestContext.getCurrentUser();
        System.out.println("service层---当前登录用户对象：" + user);
    }

    @Override
    public Set<Resource> login(UserDTO userDTO) {
        // 根据前端传递过来的账号密码从数据库中查询用户数据
        User user = userDao.selectByNameAndPassword(userDTO.getName(), userDTO.getPassword());
        if (user == null) {
            throw new RuntimeException("账号或密码错误");
        }

        return resourceDao.getCurrentUserMenus();
    }
}
