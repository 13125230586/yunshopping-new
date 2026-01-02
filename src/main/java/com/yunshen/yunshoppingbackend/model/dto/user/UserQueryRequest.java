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

    private Long id;

    private String userAccount;

    private String userName;

    private String userRole;

    private static final long serialVersionUID = 1L;
}