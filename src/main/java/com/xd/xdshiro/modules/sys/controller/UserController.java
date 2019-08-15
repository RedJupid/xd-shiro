package com.xd.xdshiro.modules.sys.controller;


import com.xd.xdshiro.modules.sys.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xd.xdshiro.base.BaseController;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author xd
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController<User> {

}

