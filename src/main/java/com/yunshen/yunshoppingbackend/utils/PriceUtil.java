package com.yunshen.yunshoppingbackend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 价格计算工具类
 */
public class PriceUtil {

    /**
     * 计算总价
     */
    public static BigDecimal calculateTotalAmount(BigDecimal price, Integer quantity) {
        if (price == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return price.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算折扣价格
     */
    public static BigDecimal calculateDiscountPrice(BigDecimal originalPrice, BigDecimal discountRate) {
        if (originalPrice == null || discountRate == null) {
            return originalPrice;
        }
        return originalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算满减价格
     */
    public static BigDecimal calculateCouponPrice(BigDecimal totalAmount, BigDecimal discountAmount) {
        if (totalAmount == null || discountAmount == null) {
            return totalAmount;
        }
        BigDecimal result = totalAmount.subtract(discountAmount);
        return result.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : result.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 乘法运算
     */
    public static BigDecimal multiply(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }
        return a.multiply(new BigDecimal(b)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 加法运算
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) {
            return b == null ? BigDecimal.ZERO : b;
        }
        if (b == null) {
            return a;
        }
        return a.add(b).setScale(2, RoundingMode.HALF_UP);
    }
}