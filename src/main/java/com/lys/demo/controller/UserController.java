package com.lys.demo.controller;

import com.lys.demo.model.User;
import com.lys.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@Api(value="测试api", tags="测试api")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="获取学生的信息", notes="根据学生的ID来获取详情")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    @RequestMapping("/select")
    public User selectUser() {
        User user = userService.selectUserById(1);
        return user;
    }

    @ApiOperation(value="获取所有学生的信息", notes="获取所有学生的信息")
    @RequestMapping("/list")
    public List<User> list(){
        List<String> list = Arrays.asList("xioa","wangs");
        return userService.listUsers();
    }

    @ApiIgnore
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
