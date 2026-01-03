-- ===================================================================
-- 修复轮播图数据,使linkValue指向真实存在的商品/分类/店铺
-- ===================================================================

USE yunshopping;

-- ===================================================================
-- 第一步:查看当前轮播图数据和实际可用的目标
-- ===================================================================

SELECT '==== 当前轮播图数据 ====' AS '';
SELECT id, title, linkType, linkValue, status FROM banner WHERE isDelete = 0 ORDER BY sortOrder;

SELECT '==== 可用的热门商品 ====' AS '';
SELECT id, productName, price, sales FROM product
WHERE status = 1 AND reviewStatus = 1 AND isDelete = 0
ORDER BY sales DESC LIMIT 10;

SELECT '==== 可用的一级分类 ====' AS '';
SELECT id, categoryName FROM category WHERE level = 1 AND isDelete = 0 ORDER BY sortOrder LIMIT 5;

SELECT '==== 可用的店铺 ====' AS '';
SELECT id, shopName FROM shop WHERE shopStatus = 1 AND isDelete = 0 LIMIT 5;

-- ===================================================================
-- 第二步:更新轮播图数据
-- ===================================================================

-- 更新第1条:手机数码分类(linkValue='1'已正确,一级分类ID)
-- 无需修改

-- 更新第2条:将商品轮播图改为指向销量最高的商品
UPDATE banner
SET linkValue = (
    SELECT id FROM product
    WHERE status = 1 AND reviewStatus = 1 AND isDelete = 0
    ORDER BY sales DESC LIMIT 1
),
title = CONCAT('热销爆款 ', (SELECT productName FROM product WHERE status = 1 AND reviewStatus = 1 AND isDelete = 0 ORDER BY sales DESC LIMIT 1))
WHERE linkType = 'product' AND sortOrder = 2;

-- 更新第3条:电脑办公分类(linkValue='2'已正确,一级分类ID)
-- 无需修改

-- 更新第4条:店铺轮播图
UPDATE banner
SET linkValue = (
    SELECT id FROM shop
    WHERE shopStatus = 1 AND isDelete = 0
    ORDER BY id ASC LIMIT 1
)
WHERE linkType = 'shop' AND sortOrder = 4;

-- ===================================================================
-- 第三步:如果想添加更多有效的商品轮播图,可以插入新数据
-- ===================================================================

-- 删除旧的无效轮播图(如果存在)
-- DELETE FROM banner WHERE linkType = 'product' AND linkValue NOT IN (SELECT id FROM product WHERE status = 1 AND reviewStatus = 1);

-- 插入新的商品轮播图(指向真实商品)
INSERT INTO banner (title, imageUrl, linkType, linkValue, sortOrder, status, createTime, updateTime, isDelete)
SELECT
    CONCAT(productName, ' 限时特惠'),
    'https://img.example.com/product-banner.jpg',
    'product',
    id,
    10,
    1,
    NOW(),
    NOW(),
    0
FROM product
WHERE status = 1 AND reviewStatus = 1 AND isDelete = 0
ORDER BY sales DESC LIMIT 1
ON DUPLICATE KEY UPDATE title = title;

-- ===================================================================
-- 第四步:验证修复结果
-- ===================================================================

SELECT '==== 修复后的轮播图数据 ====' AS '';

SELECT
    b.id,
    b.title,
    b.linkType,
    b.linkValue,
    b.sortOrder,
    CASE
        WHEN b.linkType = 'category' THEN (SELECT categoryName FROM category WHERE id = b.linkValue)
        WHEN b.linkType = 'product' THEN (SELECT productName FROM product WHERE id = b.linkValue)
        WHEN b.linkType = 'shop' THEN (SELECT shopName FROM shop WHERE id = b.linkValue)
        ELSE '外链'
    END AS target_name,
    CASE
        WHEN b.linkType = 'category' AND EXISTS(SELECT 1 FROM category WHERE id = b.linkValue AND isDelete = 0) THEN '✓ 有效'
        WHEN b.linkType = 'product' AND EXISTS(SELECT 1 FROM product WHERE id = b.linkValue AND status = 1 AND reviewStatus = 1 AND isDelete = 0) THEN '✓ 有效'
        WHEN b.linkType = 'shop' AND EXISTS(SELECT 1 FROM shop WHERE id = b.linkValue AND shopStatus = 1 AND isDelete = 0) THEN '✓ 有效'
        WHEN b.linkType = 'url' THEN '✓ 外链'
        ELSE '✗ 无效'
    END AS status
FROM banner b
WHERE b.isDelete = 0
ORDER BY b.sortOrder;

SELECT '==== 修复完成 ====' AS '';