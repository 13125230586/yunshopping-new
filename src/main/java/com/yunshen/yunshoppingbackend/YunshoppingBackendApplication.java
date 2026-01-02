package com.yunshen.yunshoppingbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 云商城系统启动类
 */
@SpringBootApplication
@MapperScan("com.yunshen.yunshoppingbackend.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class YunshoppingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunshoppingBackendApplication.class, args);
    }

}
