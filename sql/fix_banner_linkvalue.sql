-- ===================================================================
-- 修复轮播图的linkValue,使其与实际的分类ID和商品ID对应
-- ===================================================================

USE yunshopping;

-- 更新轮播图1: 手机数码分类(一级分类ID=1是正确的)
UPDATE banner
SET linkValue = '1'
WHERE title = '手机数码专场' AND linkType = 'category';

-- 更新轮播图2: iPhone 15 Pro Max商品(需要查询实际的商品ID)
UPDATE banner
SET linkValue = (SELECT id FROM product WHERE productName = 'iPhone 15 Pro Max' LIMIT 1)
WHERE title LIKE '%iPhone 15 Pro Max%' AND linkType = 'product';

-- 更新轮播图3: 电脑办公分类(一级分类ID=2是正确的)
UPDATE banner
SET linkValue = '2'
WHERE title = '电脑办公精选' AND linkType = 'category';

-- 更新轮播图4: 数码旗舰店(店铺ID=1是正确的)
UPDATE banner
SET linkValue = '1'
WHERE title = '数码旗舰店' AND linkType = 'shop';

-- 查看更新后的轮播图数据
SELECT
    id,
    title,
    linkType,
    linkValue,
    CASE
        WHEN linkType = 'category' THEN (SELECT categoryName FROM category WHERE id = banner.linkValue)
        WHEN linkType = 'product' THEN (SELECT productName FROM product WHERE id = banner.linkValue)
        WHEN linkType = 'shop' THEN (SELECT shopName FROM shop WHERE id = banner.linkValue)
        ELSE linkValue
    END AS '链接目标'
FROM banner
WHERE isDelete = 0
ORDER BY sortOrder;