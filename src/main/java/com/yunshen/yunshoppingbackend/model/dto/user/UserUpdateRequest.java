package com.yunshen.yunshoppingbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户更新请求（管理员）
 */
@Data
public class UserUpdateRequest implements Serializable {

    private Long id;

    private String userName;

    private String userAvatar;

    private String userProfile;

    private String userRole;

    private String phone;

    private String email;

    private Integer gender;

    private Date birthday;

    private static final long serialVersionUID = 1L;
}