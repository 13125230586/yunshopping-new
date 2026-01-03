-- ===================================================================
-- 清空所有表数据（保留表结构）
-- 注意：此操作会删除所有数据，请谨慎使用！
-- 使用场景：测试环境重置数据
-- ===================================================================
USE yunshopping;

-- 临时禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 清空所有表数据（按照外键依赖顺序）

-- 1. 清空订单相关表
TRUNCATE TABLE order_item;


-- 2. 清空支付表
TRUNCATE TABLE payment;

-- 3. 清空购物车表
TRUNCATE TABLE cart;

-- 4. 清空收藏表
TRUNCATE TABLE favorite;

-- 5. 清空评价表
TRUNCATE TABLE review;

-- 6. 清空优惠券相关表
TRUNCATE TABLE user_coupon;
TRUNCATE TABLE coupon;

-- 7. 清空商品表
TRUNCATE TABLE product;

-- 8. 清空分类表
TRUNCATE TABLE category;

-- 9. 清空店铺表
TRUNCATE TABLE shop;

-- 10. 清空收货地址表
TRUNCATE TABLE address;

-- 11. 清空用户表
TRUNCATE TABLE user;

-- 重新启用外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 显示清空结果
SELECT '所有表已清空，可以执行 insert_test_data.sql 插入测试数据' AS result;