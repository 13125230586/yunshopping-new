package com.yunshen.yunshoppingbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝支付配置
 */
@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {

    private String appId;

    private String privateKey;

    private String publicKey;

    private String gatewayUrl;

    private String notifyUrl;

    private String returnUrl;
}