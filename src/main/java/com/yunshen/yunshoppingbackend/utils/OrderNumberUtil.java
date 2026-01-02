package com.yunshen.yunshoppingbackend.utils;

import cn.hutool.core.date.DateUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单号生成工具类
 */
public class





OrderNumberUtil {

    private static final AtomicInteger SEQUENCE = new AtomicInteger(10000);

    private static final int MAX_SEQUENCE = 99999;

    /**
     * 生成订单号
     * 格式 yyyyMMddHHmmss + 5位递增序列号
     */
    public static String generateOrderNo() {
        String dateStr = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
        int sequence = SEQUENCE.getAndIncrement();
        if (sequence > MAX_SEQUENCE) {
            SEQUENCE.set(10000);
            sequence = SEQUENCE.getAndIncrement();
        }
        return dateStr + sequence;
    }

    /**
     * 生成支付流水号
     */
    public static String generatePaymentNo() {
        String dateStr = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
        int sequence = SEQUENCE.getAndIncrement();
        if (sequence > MAX_SEQUENCE) {
            SEQUENCE.set(10000);
            sequence = SEQUENCE.getAndIncrement();
        }
        return "PAY" + dateStr + sequence;
    }
}