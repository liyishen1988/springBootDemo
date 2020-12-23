package com.lys.demo.controller;

import com.lys.demo.entity.Resource;
import com.lys.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 权限：菜单权限+按钮权限+操作权限
 */
@Controller
public class ViewController {

    @Autowired
    ResourceService resourceService;

    @GetMapping("/auth")
    public String index(HttpServletRequest request) {
        // 菜单名映射字典。key为uri路径，value为菜单名称，方便视图根据uri路径渲染菜单名
//        Map<String, String> menuMap = new HashMap<>();
//        menuMap.put("/user/account", "用户管理");
//        menuMap.put("/user/role", "权限管理");
//        menuMap.put("/data", "数据管理");
//        request.setAttribute("menuMap", menuMap);

        // 获取当前用户的所有页面权限，并将数据放到request对象中好让视图渲染
        Set<Resource> menus = resourceService.getCurrentUserMenus();
        request.setAttribute("menus", menus);
        return "index";
    }

}
