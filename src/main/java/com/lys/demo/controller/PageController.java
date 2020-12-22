package com.lys.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转index.html页面，用于测试Vue
 */
@Controller
public class PageController {

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("name","吹泡泡的魚");
        return "index";
    }


}
