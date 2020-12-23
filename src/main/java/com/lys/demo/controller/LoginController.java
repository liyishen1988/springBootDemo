package com.lys.demo.controller;

import com.lys.demo.entity.User;
import com.lys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 登陆认证
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if ("admin".equals(user.getName()) && "123456".equals(user.getPassword())) {
            // 正确的话就将用户信息存到session中
            session.setAttribute("user", user);
            return "登录成功";
        }
        return "账号或密码错误";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 退出登录就是将用户信息删除
        session.removeAttribute("user");
        return "退出成功";
    }

    @GetMapping("/login/user/list")
    public String api() {
        userService.listUsersByLoginUser();
        return "成功返回数据";
    }
}
