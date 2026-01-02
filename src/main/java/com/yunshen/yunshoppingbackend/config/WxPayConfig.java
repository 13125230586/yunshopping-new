package com.yunshen.yunshoppingbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 */
@Configuration
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayConfig {

    private String appId;

    private String mchId;

    private String apiV3Key;

    private String privateKeyPath;

    private String merchantSerialNumber;

    private String notifyUrl;
}