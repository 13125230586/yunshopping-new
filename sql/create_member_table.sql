-- ===================================================================
-- 会员系统表创建脚本
-- ===================================================================

USE yunshopping;

-- ============================================
-- 1. 会员等级配置表 (member_level)
-- ============================================
CREATE TABLE IF NOT EXISTS `member_level` (
    `id` BIGINT AUTO_INCREMENT COMMENT '等级ID' PRIMARY KEY,
    `levelName` VARCHAR(64) NOT NULL COMMENT '等级名称',
    `levelCode` VARCHAR(32) NOT NULL COMMENT '等级代码：NORMAL-普通会员 SILVER-银卡 GOLD-金卡 DIAMOND-钻石卡',
    `discountRate` DECIMAL(3,2) NOT NULL DEFAULT 1.00 COMMENT '折扣率：0.95表示95折',
    `requiredGrowth` INT DEFAULT 0 NOT NULL COMMENT '升级所需成长值',
    `welcomeCouponId` BIGINT NULL COMMENT '开通赠送的优惠券ID',
    `birthdayCouponId` BIGINT NULL COMMENT '生日月赠送优惠券ID',
    `icon` VARCHAR(512) NULL COMMENT '等级图标URL',
    `sortOrder` INT DEFAULT 0 NOT NULL COMMENT '排序序号，数字越小越靠前',
    `description` VARCHAR(512) NULL COMMENT '等级描述',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    UNIQUE KEY `uk_levelCode` (`levelCode`),
    INDEX `idx_sortOrder` (`sortOrder`)
) COMMENT '会员等级配置表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 2. 会员信息表 (member)
-- ============================================
CREATE TABLE IF NOT EXISTS `member` (
    `id` BIGINT AUTO_INCREMENT COMMENT '会员ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `levelId` BIGINT NOT NULL COMMENT '当前等级ID',
    `growthValue` INT DEFAULT 0 NOT NULL COMMENT '成长值',
    `expireTime` DATETIME NULL COMMENT '到期时间，NULL表示永久有效',
    `activateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '开通时间',
    `status` INT DEFAULT 0 NOT NULL COMMENT '状态：0-正常 1-已过期 2-已冻结',
    `totalConsumeAmount` DECIMAL(10,2) DEFAULT 0.00 NOT NULL COMMENT '累计消费金额',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    UNIQUE KEY `uk_userId` (`userId`),
    INDEX `idx_levelId` (`levelId`),
    INDEX `idx_status` (`status`),
    INDEX `idx_expireTime` (`expireTime`)
) COMMENT '会员信息表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 3. 会员权益发放日志表 (member_benefit_log)
-- ============================================
CREATE TABLE IF NOT EXISTS `member_benefit_log` (
    `id` BIGINT AUTO_INCREMENT COMMENT '日志ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `memberId` BIGINT NOT NULL COMMENT '会员ID',
    `benefitType` INT NOT NULL COMMENT '权益类型：1-优惠券 2-折扣 3-其他',
    `benefitValue` VARCHAR(256) NULL COMMENT '权益值：优惠券ID、折扣率等',
    `triggerEvent` VARCHAR(64) NOT NULL COMMENT '触发事件：ACTIVATE-开通会员 UPGRADE-升级 BIRTHDAY-生日',
    `description` VARCHAR(512) NULL COMMENT '描述',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_memberId` (`memberId`),
    INDEX `idx_benefitType` (`benefitType`)
) COMMENT '会员权益发放日志表' COLLATE = utf8mb4_unicode_ci;