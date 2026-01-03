-- ===================================================================
-- 轮播图表创建脚本
-- ===================================================================

USE yunshopping;

-- 创建轮播图表
CREATE TABLE IF NOT EXISTS `banner` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title` VARCHAR(100) NOT NULL COMMENT '轮播图标题',
    `imageUrl` VARCHAR(500) NOT NULL COMMENT '轮播图图片地址',
    `linkType` VARCHAR(20) NOT NULL COMMENT '链接类型: category(分类)、product(商品)、shop(店铺)、url(外链)',
    `linkValue` VARCHAR(200) NOT NULL COMMENT '链接值:分类ID、商品ID、店铺ID或完整URL',
    `sortOrder` INT NOT NULL DEFAULT 0 COMMENT '排序字段,数字越小越靠前',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    INDEX `idx_status_sort` (`status`, `sortOrder`, `isDelete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';