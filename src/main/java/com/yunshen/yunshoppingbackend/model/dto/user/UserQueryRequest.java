package com.yunshen.yunshoppingbackend.model.dto.user;

import com.yunshen.yunshoppingbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 手机号
     */
    private String phone;

    private static final long serialVersionUID = 1L;
}
