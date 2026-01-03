package com.yunshen.yunshoppingbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunshen.yunshoppingbackend.model.dto.user.UserPasswordUpdateRequest;
import com.yunshen.yunshoppingbackend.model.dto.user.UserProfileUpdateRequest;
import com.yunshen.yunshoppingbackend.model.dto.user.UserQueryRequest;
import com.yunshen.yunshoppingbackend.model.dto.user.UserUpdateRequest;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     */
    boolean isAdmin(User user);

    /**
     * 用户注销
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 分页查询用户列表
     */
    Page<UserVO> listUserVOByPage(UserQueryRequest userQueryRequest);

    /**
     * 更新用户信息
     */
    Boolean updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 获取用户VO
     */
    UserVO getUserVO(User user);

    /**
     * 更新个人资料
     */
    Boolean updateProfile(UserProfileUpdateRequest profileUpdateRequest, User loginUser);

    /**
     * 修改密码
     */
    Boolean updatePassword(UserPasswordUpdateRequest passwordUpdateRequest, User loginUser);
}