package com.yunshen.yunshoppingbackend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus 配置
 */
@Configuration
@MapperScan("com.yunshen.yunshoppingbackend.mapper")
public class MyBatisPlusConfig {

}