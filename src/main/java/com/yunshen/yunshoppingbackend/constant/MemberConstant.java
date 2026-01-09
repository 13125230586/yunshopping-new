package com.yunshen.yunshoppingbackend.constant;

/**
 * 会员相关常量
 */
public interface MemberConstant {

    /**
     * 会员状态
     */
    int STATUS_NORMAL = 0;
    int STATUS_EXPIRED = 1;
    int STATUS_FROZEN = 2;

    /**
     * 权益类型
     */
    int BENEFIT_TYPE_COUPON = 1;
    int BENEFIT_TYPE_DISCOUNT = 2;
    int BENEFIT_TYPE_OTHER = 3;

    /**
     * 触发事件
     */
    String TRIGGER_EVENT_ACTIVATE = "ACTIVATE";
    String TRIGGER_EVENT_UPGRADE = "UPGRADE";
    String TRIGGER_EVENT_BIRTHDAY = "BIRTHDAY";

    /**
     * 会员等级代码
     */
    String LEVEL_CODE_NORMAL = "NORMAL";
    String LEVEL_CODE_SILVER = "SILVER";
    String LEVEL_CODE_GOLD = "GOLD";
    String LEVEL_CODE_DIAMOND = "DIAMOND";

    /**
     * 是否会员
     */
    int IS_MEMBER_NO = 0;
    int IS_MEMBER_YES = 1;
}