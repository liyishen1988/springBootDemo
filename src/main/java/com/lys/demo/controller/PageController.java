package com.lys.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2020/12/22.
 */
@Controller
public class PageController {

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("name","吹泡泡的魚");
        return "index";
    }


}
