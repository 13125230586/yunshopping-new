package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 店铺状态枚举
 */
@Getter
public enum ShopStatusEnum {

    PENDING(0, "待审核"),
    OPEN(1, "营业中"),
    CLOSED(2, "已关闭"),
    REJECTED(3, "审核拒绝");

    private final Integer value;

    private final String text;

    ShopStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据 value 获取枚举
     */
    public static ShopStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (ShopStatusEnum anEnum : ShopStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}