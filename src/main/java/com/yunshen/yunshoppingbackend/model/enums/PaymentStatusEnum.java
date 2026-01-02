package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum PaymentStatusEnum {

    WAIT(0, "待支付"),
    PAID(1, "已支付"),
    REFUNDED(2, "已退款");

    private final Integer value;

    private final String text;

    PaymentStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据 value 获取枚举
     */
    public static PaymentStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (PaymentStatusEnum anEnum : PaymentStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}