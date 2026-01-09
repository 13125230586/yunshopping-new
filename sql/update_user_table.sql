-- ===================================================================
-- 用户表添加会员字段
-- ===================================================================

USE yunshopping;

-- 为user表添加会员相关字段
ALTER TABLE `user`
    ADD COLUMN `memberId` BIGINT NULL COMMENT '会员ID' AFTER `defaultAddressId`,
    ADD COLUMN `isMember` TINYINT DEFAULT 0 NOT NULL COMMENT '是否会员：0-否 1-是' AFTER `memberId`,
    ADD INDEX `idx_memberId` (`memberId`),
    ADD INDEX `idx_isMember` (`isMember`);