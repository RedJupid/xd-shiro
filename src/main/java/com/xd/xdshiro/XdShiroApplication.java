package com.xd.xdshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xd.xdshiro.modules.sys.mapper"})
public class XdShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdShiroApplication.class, args);
    }

}
