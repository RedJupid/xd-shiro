package com.xd.xdshiro.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xd
 * @Description
 * @Date 2019/8/16 13:52
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("name","测试");
        return "test";
    }
}
