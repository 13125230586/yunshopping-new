-- ============================================
-- 云商城系统测试数据插入SQL
-- 创建日期：2026-01-02
-- 说明：包含完整的业务流程测试数据
-- ============================================

USE yunshopping;

-- ============================================
-- 1. 插入用户数据 (user)
-- ============================================
-- 密码统一为：123456（加密后：d5df7dda123e9ac01b4caade92ed2194）
INSERT INTO `user` (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole, phone, email, gender, birthday, defaultAddressId) VALUES
(1, 'admin', 'd5df7dda123e9ac01b4caade92ed2194', '系统管理员', 'https://example.com/avatar/admin.jpg', '云商城系统管理员', 'admin', '13800000000', 'admin@yunshopping.com', 1, '1990-01-01', NULL),
(2, 'buyer001', 'd5df7dda123e9ac01b4caade92ed2194', '张三', 'https://example.com/avatar/buyer1.jpg', '我是一个买家', 'buyer', '13800000001', 'zhangsan@qq.com', 1, '1995-06-15', 1),
(3, 'buyer002', 'd5df7dda123e9ac01b4caade92ed2194', '李四', 'https://example.com/avatar/buyer2.jpg', '喜欢购物的李四', 'buyer', '13800000002', 'lisi@qq.com', 2, '1998-03-20', NULL),
(4, 'seller001', 'd5df7dda123e9ac01b4caade92ed2194', '王老板', 'https://example.com/avatar/seller1.jpg', '我是卖家经营手机数码', 'seller', '13900000001', 'wanglaoban@163.com', 1, '1985-10-10', NULL),
(5, 'seller002', 'd5df7dda123e9ac01b4caade92ed2194', '刘掌柜', 'https://example.com/avatar/seller2.jpg', '我经营服装店铺', 'seller', '13900000002', 'liuzhang@163.com', 2, '1988-12-25', NULL),
(6, 'buyer003', 'd5df7dda123e9ac01b4caade92ed2194', '赵六', 'https://example.com/avatar/buyer3.jpg', NULL, 'buyer', '13800000003', 'zhaoliu@qq.com', 1, '2000-05-05', NULL),
(7, 'seller003', 'd5df7dda123e9ac01b4caade92ed2194', '孙经理', 'https://example.com/avatar/seller3.jpg', '图书文具专营', 'seller', '13900000003', 'sunjingli@163.com', 1, '1992-07-18', NULL);

-- ============================================
-- 2. 插入收货地址数据 (address)
-- ============================================
INSERT INTO `address` (id, userId, receiverName, receiverPhone, province, city, district, detailAddress, postalCode, isDefault) VALUES
(1, 2, '张三', '13800000001', '广东省', '深圳市', '南山区', '科技园南区深圳湾科技生态园10栋A座2001', '518000', 1),
(2, 2, '张三', '13800000001', '广东省', '广州市', '天河区', '天河路123号天河城6楼', '510000', 0),
(3, 3, '李四', '13800000002', '上海市', '上海市', '浦东新区', '陆家嘴环路1000号恒生银行大厦28楼', '200120', 1),
(4, 6, '赵六', '13800000003', '北京市', '北京市', '朝阳区', '建国路88号SOHO现代城A座1508', '100020', 1),
(5, 6, '赵六公司', '13800000003', '北京市', '北京市', '海淀区', '中关村大街1号海龙大厦12楼', '100080', 0);

-- 更新用户默认地址
UPDATE `user` SET defaultAddressId = 1 WHERE id = 2;
UPDATE `user` SET defaultAddressId = 3 WHERE id = 3;
UPDATE `user` SET defaultAddressId = 4 WHERE id = 6;

