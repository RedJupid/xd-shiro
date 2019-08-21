package com.xd.xdshiro.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.xdshiro.modules.sys.entity.User;
import com.xd.xdshiro.modules.sys.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author xd
 * @Description
 * @Date 2019/8/16 14:24
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        User dbUser = userService.getById(user.getId());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

//        String name = "admin";
//        String password = "123456";


        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",token.getUsername());
        User user = userService.getOne(qw,false);
//        if (!token.getUsername().equals(name)){
//            //用户名不错在
//            return null;//shiro底层会抛出UnKnowAccountException
//        }
        if (user==null){
            return null;
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
