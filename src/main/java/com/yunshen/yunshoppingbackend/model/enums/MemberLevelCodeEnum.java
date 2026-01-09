package com.yunshen.yunshoppingbackend.model.enums;

import lombok.Getter;

/**
 * 会员等级代码枚举
 */
@Getter
public enum MemberLevelCodeEnum {

    NORMAL("NORMAL", "普通会员"),
    SILVER("SILVER", "银卡会员"),
    GOLD("GOLD", "金卡会员"),
    DIAMOND("DIAMOND", "钻石会员");

    private final String code;
    private final String text;

    MemberLevelCodeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取枚举
     */
    public static MemberLevelCodeEnum getEnumByCode(String code) {
        if (code == null) {
            return null;
        }
        for (MemberLevelCodeEnum levelEnum : MemberLevelCodeEnum.values()) {
            if (levelEnum.code.equals(code)) {
                return levelEnum;
            }
        }
        return null;
    }
}