-- ============================================
-- 3. 插入店铺数据 (shop)
-- ============================================
INSERT INTO `shop` (id, shopName, shopLogo, shopBanner, shopDescription, shopLevel, shopStatus, userId, totalSales, totalRevenue, rating, reviewCount, province, city, reviewStatus, reviewMessage, reviewerId, reviewTime) VALUES
(1, '数码旗舰店', 'https://example.com/shop/logo1.jpg', 'https://example.com/shop/banner1.jpg', '专营各类手机、电脑、数码配件，正品保证，全国联保', 2, 1, 4, 1580, 156800.00, 4.85, 320, '广东省', '深圳市', 1, '店铺资质齐全审核通过', 1, '2025-12-01 10:00:00'),
(2, '时尚服装馆', 'https://example.com/shop/logo2.jpg', 'https://example.com/shop/banner2.jpg', '潮流女装男装童装一站式购物平台', 1, 1, 5, 3200, 128000.00, 4.92, 580, '浙江省', '杭州市', 1, '审核通过', 1, '2025-12-05 14:30:00'),
(3, '书香文具店', 'https://example.com/shop/logo3.jpg', 'https://example.com/shop/banner3.jpg', '图书教材文具办公用品专卖', 0, 1, 7, 680, 25600.00, 4.78, 120, '北京市', '北京市', 1, '审核通过', 1, '2025-12-10 09:15:00'),
(4, '待审核店铺', 'https://example.com/shop/logo4.jpg', NULL, '这是一家待审核的店铺', 0, 0, 5, 0, 0.00, 5.00, 0, '上海市', '上海市', 0, NULL, NULL, NULL);

-- ============================================
-- 4. 插入商品分类数据 (category)
-- ============================================
INSERT INTO `category` (id, categoryName, parentId, level, sortOrder, icon) VALUES
-- 一级分类
(1, '手机数码', 0, 1, 1, 'https://example.com/icon/phone.png'),
(2, '服装鞋包', 0, 1, 2, 'https://example.com/icon/cloth.png'),
(3, '图书音像', 0, 1, 3, 'https://example.com/icon/book.png'),
(4, '家用电器', 0, 1, 4, 'https://example.com/icon/appliance.png'),
(5, '食品生鲜', 0, 1, 5, 'https://example.com/icon/food.png'),
-- 二级分类 - 手机数码
(11, '手机', 1, 2, 1, NULL),
(12, '电脑办公', 1, 2, 2, NULL),
(13, '数码配件', 1, 2, 3, NULL),
-- 二级分类 - 服装鞋包
(21, '女装', 2, 2, 1, NULL),
(22, '男装', 2, 2, 2, NULL),
(23, '鞋靴', 2, 2, 3, NULL),
-- 二级分类 - 图书音像
(31, '图书', 3, 2, 1, NULL),
(32, '文具', 3, 2, 2, NULL),
-- 三级分类 - 手机
(111, 'iPhone', 11, 3, 1, NULL),
(112, '华为手机', 11, 3, 2, NULL),
(113, '小米手机', 11, 3, 3, NULL);

-- ============================================
-- 5. 插入商品数据 (product)
-- ============================================
INSERT INTO `product` (id, productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, reviewMessage, reviewerId, reviewTime, userId, viewCount, favoriteCount) VALUES
-- 店铺1的商品（数码旗舰店）
(1, 'iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max 256GB 深空黑色 5G手机', '钛金属设计A17 Pro芯片支持卫星通信全新的相机系统', 111, 1, 'Apple', 'https://example.com/product/iphone15.jpg', '["https://example.com/product/iphone15_1.jpg","https://example.com/product/iphone15_2.jpg"]', 9999.00, 10999.00, 50, 128, '台', 0.22, '["5G","A17芯片","钛金属"]', '{"颜色":["深空黑","原色钛金","白色钛金"],"内存":["256GB","512GB","1TB"]}', 1, 1, '审核通过', 1, '2025-12-15 10:00:00', 4, 1580, 96),
(2, '华为Mate 60 Pro', '华为Mate 60 Pro 12GB+512GB 雅川青 5G手机', '全新一代卫星通信技术昆仑玻璃超可靠玄武架构', 112, 1, '华为', 'https://example.com/product/mate60.jpg', '["https://example.com/product/mate60_1.jpg"]', 6999.00, 7999.00, 80, 256, '台', 0.21, '["5G","卫星通信","昆仑玻璃"]', '{"颜色":["雅川青","雅丹黑","白沙银"],"内存":["256GB","512GB","1TB"]}', 1, 1, '审核通过', 1, '2025-12-16 11:00:00', 4, 2340, 152),
(3, '小米14 Pro', '小米14 Pro 16GB+512GB 钛金属 骁龙8 Gen3', '第三代骊龙技术平台徕卡光学全焦段影像120W澎湃秒充', 113, 1, '小米', 'https://example.com/product/mi14.jpg', '["https://example.com/product/mi14_1.jpg"]', 4999.00, 5499.00, 120, 380, '台', 0.22, '["骁龙8Gen3","徕卡镜头","120W快充"]', '{"颜色":["钛金属","岩石青","陶瓷黑"],"内存":["256GB","512GB","1TB"]}', 1, 1, '审核通过', 1, '2025-12-17 09:00:00', 4, 3200, 210),
(4, 'AirPods Pro 2', 'Apple AirPods Pro 第二代 无线蓝牙耳机', 'H2芯片主动降噪自适应音频通透模式', 13, 1, 'Apple', 'https://example.com/product/airpods.jpg', '["https://example.com/product/airpods_1.jpg"]', 1899.00, 1999.00, 200, 560, '副', 0.05, '["主动降噪","H2芯片","无线充电"]', '{"颜色":["白色"]}', 1, 1, '审核通过', 1, '2025-12-18 10:00:00', 4, 1200, 88),

