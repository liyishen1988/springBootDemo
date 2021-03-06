package com.lys.baseJava.代码设计;

import com.lys.demo.entity.User;
import com.lys.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(value="学生信息Controller", tags="学生信息查询")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="获取学生的信息", notes="根据学生的ID来获取详情")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    @RequestMapping("/select/{id}")
    public User selectUser(@PathVariable int id) {
        User user = userService.selectUserById(id);
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
    public void addUser(@RequestBody @Validated User user){
//        User user = new User("zhangyuy",23,1);
        user.setBirthday(new Date());
        user.setRole("学生");
        user.setEmail("xxxxxx");
        user.setMobile("1888888888");
        userService.addUser(user);
    }

}
