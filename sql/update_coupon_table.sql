-- ===================================================================
-- 优惠券表添加会员等级限制字段
-- ===================================================================

USE yunshopping;

-- 为coupon表添加会员等级限制字段
ALTER TABLE `coupon`
    ADD COLUMN `forMemberLevel` VARCHAR(256) NULL COMMENT '会员等级限制：NULL表示全员可用，JSON数组存储等级ID，如[1,2,3]' AFTER `shopId`;