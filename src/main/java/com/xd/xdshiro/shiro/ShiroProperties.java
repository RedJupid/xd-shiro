package com.xd.xdshiro.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xd
 * @Description
 * @Date 2019/8/21 14:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    public static final String ANON = "anon";
    public static final String AUTHC = "authc";

    /**
     * 不拦截的路径集合
     */
    private List<String> anon;
    /**
     * 拦截的路径集合
     */
    private List<String> authc;
}
