-- ===================================================================
-- 轮播图测试数据
-- 说明：插入5条轮播图数据，用于首页展示
-- ===================================================================

USE yunshopping;

-- 插入轮播图数据
INSERT INTO banner (title, imageUrl, linkType, linkValue, sortOrder, status, createTime, updateTime, isDelete)
VALUES
-- 轮播图1：手机数码分类
('手机数码专场', 'https://img.example.com/banner1.jpg', 'category', '1', 1, 1, NOW(), NOW(), 0),

-- 轮播图2：iPhone 15 Pro Max商品
('iPhone 15 Pro Max 新品上市', 'https://img.example.com/banner2.jpg', 'product', '63', 2, 1, NOW(), NOW(), 0),

-- 轮播图3：电脑办公分类
('电脑办公精选', 'https://img.example.com/banner3.jpg', 'category', '2', 3, 1, NOW(), NOW(), 0),

-- 轮播图4：数码旗舰店
('数码旗舰店', 'https://img.example.com/banner4.jpg', 'shop', '1', 4, 1, NOW(), NOW(), 0),

-- 轮播图5：外部活动链接
('年货节大促', 'https://img.example.com/banner5.jpg', 'url', 'https://www.example.com/activity', 5, 1, NOW(), NOW(), 0);

-- 查询插入结果
SELECT * FROM banner WHERE isDelete = 0 ORDER BY sortOrder;