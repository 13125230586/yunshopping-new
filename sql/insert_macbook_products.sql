-- ===================================================================
-- MacBook Pro 笔记本电脑商品数据（20条）
-- 说明：增加多款 MacBook Pro 型号和配置，丰富电脑办公分类商品
-- ===================================================================

USE yunshopping;

SET @notebook_category_id = (SELECT id FROM category WHERE categoryName = '笔记本电脑' AND level = 3 LIMIT 1);

-- 插入 20 个 MacBook Pro 商品
INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
-- MacBook Pro 14英寸系列
('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3芯片 8核CPU 10核GPU 8GB 512GB 深空灰色', '全新M3芯片，液态视网膜XDR显示屏，专业级性能', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3-512.jpg', '[\"https://img.example.com/macbook-pro-14-m3-512-1.jpg\",\"https://img.example.com/macbook-pro-14-m3-512-2.jpg\"]', 14999.00, 15999.00, 50, 180, '台', 1.55, '[\"M3芯片\",\"14英寸\",\"专业级\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"8GB\",\"16GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3芯片 8核CPU 10核GPU 16GB 1TB 银色', 'M3芯片，16GB统一内存，1TB固态硬盘，长达17小时续航', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3-1tb.jpg', '[\"https://img.example.com/macbook-pro-14-m3-1tb-1.jpg\"]', 18999.00, 19999.00, 40, 125, '台', 1.55, '[\"M3芯片\",\"16GB内存\",\"1TB\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"16GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3 Pro芯片 11核CPU 14核GPU 18GB 512GB 深空黑色', 'M3 Pro芯片，专业图形处理性能，Thunderbolt 4端口', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3pro-512.jpg', '[\"https://img.example.com/macbook-pro-14-m3pro-512-1.jpg\"]', 19999.00, 21999.00, 35, 95, '台', 1.60, '[\"M3 Pro\",\"专业级\",\"深空黑\"]', '{\"颜色\":[\"深空黑色\",\"银色\"],\"内存\":[\"18GB\",\"36GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3 Pro芯片 12核CPU 18核GPU 18GB 1TB 银色', 'M3 Pro芯片，18核GPU，适合视频剪辑和3D渲染', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3pro-1tb.jpg', '[\"https://img.example.com/macbook-pro-14-m3pro-1tb-1.jpg\"]', 23999.00, 25999.00, 30, 78, '台', 1.60, '[\"M3 Pro\",\"18核GPU\",\"1TB\"]', '{\"颜色\":[\"银色\",\"深空黑色\"],\"内存\":[\"18GB\",\"36GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3 Max芯片 14核CPU 30核GPU 36GB 1TB 深空黑色', 'M3 Max芯片，顶级性能，适合专业创作者', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3max-1tb.jpg', '[\"https://img.example.com/macbook-pro-14-m3max-1tb-1.jpg\"]', 31999.00, 33999.00, 20, 45, '台', 1.61, '[\"M3 Max\",\"30核GPU\",\"顶配\"]', '{\"颜色\":[\"深空黑色\",\"银色\"],\"内存\":[\"36GB\",\"64GB\"],\"存储\":[\"1TB\",\"2TB\",\"4TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

-- MacBook Pro 16英寸系列
('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M3 Pro芯片 12核CPU 18核GPU 18GB 512GB 深空黑色', '16英寸大屏，M3 Pro芯片，专业工作站级性能', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-16-m3pro-512.jpg', '[\"https://img.example.com/macbook-pro-16-m3pro-512-1.jpg\"]', 22999.00, 24999.00, 45, 156, '台', 2.14, '[\"16英寸\",\"M3 Pro\",\"大屏\"]', '{\"颜色\":[\"深空黑色\",\"银色\"],\"内存\":[\"18GB\",\"36GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M3 Pro芯片 12核CPU 18核GPU 36GB 1TB 银色', 'M3 Pro芯片，36GB大内存，适合大型项目开发', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-16-m3pro-1tb.jpg', '[\"https://img.example.com/macbook-pro-16-m3pro-1tb-1.jpg\"]', 27999.00, 29999.00, 38, 112, '台', 2.14, '[\"16英寸\",\"36GB内存\",\"1TB\"]', '{\"颜色\":[\"银色\",\"深空黑色\"],\"内存\":[\"36GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M3 Max芯片 14核CPU 30核GPU 36GB 1TB 深空黑色', 'M3 Max芯片，30核GPU，终极图形处理能力', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-16-m3max-1tb.jpg', '[\"https://img.example.com/macbook-pro-16-m3max-1tb-1.jpg\"]', 34999.00, 36999.00, 25, 68, '台', 2.15, '[\"M3 Max\",\"30核GPU\",\"专业级\"]', '{\"颜色\":[\"深空黑色\",\"银色\"],\"内存\":[\"36GB\",\"64GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M3 Max芯片 16核CPU 40核GPU 48GB 2TB 银色', 'M3 Max芯片，40核GPU，2TB存储，顶级配置', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-16-m3max-2tb.jpg', '[\"https://img.example.com/macbook-pro-16-m3max-2tb-1.jpg\"]', 42999.00, 44999.00, 18, 52, '台', 2.15, '[\"M3 Max\",\"40核GPU\",\"2TB\"]', '{\"颜色\":[\"银色\",\"深空黑色\"],\"内存\":[\"48GB\",\"64GB\"],\"存储\":[\"2TB\",\"4TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M3 Max芯片 16核CPU 40核GPU 64GB 4TB 深空黑色', 'M3 Max芯片，64GB内存，4TB存储，终极旗舰配置', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-16-m3max-4tb.jpg', '[\"https://img.example.com/macbook-pro-16-m3max-4tb-1.jpg\"]', 54999.00, 56999.00, 10, 28, '台', 2.15, '[\"M3 Max\",\"64GB\",\"4TB\",\"旗舰\"]', '{\"颜色\":[\"深空黑色\",\"银色\"],\"内存\":[\"64GB\"],\"存储\":[\"4TB\",\"8TB\"]}', 1, 1, 2, NOW(), NOW(), 0),

-- MacBook Pro M2系列（上一代，价格优惠）
('MacBook Pro 13', 'Apple MacBook Pro 13英寸 M2芯片 8核CPU 10核GPU 8GB 256GB 深空灰色', 'M2芯片，轻薄便携，适合移动办公', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-13-m2-256.jpg', '[\"https://img.example.com/macbook-pro-13-m2-256-1.jpg\"]', 9999.00, 10999.00, 60, 245, '台', 1.40, '[\"M2芯片\",\"13英寸\",\"便携\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"8GB\",\"16GB\"],\"存储\":[\"256GB\",\"512GB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 13', 'Apple MacBook Pro 13英寸 M2芯片 8核CPU 10核GPU 16GB 512GB 银色', 'M2芯片，16GB内存，触控栏设计', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-13-m2-512.jpg', '[\"https://img.example.com/macbook-pro-13-m2-512-1.jpg\"]', 12999.00, 13999.00, 55, 198, '台', 1.40, '[\"M2芯片\",\"16GB\",\"触控栏\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"16GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M2 Pro芯片 10核CPU 16核GPU 16GB 512GB 深空灰色', 'M2 Pro芯片，专业性能，性价比高', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-14-m2pro-512.jpg', '[\"https://img.example.com/macbook-pro-14-m2pro-512-1.jpg\"]', 16999.00, 18999.00, 48, 186, '台', 1.60, '[\"M2 Pro\",\"14英寸\",\"性价比\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"16GB\",\"32GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M2 Pro芯片 12核CPU 19核GPU 16GB 1TB 银色', 'M2 Pro芯片，19核GPU，适合专业创作', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-14-m2pro-1tb.jpg', '[\"https://img.example.com/macbook-pro-14-m2pro-1tb-1.jpg\"]', 20999.00, 22999.00, 42, 152, '台', 1.60, '[\"M2 Pro\",\"19核GPU\",\"1TB\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"16GB\",\"32GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M2 Max芯片 12核CPU 30核GPU 32GB 1TB 深空灰色', 'M2 Max芯片，30核GPU，专业工作站', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-14-m2max-1tb.jpg', '[\"https://img.example.com/macbook-pro-14-m2max-1tb-1.jpg\"]', 27999.00, 29999.00, 35, 98, '台', 1.63, '[\"M2 Max\",\"30核GPU\",\"32GB\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"32GB\",\"64GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M2 Pro芯片 12核CPU 19核GPU 16GB 512GB 银色', '16英寸大屏，M2 Pro芯片，办公娱乐两相宜', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-16-m2pro-512.jpg', '[\"https://img.example.com/macbook-pro-16-m2pro-512-1.jpg\"]', 19999.00, 21999.00, 50, 168, '台', 2.15, '[\"16英寸\",\"M2 Pro\",\"大屏\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"16GB\",\"32GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M2 Pro芯片 12核CPU 19核GPU 32GB 1TB 深空灰色', 'M2 Pro芯片，32GB大内存，多任务处理', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-16-m2pro-1tb.jpg', '[\"https://img.example.com/macbook-pro-16-m2pro-1tb-1.jpg\"]', 24999.00, 26999.00, 40, 132, '台', 2.15, '[\"M2 Pro\",\"32GB\",\"多任务\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"32GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M2 Max芯片 12核CPU 38核GPU 32GB 1TB 银色', 'M2 Max芯片，38核GPU，终极图形性能', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-16-m2max-1tb.jpg', '[\"https://img.example.com/macbook-pro-16-m2max-1tb-1.jpg\"]', 31999.00, 33999.00, 30, 85, '台', 2.16, '[\"M2 Max\",\"38核GPU\",\"专业\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"32GB\",\"64GB\"],\"存储\":[\"1TB\",\"2TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

('MacBook Pro 16', 'Apple MacBook Pro 16英寸 M2 Max芯片 12核CPU 38核GPU 64GB 2TB 深空灰色', 'M2 Max芯片，64GB内存，2TB存储，旗舰配置', @notebook_category_id, 2, 'Apple', 'https://img.example.com/macbook-pro-16-m2max-2tb.jpg', '[\"https://img.example.com/macbook-pro-16-m2max-2tb-1.jpg\"]', 39999.00, 41999.00, 22, 62, '台', 2.16, '[\"M2 Max\",\"64GB\",\"2TB\",\"旗舰\"]', '{\"颜色\":[\"深空灰色\",\"银色\"],\"内存\":[\"64GB\"],\"存储\":[\"2TB\",\"4TB\"]}', 1, 1, 3, NOW(), NOW(), 0),

-- 教育优惠款
('MacBook Pro 14', 'Apple MacBook Pro 14英寸 M3芯片 教育优惠版 8GB 512GB 银色', 'M3芯片，教育优惠，适合学生和教师', @notebook_category_id, 1, 'Apple', 'https://img.example.com/macbook-pro-14-m3-edu.jpg', '[\"https://img.example.com/macbook-pro-14-m3-edu-1.jpg\"]', 13999.00, 15999.00, 80, 320, '台', 1.55, '[\"M3芯片\",\"教育优惠\",\"学生\"]', '{\"颜色\":[\"银色\",\"深空灰色\"],\"内存\":[\"8GB\",\"16GB\"],\"存储\":[\"512GB\",\"1TB\"]}', 1, 1, 2, NOW(), NOW(), 0);

-- 查询结果统计
SELECT
    '补充完成！' AS 提示,
    (SELECT COUNT(*) FROM product WHERE categoryId = @notebook_category_id) AS 笔记本电脑商品总数,
    (SELECT SUM(stock) FROM product WHERE categoryId = @notebook_category_id) AS 总库存,
    (SELECT SUM(sales) FROM product WHERE categoryId = @notebook_category_id) AS 总销量;

-- 查看 MacBook Pro 各型号统计
SELECT
    SUBSTRING_INDEX(productName, ' ', 3) AS 型号,
    COUNT(*) AS 商品数量,
    MIN(price) AS 最低价,
    MAX(price) AS 最高价,
    SUM(stock) AS 库存,
    SUM(sales) AS 销量
FROM product
WHERE categoryId = @notebook_category_id AND productName LIKE 'MacBook%'
GROUP BY SUBSTRING_INDEX(productName, ' ', 3)
ORDER BY 销量 DESC;