-- 店铺2的商品（时尚服装馆）
(5, '羽绒服女中长款', '2025冬季新款韩版修身显瘦加厚保暖羽绒服', '90%白鸭绒高品质面料防风防水保暖舒适', 21, 2, '自营品牌', 'https://example.com/product/down_jacket.jpg', '["https://example.com/product/down_jacket_1.jpg","https://example.com/product/down_jacket_2.jpg"]', 699.00, 1299.00, 150, 420, '件', 0.8, '["羽绒服","保暖","韩版"]', '{"颜色":["黑色","米白","玫红"],"尺码":["S","M","L","XL","XXL"]}', 1, 1, '审核通过', 1, '2025-12-12 14:00:00', 5, 2600, 180),
(6, '男士商务衬衫', '纯棉免烫长袖衬衫男士商务正装修身衬衣', '精选新疆长绒棉免烫工艺商务百搭', 22, 2, '雅戈尔', 'https://example.com/product/shirt.jpg', '["https://example.com/product/shirt_1.jpg"]', 299.00, 499.00, 300, 680, '件', 0.3, '["纯棉","免烫","商务"]', '{"颜色":["白色","浅蓝","浅粉"],"尺码":["38","39","40","41","42","43"]}', 1, 1, '审核通过', 1, '2025-12-13 15:00:00', 5, 1800, 95),
(7, '运动鞋男款', '2025新款透气网面跑步鞋减震运动休闲鞋', '飞织鞋面透气舒适MD中底减震耐磨橡胶大底', 23, 2, '耐克', 'https://example.com/product/shoes.jpg', '["https://example.com/product/shoes_1.jpg"]', 459.00, 699.00, 200, 340, '双', 0.6, '["运动鞋","透气","减震"]', '{"颜色":["黑色","白色","灰色"],"尺码":["39","40","41","42","43","44"]}', 1, 1, '审核通过', 1, '2025-12-14 16:00:00', 5, 1500, 72),

-- 店铺3的商品（书香文具店）
(8, 'Python编程从入门到精通', 'Python编程从入门到精通第2版零基础自学教程', '适合零基础读者配套视频教程案例丰富', 31, 3, '清华大学出版社', 'https://example.com/product/python_book.jpg', '["https://example.com/product/python_book_1.jpg"]', 89.00, 129.00, 500, 260, '本', 0.5, '["编程","Python","入门教程"]', '{"版本":["纸质版","电子版"]}', 1, 1, '审核通过', 1, '2025-12-19 10:00:00', 7, 680, 45),
(9, '晨光文具套装', '晨光学生文具套装笔记本铅笔橡皮尺子', '学生必备文具套装品质保证', 32, 3, '晨光', 'https://example.com/product/stationery.jpg', '["https://example.com/product/stationery_1.jpg"]', 39.90, 59.90, 800, 520, '套', 0.3, '["文具","学生用品","套装"]', '{"颜色":["蓝色","粉色","绿色"]}', 1, 1, '审核通过', 1, '2025-12-20 11:00:00', 7, 920, 38),

-- 待审核商品
(10, '待审核商品测试', '这是一个待审核的测试商品', '待审核商品描述', 21, 2, '测试品牌', 'https://example.com/product/test.jpg', NULL, 99.00, 199.00, 100, 0, '件', 0.5, '["测试"]', NULL, 0, 0, NULL, NULL, NULL, 5, 10, 0);

