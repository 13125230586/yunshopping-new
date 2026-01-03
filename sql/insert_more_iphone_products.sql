-- ===================================================================
-- iPhone 分类补充商品数据（20个）
-- 说明：增加更多 iPhone 型号和配置，丰富商品选择
-- ===================================================================

USE yunshopping;

SET @iphone_category_id = (SELECT id FROM category WHERE categoryName = 'iPhone' AND level = 3 LIMIT 1);

-- 补充 20 个 iPhone 商品
INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
-- iPhone 15 系列其他配置
('iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max 512GB 黑色钛金属', 'A17 Pro芯片，512GB大容量，专业摄影系统', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15promax-512.jpg', '["https://img.example.com/iphone15promax-512-1.jpg"]', 11999.00, 12999.00, 80, 420, '台', 0.25, '["5G","大容量","钛金属"]', '{"颜色":["黑色钛金属","原色钛金属","蓝色钛金属"],"存储":["512GB","1TB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max 1TB 蓝色钛金属', 'A17 Pro芯片，1TB顶配，4K ProRes视频录制', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15promax-1tb.jpg', '["https://img.example.com/iphone15promax-1tb-1.jpg"]', 13999.00, 14999.00, 50, 180, '台', 0.25, '["5G","顶配","专业"]', '{"颜色":["蓝色钛金属","黑色钛金属","白色钛金属"],"存储":["1TB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Pro', 'Apple iPhone 15 Pro 256GB 白色钛金属', 'A17 Pro芯片，256GB存储，轻巧便携', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15pro-256.jpg', '["https://img.example.com/iphone15pro-256-1.jpg"]', 8999.00, 9999.00, 120, 580, '台', 0.22, '["5G","Pro","轻薄"]', '{"颜色":["白色钛金属","黑色钛金属","原色钛金属"],"存储":["256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Pro', 'Apple iPhone 15 Pro 512GB 原色钛金属', 'A17 Pro芯片，512GB大容量，长焦镜头', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15pro-512.jpg', '["https://img.example.com/iphone15pro-512-1.jpg"]', 10999.00, 11999.00, 90, 380, '台', 0.22, '["5G","大容量","长焦"]', '{"颜色":["原色钛金属","蓝色钛金属"],"存储":["512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Plus', 'Apple iPhone 15 Plus 128GB 黑色', '6.7英寸大屏，A16芯片，双摄系统', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15plus-128.jpg', '["https://img.example.com/iphone15plus-128-1.jpg"]', 6999.00, 7499.00, 150, 720, '台', 0.23, '["5G","大屏","长续航"]', '{"颜色":["黑色","粉色","蓝色","绿色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Plus', 'Apple iPhone 15 Plus 256GB 蓝色', '6.7英寸超视网膜XDR显示屏，4800万像素', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15plus-256.jpg', '["https://img.example.com/iphone15plus-256-1.jpg"]', 7999.00, 8499.00, 130, 620, '台', 0.23, '["5G","大屏","摄影"]', '{"颜色":["蓝色","黑色","粉色"],"存储":["256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15', 'Apple iPhone 15 256GB 绿色', '灵动岛，A16芯片，256GB存储', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15-256.jpg', '["https://img.example.com/iphone15-256-1.jpg"]', 6999.00, 7499.00, 180, 1100, '台', 0.20, '["5G","灵动岛","中杯"]', '{"颜色":["绿色","粉色","黄色","蓝色"],"存储":["256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15', 'Apple iPhone 15 512GB 黄色', 'A16芯片，512GB大容量，超长续航', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15-512.jpg', '["https://img.example.com/iphone15-512-1.jpg"]', 8499.00, 8999.00, 100, 450, '台', 0.20, '["5G","大容量","时尚"]', '{"颜色":["黄色","粉色","黑色"],"存储":["512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

-- iPhone 14 系列其他配置
('iPhone 14 Pro Max', 'Apple iPhone 14 Pro Max 256GB 深空黑色', 'A16芯片，灵动岛，4800万像素主摄', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14promax-256.jpg', '["https://img.example.com/iphone14promax-256-1.jpg"]', 8999.00, 9999.00, 100, 680, '台', 0.24, '["5G","Pro Max","灵动岛"]', '{"颜色":["深空黑色","银色","金色","暗紫色"],"存储":["256GB","512GB","1TB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Pro Max', 'Apple iPhone 14 Pro Max 512GB 暗紫色', 'A16芯片，512GB存储，ProRAW照片', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14promax-512.jpg', '["https://img.example.com/iphone14promax-512-1.jpg"]', 10999.00, 11999.00, 80, 520, '台', 0.24, '["5G","专业","暗紫色"]', '{"颜色":["暗紫色","深空黑色","金色"],"存储":["512GB","1TB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Pro', 'Apple iPhone 14 Pro 128GB 深空黑色', 'A16芯片，灵动岛，专业相机系统', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14pro-128.jpg', '["https://img.example.com/iphone14pro-128-1.jpg"]', 7999.00, 8999.00, 110, 780, '台', 0.21, '["5G","Pro","轻便"]', '{"颜色":["深空黑色","银色","金色","暗紫色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Pro', 'Apple iPhone 14 Pro 256GB 金色', 'A16芯片，256GB存储，ProMotion自适应刷新率', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14pro-256.jpg', '["https://img.example.com/iphone14pro-256-1.jpg"]', 8999.00, 9999.00, 95, 650, '台', 0.21, '["5G","ProMotion","金色"]', '{"颜色":["金色","银色","暗紫色"],"存储":["256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Plus', 'Apple iPhone 14 Plus 128GB 蓝色', '6.7英寸大屏，A15芯片，全天续航', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14plus-128.jpg', '["https://img.example.com/iphone14plus-128-1.jpg"]', 6399.00, 6999.00, 140, 820, '台', 0.23, '["5G","大屏","续航"]', '{"颜色":["蓝色","紫色","午夜色","星光色","红色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Plus', 'Apple iPhone 14 Plus 512GB 红色', 'A15芯片，512GB大容量，经典配色', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14plus-512.jpg', '["https://img.example.com/iphone14plus-512-1.jpg"]', 8399.00, 8999.00, 70, 380, '台', 0.23, '["5G","红色","大容量"]', '{"颜色":["红色","午夜色","蓝色"],"存储":["512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14', 'Apple iPhone 14 256GB 星光色', 'A15芯片，256GB存储，双摄系统', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14-256.jpg', '["https://img.example.com/iphone14-256-1.jpg"]', 6399.00, 6999.00, 150, 1050, '台', 0.19, '["5G","经典","星光色"]', '{"颜色":["星光色","午夜色","蓝色","紫色","红色"],"存储":["256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14', 'Apple iPhone 14 512GB 紫色', 'A15芯片，512GB大容量，时尚紫色', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14-512.jpg', '["https://img.example.com/iphone14-512-1.jpg"]', 7399.00, 7999.00, 85, 520, '台', 0.19, '["5G","紫色","大容量"]', '{"颜色":["紫色","午夜色","星光色"],"存储":["512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

-- iPhone 13 系列
('iPhone 13 Pro Max', 'Apple iPhone 13 Pro Max 256GB 远峰蓝色', 'A15芯片，超视网膜XDR显示屏，三摄系统', @iphone_category_id, 2, 'Apple', 'https://img.example.com/iphone13promax-256.jpg', '["https://img.example.com/iphone13promax-256-1.jpg"]', 7999.00, 8999.00, 90, 650, '台', 0.24, '["5G","Pro Max","远峰蓝"]', '{"颜色":["远峰蓝色","石墨色","金色","银色"],"存储":["256GB","512GB","1TB"]}', 1, 1, 3, NOW(), NOW(), 0),

('iPhone 13 Pro', 'Apple iPhone 13 Pro 128GB 苍岭绿色', 'A15芯片，ProMotion技术，120Hz刷新率', @iphone_category_id, 2, 'Apple', 'https://img.example.com/iphone13pro-128.jpg', '["https://img.example.com/iphone13pro-128-1.jpg"]', 6999.00, 7999.00, 100, 720, '台', 0.21, '["5G","苍岭绿","120Hz"]', '{"颜色":["苍岭绿色","远峰蓝色","金色","石墨色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('iPhone 13', 'Apple iPhone 13 128GB 粉色', 'A15芯片，双摄系统，超长续航', @iphone_category_id, 2, 'Apple', 'https://img.example.com/iphone13-128.jpg', '["https://img.example.com/iphone13-128-1.jpg"]', 4999.00, 5499.00, 200, 1850, '台', 0.19, '["5G","经典","粉色"]', '{"颜色":["粉色","蓝色","午夜色","星光色","红色","绿色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('iPhone 13 mini', 'Apple iPhone 13 mini 128GB 星光色', '小屏旗舰，A15芯片，单手操作', @iphone_category_id, 2, 'Apple', 'https://img.example.com/iphone13mini-128.jpg', '["https://img.example.com/iphone13mini-128-1.jpg"]', 4499.00, 4999.00, 150, 980, '台', 0.14, '["5G","小屏","轻便"]', '{"颜色":["星光色","午夜色","粉色","蓝色","红色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 3, NOW(), NOW(), 0);

-- 查询结果统计
SELECT
    '补充完成！' AS 提示,
    (SELECT COUNT(*) FROM product WHERE categoryId = @iphone_category_id) AS iPhone商品总数,
    (SELECT SUM(stock) FROM product WHERE categoryId = @iphone_category_id) AS 总库存,
    (SELECT SUM(sales) FROM product WHERE categoryId = @iphone_category_id) AS 总销量;

-- 查看 iPhone 各型号统计
SELECT
    SUBSTRING_INDEX(productName, ' ', 3) AS 型号,
    COUNT(*) AS 商品数量,
    MIN(price) AS 最低价,
    MAX(price) AS 最高价,
    SUM(stock) AS 库存,
    SUM(sales) AS 销量
FROM product
WHERE categoryId = @iphone_category_id
GROUP BY SUBSTRING_INDEX(productName, ' ', 3)
ORDER BY 销量 DESC;