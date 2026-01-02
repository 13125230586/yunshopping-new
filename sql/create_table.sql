-- 创建数据库
CREATE DATABASE IF NOT EXISTS yunshopping DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE yunshopping;

-- ============================================
-- 1. 用户表 (user)
-- ============================================
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT AUTO_INCREMENT COMMENT '用户ID' PRIMARY KEY,
    `userAccount` VARCHAR(256) NOT NULL COMMENT '账号',
    `userPassword` VARCHAR(512) NOT NULL COMMENT '密码（加密）',
    `userName` VARCHAR(256) NULL COMMENT '用户昵称',
    `userAvatar` VARCHAR(1024) NULL COMMENT '用户头像URL',
    `userProfile` VARCHAR(512) NULL COMMENT '用户简介',
    `userRole` VARCHAR(256) DEFAULT 'buyer' NOT NULL COMMENT '用户角色：buyer-买家/seller-卖家/admin-管理员',
    `phone` VARCHAR(20) NULL COMMENT '手机号',
    `email` VARCHAR(256) NULL COMMENT '邮箱',
    `gender` TINYINT DEFAULT 0 NULL COMMENT '性别：0-未知 1-男 2-女',
    `birthday` DATE NULL COMMENT '生日',
    `defaultAddressId` BIGINT NULL COMMENT '默认收货地址ID',
    `editTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '编辑时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    UNIQUE KEY `uk_userAccount` (`userAccount`),
    INDEX `idx_userName` (`userName`),
    INDEX `idx_phone` (`phone`),
    INDEX `idx_userRole` (`userRole`)
) COMMENT '用户表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 2. 收货地址表 (address)
-- ============================================
CREATE TABLE IF NOT EXISTS `address` (
    `id` BIGINT AUTO_INCREMENT COMMENT '地址ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `receiverName` VARCHAR(64) NOT NULL COMMENT '收货人姓名',
    `receiverPhone` VARCHAR(20) NOT NULL COMMENT '收货人手机号',
    `province` VARCHAR(64) NOT NULL COMMENT '省份',
    `city` VARCHAR(64) NOT NULL COMMENT '城市',
    `district` VARCHAR(64) NOT NULL COMMENT '区/县',
    `detailAddress` VARCHAR(512) NOT NULL COMMENT '详细地址',
    `postalCode` VARCHAR(20) NULL COMMENT '邮政编码',
    `isDefault` TINYINT DEFAULT 0 NOT NULL COMMENT '是否默认地址：0-否 1-是',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_userId` (`userId`)
) COMMENT '收货地址表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 3. 店铺表 (shop)
-- ============================================
CREATE TABLE IF NOT EXISTS `shop` (
    `id` BIGINT AUTO_INCREMENT COMMENT '店铺ID' PRIMARY KEY,
    `shopName` VARCHAR(128) NOT NULL COMMENT '店铺名称',
    `shopLogo` VARCHAR(1024) NULL COMMENT '店铺Logo URL',
    `shopBanner` VARCHAR(1024) NULL COMMENT '店铺横幅URL',
    `shopDescription` VARCHAR(1024) NULL COMMENT '店铺描述',
    `shopLevel` INT DEFAULT 0 NOT NULL COMMENT '店铺等级：0-普通店 1-品牌店 2-旗舰店',
    `shopStatus` INT DEFAULT 0 NOT NULL COMMENT '店铺状态：0-待审核 1-营业中 2-已关闭 3-审核拒绝',
    `userId` BIGINT NOT NULL COMMENT '店主用户ID',
    `totalSales` BIGINT DEFAULT 0 NULL COMMENT '总销量',
    `totalRevenue` DECIMAL(10,2) DEFAULT 0.00 NULL COMMENT '总营收',
    `rating` DECIMAL(3,2) DEFAULT 5.00 NULL COMMENT '店铺评分（1-5分）',
    `reviewCount` INT DEFAULT 0 NULL COMMENT '评价数量',
    `province` VARCHAR(64) NULL COMMENT '店铺所在省份',
    `city` VARCHAR(64) NULL COMMENT '店铺所在城市',
    `reviewStatus` INT DEFAULT 0 NOT NULL COMMENT '审核状态：0-待审核 1-通过 2-拒绝',
    `reviewMessage` VARCHAR(512) NULL COMMENT '审核信息',
    `reviewerId` BIGINT NULL COMMENT '审核人ID',
    `reviewTime` DATETIME NULL COMMENT '审核时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `editTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '编辑时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_shopName` (`shopName`),
    INDEX `idx_shopLevel` (`shopLevel`),
    INDEX `idx_shopStatus` (`shopStatus`),
    INDEX `idx_reviewStatus` (`reviewStatus`)
) COMMENT '店铺表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 4. 商品分类表 (category)
-- ============================================
CREATE TABLE IF NOT EXISTS `category` (
    `id` BIGINT AUTO_INCREMENT COMMENT '分类ID' PRIMARY KEY,
    `categoryName` VARCHAR(128) NOT NULL COMMENT '分类名称',
    `parentId` BIGINT DEFAULT 0 NOT NULL COMMENT '父分类ID（0表示一级分类）',
    `level` TINYINT DEFAULT 1 NOT NULL COMMENT '分类层级：1-一级 2-二级 3-三级',
    `sortOrder` INT DEFAULT 0 NULL COMMENT '排序序号',
    `icon` VARCHAR(512) NULL COMMENT '分类图标URL',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_parentId` (`parentId`),
    INDEX `idx_level` (`level`)
) COMMENT '商品分类表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 5. 商品表 (product)
-- ============================================
CREATE TABLE IF NOT EXISTS `product` (
    `id` BIGINT AUTO_INCREMENT COMMENT '商品ID' PRIMARY KEY,
    `productName` VARCHAR(256) NOT NULL COMMENT '商品名称',
    `productTitle` VARCHAR(512) NULL COMMENT '商品标题',
    `productDescription` TEXT NULL COMMENT '商品描述',
    `categoryId` BIGINT NOT NULL COMMENT '分类ID',
    `shopId` BIGINT NOT NULL COMMENT '店铺ID',
    `brandName` VARCHAR(128) NULL COMMENT '品牌名称',
    `mainImageUrl` VARCHAR(1024) NOT NULL COMMENT '商品主图URL',
    `imageUrls` TEXT NULL COMMENT '商品图片URL列表（JSON数组）',
    `price` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
    `originalPrice` DECIMAL(10,2) NULL COMMENT '原价',
    `stock` INT DEFAULT 0 NOT NULL COMMENT '库存数量',
    `sales` INT DEFAULT 0 NULL COMMENT '销量',
    `unit` VARCHAR(32) DEFAULT '件' NULL COMMENT '单位',
    `weight` DECIMAL(10,2) NULL COMMENT '重量（kg）',
    `tags` VARCHAR(512) NULL COMMENT '标签（JSON数组）',
    `specifications` TEXT NULL COMMENT '商品规格（JSON）',
    `status` INT DEFAULT 0 NOT NULL COMMENT '商品状态：0-待上架 1-已上架 2-已下架',
    `reviewStatus` INT DEFAULT 0 NOT NULL COMMENT '审核状态：0-待审核 1-通过 2-拒绝',
    `reviewMessage` VARCHAR(512) NULL COMMENT '审核信息',
    `reviewerId` BIGINT NULL COMMENT '审核人ID',
    `reviewTime` DATETIME NULL COMMENT '审核时间',
    `userId` BIGINT NOT NULL COMMENT '发布者用户ID',
    `viewCount` INT DEFAULT 0 NULL COMMENT '浏览次数',
    `favoriteCount` INT DEFAULT 0 NULL COMMENT '收藏次数',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `editTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '编辑时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_productName` (`productName`),
    INDEX `idx_categoryId` (`categoryId`),
    INDEX `idx_shopId` (`shopId`),
    INDEX `idx_userId` (`userId`),
    INDEX `idx_status` (`status`),
    INDEX `idx_reviewStatus` (`reviewStatus`),
    INDEX `idx_price` (`price`)
) COMMENT '商品表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 6. 购物车表 (cart)
-- ============================================
CREATE TABLE IF NOT EXISTS `cart` (
    `id` BIGINT AUTO_INCREMENT COMMENT '购物车ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `productId` BIGINT NOT NULL COMMENT '商品ID',
    `quantity` INT DEFAULT 1 NOT NULL COMMENT '商品数量',
    `specification` VARCHAR(512) NULL COMMENT '选中的规格（JSON）',
    `isChecked` TINYINT DEFAULT 1 NOT NULL COMMENT '是否选中：0-否 1-是',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_productId` (`productId`),
    UNIQUE KEY `uk_user_product` (`userId`, `productId`, `specification`(255))
) COMMENT '购物车表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 7. 订单表 (orders)
-- ============================================
CREATE TABLE IF NOT EXISTS `orders` (
    `id` BIGINT AUTO_INCREMENT COMMENT '订单ID' PRIMARY KEY,
    `orderNo` VARCHAR(64) NOT NULL COMMENT '订单号（唯一）',
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `shopId` BIGINT NOT NULL COMMENT '店铺ID',
    `totalAmount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    `payAmount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',
    `discountAmount` DECIMAL(10,2) DEFAULT 0.00 NULL COMMENT '优惠金额',
    `shippingFee` DECIMAL(10,2) DEFAULT 0.00 NULL COMMENT '运费',
    `paymentMethod` VARCHAR(32) NULL COMMENT '支付方式：alipay-支付宝 wxpay-微信',
    `orderStatus` INT DEFAULT 0 NOT NULL COMMENT '订单状态：0-待支付 1-已支付 2-待发货 3-已发货 4-已完成 5-已取消 6-退款中 7-已退款',
    `paymentStatus` INT DEFAULT 0 NOT NULL COMMENT '支付状态：0-未支付 1-已支付 2-已退款',
    `shippingStatus` INT DEFAULT 0 NOT NULL COMMENT '物流状态：0-未发货 1-已发货 2-已签收',
    `receiverName` VARCHAR(64) NOT NULL COMMENT '收货人姓名',
    `receiverPhone` VARCHAR(20) NOT NULL COMMENT '收货人手机号',
    `receiverAddress` VARCHAR(512) NOT NULL COMMENT '收货地址',
    `buyerMessage` VARCHAR(512) NULL COMMENT '买家留言',
    `payTime` DATETIME NULL COMMENT '支付时间',
    `shipTime` DATETIME NULL COMMENT '发货时间',
    `completeTime` DATETIME NULL COMMENT '完成时间',
    `cancelTime` DATETIME NULL COMMENT '取消时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    UNIQUE KEY `uk_orderNo` (`orderNo`),
    INDEX `idx_userId` (`userId`),
    INDEX `idx_shopId` (`shopId`),
    INDEX `idx_orderStatus` (`orderStatus`),
    INDEX `idx_createTime` (`createTime`)
) COMMENT '订单表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 8. 订单详情表 (order_item)
-- ============================================
CREATE TABLE IF NOT EXISTS `order_item` (
    `id` BIGINT AUTO_INCREMENT COMMENT '订单详情ID' PRIMARY KEY,
    `orderId` BIGINT NOT NULL COMMENT '订单ID',
    `productId` BIGINT NOT NULL COMMENT '商品ID',
    `productName` VARCHAR(256) NOT NULL COMMENT '商品名称',
    `productImage` VARCHAR(1024) NULL COMMENT '商品图片',
    `price` DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    `quantity` INT NOT NULL COMMENT '购买数量',
    `totalAmount` DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    `specification` VARCHAR(512) NULL COMMENT '商品规格（JSON）',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_orderId` (`orderId`),
    INDEX `idx_productId` (`productId`)
) COMMENT '订单详情表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 9. 支付记录表 (payment)
-- ============================================
CREATE TABLE IF NOT EXISTS `payment` (
    `id` BIGINT AUTO_INCREMENT COMMENT '支付ID' PRIMARY KEY,
    `paymentNo` VARCHAR(64) NOT NULL COMMENT '支付流水号',
    `orderId` BIGINT NOT NULL COMMENT '订单ID',
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `paymentMethod` VARCHAR(32) NOT NULL COMMENT '支付方式',
    `payAmount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
    `payStatus` INT DEFAULT 0 NOT NULL COMMENT '支付状态：0-待支付 1-已支付 2-已退款',
    `transactionId` VARCHAR(128) NULL COMMENT '第三方交易流水号',
    `payTime` DATETIME NULL COMMENT '支付时间',
    `refundTime` DATETIME NULL COMMENT '退款时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uk_paymentNo` (`paymentNo`),
    INDEX `idx_orderId` (`orderId`),
    INDEX `idx_userId` (`userId`),
    INDEX `idx_transactionId` (`transactionId`)
) COMMENT '支付记录表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 10. 物流表 (logistics)
-- ============================================
CREATE TABLE IF NOT EXISTS `logistics` (
    `id` BIGINT AUTO_INCREMENT COMMENT '物流ID' PRIMARY KEY,
    `orderId` BIGINT NOT NULL COMMENT '订单ID',
    `logisticsCompany` VARCHAR(128) NULL COMMENT '物流公司',
    `trackingNumber` VARCHAR(128) NULL COMMENT '物流单号',
    `logisticsStatus` INT DEFAULT 0 NOT NULL COMMENT '物流状态：0-未发货 1-运输中 2-派送中 3-已签收',
    `currentLocation` VARCHAR(256) NULL COMMENT '当前位置',
    `logisticsInfo` TEXT NULL COMMENT '物流信息（JSON数组）',
    `shipTime` DATETIME NULL COMMENT '发货时间',
    `receiveTime` DATETIME NULL COMMENT '签收时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_orderId` (`orderId`),
    INDEX `idx_trackingNumber` (`trackingNumber`)
) COMMENT '物流表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 11. 商品评价表 (review)
-- ============================================
CREATE TABLE IF NOT EXISTS `review` (
    `id` BIGINT AUTO_INCREMENT COMMENT '评价ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `productId` BIGINT NOT NULL COMMENT '商品ID',
    `orderId` BIGINT NOT NULL COMMENT '订单ID',
    `shopId` BIGINT NOT NULL COMMENT '店铺ID',
    `rating` TINYINT NOT NULL COMMENT '评分：1-5星',
    `content` TEXT NULL COMMENT '评价内容',
    `images` TEXT NULL COMMENT '评价图片（JSON数组）',
    `isAnonymous` TINYINT DEFAULT 0 NOT NULL COMMENT '是否匿名：0-否 1-是',
    `likeCount` INT DEFAULT 0 NULL COMMENT '点赞数',
    `replyContent` TEXT NULL COMMENT '商家回复',
    `replyTime` DATETIME NULL COMMENT '回复时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_productId` (`productId`),
    INDEX `idx_orderId` (`orderId`),
    INDEX `idx_shopId` (`shopId`)
) COMMENT '商品评价表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 12. 优惠券表 (coupon)
-- ============================================
CREATE TABLE IF NOT EXISTS `coupon` (
    `id` BIGINT AUTO_INCREMENT COMMENT '优惠券ID' PRIMARY KEY,
    `couponName` VARCHAR(128) NOT NULL COMMENT '优惠券名称',
    `couponType` INT NOT NULL COMMENT '优惠券类型：1-满减 2-折扣 3-无门槛',
    `discountAmount` DECIMAL(10,2) NULL COMMENT '优惠金额（满减和无门槛使用）',
    `discountRate` DECIMAL(3,2) NULL COMMENT '折扣率（折扣券使用，如0.8表示8折）',
    `minAmount` DECIMAL(10,2) DEFAULT 0.00 NULL COMMENT '使用门槛（满X元可用）',
    `totalCount` INT NOT NULL COMMENT '发行总数',
    `usedCount` INT DEFAULT 0 NULL COMMENT '已使用数量',
    `shopId` BIGINT NULL COMMENT '店铺ID（NULL表示平台券）',
    `startTime` DATETIME NOT NULL COMMENT '生效时间',
    `endTime` DATETIME NOT NULL COMMENT '失效时间',
    `status` INT DEFAULT 1 NOT NULL COMMENT '状态：0-未开始 1-进行中 2-已结束',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_shopId` (`shopId`),
    INDEX `idx_status` (`status`),
    INDEX `idx_time` (`startTime`, `endTime`)
) COMMENT '优惠券表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 13. 用户优惠券表 (user_coupon)
-- ============================================
CREATE TABLE IF NOT EXISTS `user_coupon` (
    `id` BIGINT AUTO_INCREMENT COMMENT '用户优惠券ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `couponId` BIGINT NOT NULL COMMENT '优惠券ID',
    `orderId` BIGINT NULL COMMENT '使用的订单ID',
    `status` INT DEFAULT 0 NOT NULL COMMENT '状态：0-未使用 1-已使用 2-已过期',
    `receiveTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '领取时间',
    `useTime` DATETIME NULL COMMENT '使用时间',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_couponId` (`couponId`),
    INDEX `idx_status` (`status`)
) COMMENT '用户优惠券表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 14. 秒杀活动表 (seckill)
-- ============================================
CREATE TABLE IF NOT EXISTS `seckill` (
    `id` BIGINT AUTO_INCREMENT COMMENT '秒杀活动ID' PRIMARY KEY,
    `activityName` VARCHAR(128) NOT NULL COMMENT '活动名称',
    `productId` BIGINT NOT NULL COMMENT '商品ID',
    `seckillPrice` DECIMAL(10,2) NOT NULL COMMENT '秒杀价格',
    `seckillStock` INT NOT NULL COMMENT '秒杀库存',
    `remainStock` INT NOT NULL COMMENT '剩余库存',
    `limitPerUser` INT DEFAULT 1 NOT NULL COMMENT '每人限购数量',
    `startTime` DATETIME NOT NULL COMMENT '开始时间',
    `endTime` DATETIME NOT NULL COMMENT '结束时间',
    `status` INT DEFAULT 0 NOT NULL COMMENT '状态：0-未开始 1-进行中 2-已结束',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    INDEX `idx_productId` (`productId`),
    INDEX `idx_status` (`status`),
    INDEX `idx_time` (`startTime`, `endTime`)
) COMMENT '秒杀活动表' COLLATE = utf8mb4_unicode_ci;

-- ============================================
-- 15. 收藏表 (favorite)
-- ============================================
CREATE TABLE IF NOT EXISTS `favorite` (
    `id` BIGINT AUTO_INCREMENT COMMENT '收藏ID' PRIMARY KEY,
    `userId` BIGINT NOT NULL COMMENT '用户ID',
    `productId` BIGINT NOT NULL COMMENT '商品ID',
    `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    INDEX `idx_userId` (`userId`),
    INDEX `idx_productId` (`productId`),
    UNIQUE KEY `uk_user_product` (`userId`, `productId`)
) COMMENT '收藏表' COLLATE = utf8mb4_unicode_ci;