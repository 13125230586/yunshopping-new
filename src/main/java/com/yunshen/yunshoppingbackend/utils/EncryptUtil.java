package com.yunshen.yunshoppingbackend.utils;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 加密工具类
 */
public class EncryptUtil {

    private static final String SALT = "yunshopping_salt";

    /**
     * MD5加密
     */
    public static String md5(String text) {
        return DigestUtil.md5Hex(text + SALT);
    }

    /**
     * 校验密码
     */
    public static boolean verify(String plainText, String encryptedText) {
        return md5(plainText).equals(encryptedText);
    }
}