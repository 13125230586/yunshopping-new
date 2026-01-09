package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 会员权益类型枚举
 */
@Getter
public enum BenefitTypeEnum {

    COUPON(1, "优惠券"),
    DISCOUNT(2, "折扣"),
    OTHER(3, "其他");

    private final int value;
    private final String text;

    BenefitTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据value获取枚举
     */
    public static BenefitTypeEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (BenefitTypeEnum typeEnum : BenefitTypeEnum.values()) {
            if (typeEnum.value == value) {
                return typeEnum;
            }
        }
        return null;
    }
}