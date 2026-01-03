-- ============================================
-- 云商城系统订单补充测试数据
-- 创建日期：2026-01-03
-- 说明：增加更多订单数据用于管理后台展示
-- ============================================

USE yunshopping;

-- ============================================
-- 插入补充订单数据 (orders)
-- ============================================
INSERT INTO `orders` (orderNo, userId, shopId, totalAmount, payAmount, discountAmount, shippingFee, paymentMethod, orderStatus, paymentStatus, shippingStatus, receiverName, receiverPhone, receiverAddress, buyerMessage, payTime, shipTime, completeTime, cancelTime) VALUES
-- 已完成订单
('ORDER202512220001', 2, 3, 267.00, 267.00, 0.00, 0.00, 'alipay', 4, 1, 2, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', NULL, '2025-12-22 10:00:00', '2025-12-22 15:00:00', '2025-12-27 10:00:00', NULL),
('ORDER202512230001', 3, 1, 4999.00, 4999.00, 0.00, 0.00, 'wxpay', 4, 1, 2, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', '请放前台', '2025-12-23 14:20:00', '2025-12-24 09:00:00', '2025-12-28 15:30:00', NULL),
('ORDER202512240001', 6, 2, 459.00, 459.00, 0.00, 0.00, 'alipay', 4, 1, 2, '赵六', '13800000003', '北京市北京市朝阳区建国路88号SOHO现代城A座1508', NULL, '2025-12-24 16:00:00', '2025-12-25 10:00:00', '2025-12-29 18:00:00', NULL),
('ORDER202512260001', 2, 2, 998.00, 968.00, 30.00, 0.00, 'alipay', 4, 1, 2, '张三', '13800000001', '广东省广州市天河区天河路123号天河城6楼', '请快递柜', '2025-12-26 11:30:00', '2025-12-26 16:00:00', '2025-12-30 14:00:00', NULL),
('ORDER202512270001', 3, 3, 119.70, 119.70, 0.00, 0.00, 'wxpay', 4, 1, 2, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', NULL, '2025-12-27 09:45:00', '2025-12-27 14:00:00', '2026-01-01 11:00:00', NULL),

-- 已发货订单
('ORDER202512290001', 6, 1, 1899.00, 1899.00, 0.00, 0.00, 'wxpay', 3, 1, 1, '赵六公司', '13800000003', '北京市北京市海淀区中关村大街1号海龙大厦12楼', '工作日配送', '2025-12-29 10:00:00', '2025-12-29 15:30:00', NULL, NULL),
('ORDER202512310002', 2, 2, 699.00, 699.00, 0.00, 0.00, 'alipay', 3, 1, 1, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', NULL, '2025-12-31 14:00:00', '2025-12-31 18:00:00', NULL, NULL),
('ORDER202601010001', 3, 1, 6999.00, 6999.00, 0.00, 0.00, 'wxpay', 3, 1, 1, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', '请提前电联', '2026-01-01 11:20:00', '2026-01-01 16:00:00', NULL, NULL),

-- 待发货订单
('ORDER202601020001', 2, 3, 89.00, 89.00, 0.00, 0.00, 'alipay', 2, 1, 0, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', NULL, '2026-01-02 09:00:00', NULL, NULL, NULL),
('ORDER202601020002', 6, 2, 299.00, 299.00, 0.00, 0.00, 'wxpay', 2, 1, 0, '赵六', '13800000003', '北京市北京市朝阳区建国路88号SOHO现代城A座1508', '尽快发货', '2026-01-02 13:30:00', NULL, NULL, NULL),
('ORDER202601030001', 3, 1, 9999.00, 9999.00, 0.00, 0.00, 'alipay', 2, 1, 0, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', NULL, '2026-01-03 10:15:00', NULL, NULL, NULL),

-- 待支付订单
('ORDER202601020003', 2, 1, 4999.00, 4999.00, 0.00, 0.00, NULL, 0, 0, 0, '张三', '13800000001', '广东省深圳市南山区科技园南区深圳湾科技生态园10栋A座2001', NULL, NULL, NULL, NULL, NULL),
('ORDER202601030002', 6, 3, 39.90, 39.90, 0.00, 0.00, NULL, 0, 0, 0, '赵六', '13800000003', '北京市北京市朝阳区建国路88号SOHO现代城A座1508', NULL, NULL, NULL, NULL, NULL),

-- 已取消订单
('ORDER202512270002', 3, 2, 459.00, 459.00, 0.00, 0.00, NULL, 5, 0, 0, '李四', '13800000002', '上海市上海市浦东新区陆家嘴环路1000号恒生银行大厦28楼', NULL, NULL, NULL, NULL, '2025-12-28 09:00:00'),
('ORDER202512290002', 2, 3, 267.00, 267.00, 0.00, 0.00, NULL, 5, 0, 0, '张三', '13800000001', '广东省广州市天河区天河路123号天河城6楼', '取消不要了', NULL, NULL, NULL, '2025-12-30 10:00:00');

-- ============================================
-- 插入订单详情数据 (order_item)
-- ============================================
-- 获取最新插入的订单ID需要根据实际情况调整，这里使用订单号关联

-- 订单 ORDER202512220001 的详情（3本书）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 8, 'Python编程从入门到精通', 'https://example.com/product/python_book.jpg', 89.00, 3, 267.00, '{"版本":"纸质版"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512220001';

-- 订单 ORDER202512230001 的详情（小米手机）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 3, '小米14 Pro', 'https://example.com/product/mi14.jpg', 4999.00, 1, 4999.00, '{"颜色":"钛金属","内存":"512GB"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512230001';

-- 订单 ORDER202512240001 的详情（运动鞋）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 7, '运动鞋男款', 'https://example.com/product/shoes.jpg', 459.00, 1, 459.00, '{"颜色":"黑色","尺码":"42"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512240001';

-- 订单 ORDER202512260001 的详情（羽绒服2件）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 5, '羽绒服女中长款', 'https://example.com/product/down_jacket.jpg', 699.00, 2, 1398.00, '{"颜色":"米白","尺码":"M"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512260001';

-- 订单 ORDER202512270001 的详情（文具套装3套）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 9, '晨光文具套装', 'https://example.com/product/stationery.jpg', 39.90, 3, 119.70, '{"颜色":"蓝色"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512270001';

-- 订单 ORDER202512290001 的详情（AirPods）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 4, 'AirPods Pro 2', 'https://example.com/product/airpods.jpg', 1899.00, 1, 1899.00, '{"颜色":"白色"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512290001';

-- 订单 ORDER202512310002 的详情（羽绒服）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 5, '羽绒服女中长款', 'https://example.com/product/down_jacket.jpg', 699.00, 1, 699.00, '{"颜色":"黑色","尺码":"L"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512310002';

-- 订单 ORDER202601010001 的详情（华为手机）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 2, '华为Mate 60 Pro', 'https://example.com/product/mate60.jpg', 6999.00, 1, 6999.00, '{"颜色":"雅丹黑","内存":"512GB"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601010001';

-- 订单 ORDER202601020001 的详情（Python书籍）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 8, 'Python编程从入门到精通', 'https://example.com/product/python_book.jpg', 89.00, 1, 89.00, '{"版本":"纸质版"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601020001';

-- 订单 ORDER202601020002 的详情（衬衫）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 6, '男士商务衬衫', 'https://example.com/product/shirt.jpg', 299.00, 1, 299.00, '{"颜色":"浅蓝","尺码":"41"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601020002';

-- 订单 ORDER202601030001 的详情（iPhone）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 1, 'iPhone 15 Pro Max', 'https://example.com/product/iphone15.jpg', 9999.00, 1, 9999.00, '{"颜色":"原色钛金","内存":"512GB"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601030001';

-- 订单 ORDER202601020003 的详情（小米手机）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 3, '小米14 Pro', 'https://example.com/product/mi14.jpg', 4999.00, 1, 4999.00, '{"颜色":"岩石青","内存":"256GB"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601020003';

-- 订单 ORDER202601030002 的详情（文具套装）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 9, '晨光文具套装', 'https://example.com/product/stationery.jpg', 39.90, 1, 39.90, '{"颜色":"粉色"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202601030002';

-- 订单 ORDER202512270002 的详情（运动鞋）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 7, '运动鞋男款', 'https://example.com/product/shoes.jpg', 459.00, 1, 459.00, '{"颜色":"白色","尺码":"43"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512270002';

-- 订单 ORDER202512290002 的详情（书籍）
INSERT INTO `order_item` (orderId, productId, productName, productImage, price, quantity, totalAmount, specification)
SELECT o.id, 8, 'Python编程从入门到精通', 'https://example.com/product/python_book.jpg', 89.00, 3, 267.00, '{"版本":"纸质版"}'
FROM `orders` o WHERE o.orderNo = 'ORDER202512290002';

-- ============================================
-- 插入支付记录数据 (payment)
-- ============================================
-- 已支付订单的支付记录
INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512220001', o.id, 2, 'alipay', 267.00, 1, '2025122222001001234567893', '2025-12-22 10:00:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512220001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512230001', o.id, 3, 'wxpay', 4999.00, 1, '4200001234202512230123456790', '2025-12-23 14:20:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512230001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512240001', o.id, 6, 'alipay', 459.00, 1, '2025122422001001234567894', '2025-12-24 16:00:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512240001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512260001', o.id, 2, 'alipay', 968.00, 1, '2025122622001001234567895', '2025-12-26 11:30:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512260001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512270001', o.id, 3, 'wxpay', 119.70, 1, '4200001234202512270123456791', '2025-12-27 09:45:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512270001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512290001', o.id, 6, 'wxpay', 1899.00, 1, '4200001234202512290123456792', '2025-12-29 10:00:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512290001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202512310002', o.id, 2, 'alipay', 699.00, 1, '2025123122001001234567896', '2025-12-31 14:00:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202512310002';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202601010001', o.id, 3, 'wxpay', 6999.00, 1, '4200001234202601010123456793', '2026-01-01 11:20:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202601010001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202601020001', o.id, 2, 'alipay', 89.00, 1, '2026010222001001234567897', '2026-01-02 09:00:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202601020001';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202601020002', o.id, 6, 'wxpay', 299.00, 1, '4200001234202601020123456794', '2026-01-02 13:30:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202601020002';

INSERT INTO `payment` (paymentNo, orderId, userId, paymentMethod, payAmount, payStatus, transactionId, payTime, refundTime)
SELECT 'PAY202601030001', o.id, 3, 'alipay', 9999.00, 1, '2026010322001001234567898', '2026-01-03 10:15:00', NULL
FROM `orders` o WHERE o.orderNo = 'ORDER202601030001';

-- ============================================
-- 数据插入完成
-- ============================================
SELECT '补充订单数据插入完成，新增订单数：' AS 提示, COUNT(*) AS 订单数 FROM `orders` WHERE orderNo LIKE 'ORDER20251222%' OR orderNo LIKE 'ORDER202601%';