package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 审核状态枚举
 */
@Getter
public enum ReviewStatusEnum {

    PENDING(0, "待审核"),
    PASS(1, "通过"),
    REJECT(2, "拒绝");

    private final Integer value;

    private final String text;

    ReviewStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据 value 获取枚举
     */
    public static ReviewStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (ReviewStatusEnum anEnum : ReviewStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}