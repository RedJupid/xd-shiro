package com.xd.xdshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author xd
 * @Description
 * @Date 2019/8/16 14:20
 */
@Configuration
public class ShiroConfig {

    @Autowired
    ShiroProperties shiroProperties;

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         *     常用的过滤器：
         *         anon: 无需认证（登录）可以访问
         *         authc: 必须认证才可以访问
         *         user: 如果使用rememberMe的功能可以直接访问
         *         perms: 改资源必须得到资源权限才可访问
         *         role: 改资源必须得到角色权限才可访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();

        for (String value : shiroProperties.getAnon()) {// 不要验证
            filterMap.put(value, ShiroProperties.ANON);
        }
        for (String value : shiroProperties.getAuthc()) {// 需要验证
            filterMap.put(value, ShiroProperties.AUTHC);
        }

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("toLogin");

        //修改未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm对象
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //配置shiroDialect，用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException","noAuth");
        properties.setProperty("org.apache.shiro.authz.AuthorizationException","noAuth");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }

}
