package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 会员状态枚举
 */
@Getter
public enum MemberStatusEnum {

    NORMAL(0, "正常"),
    EXPIRED(1, "已过期"),
    FROZEN(2, "已冻结");

    private final int value;
    private final String text;

    MemberStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据value获取枚举
     */
    public static MemberStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (MemberStatusEnum statusEnum : MemberStatusEnum.values()) {
            if (statusEnum.value == value) {
                return statusEnum;
            }
        }
        return null;
    }
}