-- ============================================
-- 6. 插入购物车数据 (cart)
-- ============================================
INSERT INTO `cart` (id, userId, productId, quantity, specification, isChecked) VALUES
(1, 2, 1, 1, '{"颜色":"深空黑","内存":"256GB"}', 1),
(2, 2, 4, 2, '{"颜色":"白色"}', 1),
(3, 3, 5, 1, '{"颜色":"黑色","尺码":"M"}', 1),
(4, 3, 8, 3, '{"版本":"纸质版"}', 0),
(5, 6, 2, 1, '{"颜色":"雅川青","内存":"512GB"}', 1);

-- ============================================
-- 7. 插入订单数据 (orders)
-- ============================================
INSERT INTO `orders` (id, orderNo, userId, shopId, totalAmount, payAmount, discountAmount, shippingFee, paymentMethod, orderStatus, paymentStatus, shippingStatus, receiverName, receiverPhone, receiverAddress, buyerMessage, payTime, shipTime, completeTime, cancelTime) VALUES
-- 已完成订单
(1, 'ORDER202512200001', 2, 1, 9999.00, 9989.00, 10.00, 0.00, 'alipay', 4, 1, 2, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', '尽快发货谢谢', '2025-12-20 14:30:00', '2025-12-21 09:00:00', '2025-12-25 16:00:00', NULL),
(2, 'ORDER202512210001', 3, 2, 699.00, 699.00, 0.00, 0.00, 'wxpay', 4, 1, 2, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', NULL, '2025-12-21 10:15:00', '2025-12-21 15:00:00', '2025-12-26 18:30:00', NULL),

-- 已发货订单
(3, 'ORDER202512280001', 2, 2, 299.00, 299.00, 0.00, 0.00, 'alipay', 3, 1, 1, '张三', '13800000001', '广东省广州市天河区天河路123号天河城6楼', '请放快递柜', '2025-12-28 11:00:00', '2025-12-28 16:00:00', NULL, NULL),

-- 待发货订单
(4, 'ORDER202512300001', 6, 1, 6999.00, 6999.00, 0.00, 0.00, 'alipay', 2, 1, 0, '赵六', '13800000003', '北京市北京市朝阳区建国路88号SOHO现代城A座1508', '上午送货', '2025-12-30 09:30:00', NULL, NULL, NULL),

-- 待支付订单
(5, 'ORDER202512310001', 3, 3, 89.00, 89.00, 0.00, 0.00, NULL, 0, 0, 0, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', NULL, NULL, NULL, NULL, NULL),

-- 已取消订单
(6, 'ORDER202512250001', 2, 1, 1899.00, 1899.00, 0.00, 0.00, NULL, 5, 0, 0, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', NULL, NULL, NULL, NULL, '2025-12-26 10:00:00');

-- ============================================
-- 8. 插入订单详情数据 (order_item)
-- ============================================
INSERT INTO `order_item` (id, orderId, productId, productName, productImage, price, quantity, totalAmount, specification) VALUES
-- 订单1的详情
(1, 1, 1, 'iPhone 15 Pro Max', 'https://example.com/product/iphone15.jpg', 9999.00, 1, 9999.00, '{"颜色":"深空黑","内存":"256GB"}'),

-- 订单2的详情
(2, 2, 5, '羽绒服女中长款', 'https://example.com/product/down_jacket.jpg', 699.00, 1, 699.00, '{"颜色":"黑色","尺码":"M"}'),

-- 订单3的详情
(3, 3, 6, '男士商务衬衫', 'https://example.com/product/shirt.jpg', 299.00, 1, 299.00, '{"颜色":"白色","尺码":"40"}'),

-- 订单4的详情
(4, 4, 2, '华为Mate 60 Pro', 'https://example.com/product/mate60.jpg', 6999.00, 1, 6999.00, '{"颜色":"雅川青","内存":"512GB"}'),

-- 订单5的详情
(5, 5, 8, 'Python编程从入门到精通', 'https://example.com/product/python_book.jpg', 89.00, 1, 89.00, '{"版本":"纸质版"}'),

-- 订单6的详情
(6, 6, 4, 'AirPods Pro 2', 'https://example.com/product/airpods.jpg', 1899.00, 1, 1899.00, '{"颜色":"白色"}');

-- ============================================
-- 9. 插入支付记录数据 (payment)
-- ============================================
INSERT INTO `payment` (id, paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime) VALUES
(1, 'PAY202512200001', 1, 2, 'alipay', 9989.00, 1, '2025122022001001234567890', '2025-12-20 14:30:00', NULL),
(2, 'PAY202512210001', 2, 3, 'wxpay', 699.00, 1, '4200001234202512210123456789', '2025-12-21 10:15:00', NULL),
(3, 'PAY202512280001', 3, 2, 'alipay', 299.00, 1, '2025122822001001234567891', '2025-12-28 11:00:00', NULL),
(4, 'PAY202512300001', 4, 6, 'alipay', 6999.00, 1, '2025123022001001234567892', '2025-12-30 09:30:00', NULL);

-- ============================================
-- 10. 插入物流数据 (logistics)
-- ============================================
INSERT INTO `logistics` (id, orderId, logisticsCompany, trackingNumber, logisticsStatus, currentLocation, logisticsInfo, shipTime, receiveTime) VALUES
-- 已签收订单的物流
(1, 1, '顺丰速运', 'SF1234567890123', 3, '广东省深圳市南山区', '[{"time":"2025-12-21 09:00:00","status":"已发货","location":"广东省深圳市"},{"time":"2025-12-22 15:00:00","status":"运输中","location":"广东省深圳市分拨中心"},{"time":"2025-12-23 08:00:00","status":"派送中","location":"南山区营业部"},{"time":"2025-12-25 16:00:00","status":"已签收","location":"客户签收"}]', '2025-12-21 09:00:00', '2025-12-25 16:00:00'),

(2, 2, '中通快递', 'ZTO9876543210123', 3, '上海市浦东新区', '[{"time":"2025-12-21 15:00:00","status":"已发货","location":"浙江省杭州市"},{"time":"2025-12-23 10:00:00","status":"运输中","location":"上海市分拨中心"},{"time":"2025-12-25 09:00:00","status":"派送中","location":"浦东新区营业部"},{"time":"2025-12-26 18:30:00","status":"已签收","location":"客户签收"}]', '2025-12-21 15:00:00', '2025-12-26 18:30:00'),

-- 派送中订单的物流
(3, 3, '圆通速递', 'YTO5678901234567', 2, '广东省广州市天河区', '[{"time":"2025-12-28 16:00:00","status":"已发货","location":"浙江省杭州市"},{"time":"2025-12-29 20:00:00","status":"运输中","location":"广东省广州市分拨中心"},{"time":"2025-12-31 08:00:00","status":"派送中","location":"天河区营业部"}]', '2025-12-28 16:00:00', NULL);

-- ============================================
-- 11. 插入商品评价数据 (review)
-- ============================================
INSERT INTO `review` (id, userId, productId, orderId, shopId, rating, content, images, isAnonymous, likeCount, replyContent, replyTime) VALUES
-- 订单1的评价
(1, 2, 1, 1, 1, 5, 'iPhone 15 Pro Max真的很棒，钛金属设计手感一流，A17 Pro芯片性能强劲，拍照效果也非常出色！物流很快，包装完好，非常满意！', '["https://example.com/review/1_1.jpg","https://example.com/review/1_2.jpg"]', 0, 15, '感谢您的好评！我们会继续努力为您提供优质的产品和服务！', '2025-12-26 10:00:00'),

-- 订单2的评价
(2, 3, 5, 2, 2, 5, '羽绒服质量很好，面料柔软，保暖效果棒，版型修身显瘦，颜色也很正，快递包装很仔细，好评！', '["https://example.com/review/2_1.jpg"]', 0, 8, '谢谢亲的支持！冬天注意保暖哦~', '2025-12-27 09:00:00'),

-- 订单1的追加评价（没有追加功能，这里作为第二条评价）
(3, 2, 4, 1, 1, 4, 'AirPods Pro降噪效果确实不错，音质也很好，但是价格有点贵，总体还是值得购买的', NULL, 0, 3, '感谢您的评价！AirPods Pro确实是高端产品，物有所值！', '2025-12-27 14:00:00'),

-- 匿名评价
(4, 6, 2, 4, 1, 5, '华为Mate 60 Pro太香了！昆仑玻璃确实耐摔，信号也比之前的手机好很多，卫星通信功能很强大！', '["https://example.com/review/4_1.jpg"]', 1, 22, NULL, NULL);

-- ============================================
-- 12. 插入优惠券数据 (coupon)
-- ============================================
INSERT INTO `coupon` (id, couponName, couponType, discountAmount, discountRate, minAmount, totalCount, usedCount, shopId, startTime, endTime, status) VALUES
-- 平台券
(1, '新人专享满100减10', 1, 10.00, NULL, 100.00, 10000, 2350, NULL, '2025-12-01 00:00:00', '2026-03-31 23:59:59', 1),
(2, '春节大促满500减50', 1, 50.00, NULL, 500.00, 5000, 680, NULL, '2026-01-20 00:00:00', '2026-02-10 23:59:59', 1),
(3, '全场8折优惠券', 2, NULL, 0.80, 200.00, 3000, 450, NULL, '2025-12-15 00:00:00', '2026-01-15 23:59:59', 1),

-- 店铺券
(4, '数码旗舰店满1000减100', 1, 100.00, NULL, 1000.00, 2000, 580, 1, '2025-12-10 00:00:00', '2026-01-31 23:59:59', 1),
(5, '时尚服装馆满200减30', 1, 30.00, NULL, 200.00, 5000, 1250, 2, '2025-12-01 00:00:00', '2026-02-28 23:59:59', 1),
(6, '书香文具店无门槛10元券', 3, 10.00, NULL, 0.00, 10000, 3200, 3, '2025-12-01 00:00:00', '2026-06-30 23:59:59', 1);

-- ============================================
-- 13. 插入用户优惠券数据 (user_coupon)
-- ============================================
INSERT INTO `user_coupon` (id, userId, couponId, orderId, status, receiveTime, useTime) VALUES
-- 已使用
(1, 2, 1, 1, 1, '2025-12-15 10:00:00', '2025-12-20 14:30:00'),
(2, 3, 5, 2, 1, '2025-12-18 15:00:00', '2025-12-21 10:15:00'),

-- 未使用
(3, 2, 4, NULL, 0, '2025-12-20 16:00:00', NULL),
(4, 3, 1, NULL, 0, '2025-12-22 09:00:00', NULL),
(5, 6, 3, NULL, 0, '2025-12-25 11:00:00', NULL),
(6, 2, 6, NULL, 0, '2025-12-28 14:00:00', NULL),

-- 已过期
(7, 2, 3, NULL, 2, '2025-11-01 10:00:00', NULL);

-- ============================================
-- 14. 插入秒杀活动数据 (seckill)
-- ============================================
INSERT INTO `seckill` (id, activityName, productId, seckillPrice, seckillStock, remainStock, limitPerUser, startTime, endTime, status) VALUES
-- 进行中的秒杀
(1, '元旦秒杀-iPhone15特惠', 1, 8888.00, 100, 35, 1, '2026-01-01 10:00:00', '2026-01-01 22:00:00', 1),
(2, '每日秒杀-羽绒服限时抢', 5, 499.00, 200, 88, 2, '2026-01-02 00:00:00', '2026-01-02 23:59:59', 1),

-- 未开始的秒杀
(3, '周末特惠-华为手机', 2, 5999.00, 150, 150, 1, '2026-01-04 10:00:00', '2026-01-05 22:00:00', 0),

-- 已结束的秒杀
(4, '圣诞秒杀-AirPods特价', 4, 1599.00, 300, 0, 1, '2025-12-25 10:00:00', '2025-12-25 22:00:00', 2);

-- ============================================
-- 15. 插入收藏数据 (favorite)
-- ============================================
INSERT INTO `favorite` (id, userId, productId) VALUES
(1, 2, 2),  -- 张三收藏华为手机
(2, 2, 3),  -- 张三收藏小米手机
(3, 2, 5),  -- 张三收藏羽绒服
(4, 3, 1),  -- 李四收藏iPhone
(5, 3, 7),  -- 李四收藏运动鞋
(6, 3, 9),  -- 李四收藏文具套装
(7, 6, 1),  -- 赵六收藏iPhone
(8, 6, 8);  -- 赵六收藏Python书籍

-- ============================================
-- 数据插入完成
-- ============================================

-- 查询统计信息
SELECT '用户数据统计' AS 统计项, COUNT(*) AS 数量 FROM user
UNION ALL
SELECT '店铺数据统计', COUNT(*) FROM shop
UNION ALL
SELECT '商品数据统计', COUNT(*) FROM product
UNION ALL
SELECT '订单数据统计', COUNT(*) FROM orders
UNION ALL
SELECT '评价数据统计', COUNT(*) FROM review;