package com.xd.xdshiro.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @RequiresPermissions({"user:add"})
    @GetMapping("add")
    public String add(){
        return "/user/add";
    }

    @GetMapping("update")
    public String update(){
        return "/user/update";
    }

    @GetMapping("toLogin")
    public String toLogin(){
        return "/login";
    }

    @GetMapping("noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    @PostMapping("/login")
    public String login(String name,String password,Model model){
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try{
            subject.login(token);
            return "redirect:test";
        }catch (UnknownAccountException e){
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
