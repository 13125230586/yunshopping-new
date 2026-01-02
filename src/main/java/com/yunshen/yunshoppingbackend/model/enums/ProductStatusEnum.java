package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 商品状态枚举
 */
@Getter
public enum ProductStatusEnum {

    WAIT_SHELVE(0, "待上架"),
    SHELVED(1, "已上架"),
    UNSHELVED(2, "已下架");

    private final Integer value;

    private final String text;

    ProductStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据 value 获取枚举
     */
    public static ProductStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (ProductStatusEnum anEnum : ProductStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}