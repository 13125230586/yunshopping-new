-- ===================================================================
-- 商品分类测试数据（三级分类结构）
-- 说明：包含 5 个一级分类，每个一级分类下有多个二级分类和三级分类
-- ===================================================================

USE yunshopping;

-- 清空现有分类数据（可选）
-- TRUNCATE TABLE category;

-- ===================================================================
-- 一级分类（level = 1, parentId = 0）
-- ===================================================================

-- 1. 手机数码
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (1, '手机数码', 0, 1, 1, 'https://example.com/icons/mobile.png', NOW(), NOW(), 0);

-- 2. 电脑办公
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (2, '电脑办公', 0, 1, 2, 'https://example.com/icons/computer.png', NOW(), NOW(), 0);

-- 3. 服装鞋包
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (3, '服装鞋包', 0, 1, 3, 'https://example.com/icons/clothing.png', NOW(), NOW(), 0);

-- 4. 家用电器
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (4, '家用电器', 0, 1, 4, 'https://example.com/icons/appliance.png', NOW(), NOW(), 0);

-- 5. 食品生鲜
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (5, '食品生鲜', 0, 1, 5, 'https://example.com/icons/food.png', NOW(), NOW(), 0);

-- 6. 图书音像
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (6, '图书音像', 0, 1, 6, 'https://example.com/icons/book.png', NOW(), NOW(), 0);

-- 7. 运动户外
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (7, '运动户外', 0, 1, 7, 'https://example.com/icons/sports.png', NOW(), NOW(), 0);

-- 8. 美妆个护
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (8, '美妆个护', 0, 1, 8, 'https://example.com/icons/beauty.png', NOW(), NOW(), 0);

-- 9. 母婴玩具
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (9, '母婴玩具', 0, 1, 9, 'https://example.com/icons/baby.png', NOW(), NOW(), 0);

-- 10. 家居家装
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (10, '家居家装', 0, 1, 10, 'https://example.com/icons/home.png', NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类（level = 2）- 手机数码
-- ===================================================================

-- 1.1 手机通讯
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (101, '手机通讯', 1, 2, 1, NULL, NOW(), NOW(), 0);

-- 1.2 数码配件
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (102, '数码配件', 1, 2, 2, NULL, NOW(), NOW(), 0);

-- 1.3 摄影摄像
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (103, '摄影摄像', 1, 2, 3, NULL, NOW(), NOW(), 0);

-- 1.4 影音娱乐
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (104, '影音娱乐', 1, 2, 4, NULL, NOW(), NOW(), 0);

-- 1.5 智能设备
INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES (105, '智能设备', 1, 2, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 手机通讯
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('iPhone', 101, 3, 1, NULL, NOW(), NOW(), 0),
('华为手机', 101, 3, 2, NULL, NOW(), NOW(), 0),
('小米手机', 101, 3, 3, NULL, NOW(), NOW(), 0),
('OPPO手机', 101, 3, 4, NULL, NOW(), NOW(), 0),
('vivo手机', 101, 3, 5, NULL, NOW(), NOW(), 0),
('荣耀手机', 101, 3, 6, NULL, NOW(), NOW(), 0),
('三星手机', 101, 3, 7, NULL, NOW(), NOW(), 0),
('一加手机', 101, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 数码配件
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('手机壳', 102, 3, 1, NULL, NOW(), NOW(), 0),
('钢化膜', 102, 3, 2, NULL, NOW(), NOW(), 0),
('充电器', 102, 3, 3, NULL, NOW(), NOW(), 0),
('数据线', 102, 3, 4, NULL, NOW(), NOW(), 0),
('充电宝', 102, 3, 5, NULL, NOW(), NOW(), 0),
('蓝牙耳机', 102, 3, 6, NULL, NOW(), NOW(), 0),
('有线耳机', 102, 3, 7, NULL, NOW(), NOW(), 0),
('手机支架', 102, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 摄影摄像
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('单反相机', 103, 3, 1, NULL, NOW(), NOW(), 0),
('微单相机', 103, 3, 2, NULL, NOW(), NOW(), 0),
('数码相机', 103, 3, 3, NULL, NOW(), NOW(), 0),
('摄像机', 103, 3, 4, NULL, NOW(), NOW(), 0),
('镜头', 103, 3, 5, NULL, NOW(), NOW(), 0),
('三脚架', 103, 3, 6, NULL, NOW(), NOW(), 0),
('云台稳定器', 103, 3, 7, NULL, NOW(), NOW(), 0),
('相机包', 103, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 电脑办公
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(201, '电脑整机', 2, 2, 1, NULL, NOW(), NOW(), 0),
(202, '电脑配件', 2, 2, 2, NULL, NOW(), NOW(), 0),
(203, '外设产品', 2, 2, 3, NULL, NOW(), NOW(), 0),
(204, '网络产品', 2, 2, 4, NULL, NOW(), NOW(), 0),
(205, '办公设备', 2, 2, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 电脑整机
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('笔记本电脑', 201, 3, 1, NULL, NOW(), NOW(), 0),
('游戏本', 201, 3, 2, NULL, NOW(), NOW(), 0),
('台式机', 201, 3, 3, NULL, NOW(), NOW(), 0),
('平板电脑', 201, 3, 4, NULL, NOW(), NOW(), 0),
('一体机', 201, 3, 5, NULL, NOW(), NOW(), 0),
('服务器', 201, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 电脑配件
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('显示器', 202, 3, 1, NULL, NOW(), NOW(), 0),
('CPU处理器', 202, 3, 2, NULL, NOW(), NOW(), 0),
('主板', 202, 3, 3, NULL, NOW(), NOW(), 0),
('显卡', 202, 3, 4, NULL, NOW(), NOW(), 0),
('内存', 202, 3, 5, NULL, NOW(), NOW(), 0),
('硬盘', 202, 3, 6, NULL, NOW(), NOW(), 0),
('固态硬盘', 202, 3, 7, NULL, NOW(), NOW(), 0),
('电源', 202, 3, 8, NULL, NOW(), NOW(), 0),
('机箱', 202, 3, 9, NULL, NOW(), NOW(), 0),
('散热器', 202, 3, 10, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 外设产品
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('鼠标', 203, 3, 1, NULL, NOW(), NOW(), 0),
('键盘', 203, 3, 2, NULL, NOW(), NOW(), 0),
('机械键盘', 203, 3, 3, NULL, NOW(), NOW(), 0),
('游戏鼠标', 203, 3, 4, NULL, NOW(), NOW(), 0),
('鼠标垫', 203, 3, 5, NULL, NOW(), NOW(), 0),
('音箱', 203, 3, 6, NULL, NOW(), NOW(), 0),
('摄像头', 203, 3, 7, NULL, NOW(), NOW(), 0),
('U盘', 203, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 服装鞋包
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(301, '男装', 3, 2, 1, NULL, NOW(), NOW(), 0),
(302, '女装', 3, 2, 2, NULL, NOW(), NOW(), 0),
(303, '童装', 3, 2, 3, NULL, NOW(), NOW(), 0),
(304, '男鞋', 3, 2, 4, NULL, NOW(), NOW(), 0),
(305, '女鞋', 3, 2, 5, NULL, NOW(), NOW(), 0),
(306, '箱包', 3, 2, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 男装
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('T恤', 301, 3, 1, NULL, NOW(), NOW(), 0),
('衬衫', 301, 3, 2, NULL, NOW(), NOW(), 0),
('夹克', 301, 3, 3, NULL, NOW(), NOW(), 0),
('卫衣', 301, 3, 4, NULL, NOW(), NOW(), 0),
('牛仔裤', 301, 3, 5, NULL, NOW(), NOW(), 0),
('休闲裤', 301, 3, 6, NULL, NOW(), NOW(), 0),
('西装', 301, 3, 7, NULL, NOW(), NOW(), 0),
('羽绒服', 301, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 女装
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('连衣裙', 302, 3, 1, NULL, NOW(), NOW(), 0),
('半身裙', 302, 3, 2, NULL, NOW(), NOW(), 0),
('T恤', 302, 3, 3, NULL, NOW(), NOW(), 0),
('衬衫', 302, 3, 4, NULL, NOW(), NOW(), 0),
('毛衣', 302, 3, 5, NULL, NOW(), NOW(), 0),
('打底裤', 302, 3, 6, NULL, NOW(), NOW(), 0),
('牛仔裤', 302, 3, 7, NULL, NOW(), NOW(), 0),
('风衣', 302, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 家用电器
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(401, '大家电', 4, 2, 1, NULL, NOW(), NOW(), 0),
(402, '生活电器', 4, 2, 2, NULL, NOW(), NOW(), 0),
(403, '厨房电器', 4, 2, 3, NULL, NOW(), NOW(), 0),
(404, '个护健康', 4, 2, 4, NULL, NOW(), NOW(), 0),
(405, '影音电器', 4, 2, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 大家电
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('电视', 401, 3, 1, NULL, NOW(), NOW(), 0),
('冰箱', 401, 3, 2, NULL, NOW(), NOW(), 0),
('洗衣机', 401, 3, 3, NULL, NOW(), NOW(), 0),
('空调', 401, 3, 4, NULL, NOW(), NOW(), 0),
('热水器', 401, 3, 5, NULL, NOW(), NOW(), 0),
('油烟机', 401, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 厨房电器
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('电饭煲', 403, 3, 1, NULL, NOW(), NOW(), 0),
('微波炉', 403, 3, 2, NULL, NOW(), NOW(), 0),
('电磁炉', 403, 3, 3, NULL, NOW(), NOW(), 0),
('榨汁机', 403, 3, 4, NULL, NOW(), NOW(), 0),
('豆浆机', 403, 3, 5, NULL, NOW(), NOW(), 0),
('电烤箱', 403, 3, 6, NULL, NOW(), NOW(), 0),
('空气炸锅', 403, 3, 7, NULL, NOW(), NOW(), 0),
('破壁机', 403, 3, 8, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 食品生鲜
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(501, '休闲零食', 5, 2, 1, NULL, NOW(), NOW(), 0),
(502, '饮料冲调', 5, 2, 2, NULL, NOW(), NOW(), 0),
(503, '粮油调味', 5, 2, 3, NULL, NOW(), NOW(), 0),
(504, '生鲜果蔬', 5, 2, 4, NULL, NOW(), NOW(), 0),
(505, '肉禽蛋品', 5, 2, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 休闲零食
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('饼干糕点', 501, 3, 1, NULL, NOW(), NOW(), 0),
('坚果炒货', 501, 3, 2, NULL, NOW(), NOW(), 0),
('糖果巧克力', 501, 3, 3, NULL, NOW(), NOW(), 0),
('蜜饯果干', 501, 3, 4, NULL, NOW(), NOW(), 0),
('膨化食品', 501, 3, 5, NULL, NOW(), NOW(), 0),
('肉类零食', 501, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 饮料冲调
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('牛奶乳品', 502, 3, 1, NULL, NOW(), NOW(), 0),
('咖啡', 502, 3, 2, NULL, NOW(), NOW(), 0),
('茶叶', 502, 3, 3, NULL, NOW(), NOW(), 0),
('果汁饮料', 502, 3, 4, NULL, NOW(), NOW(), 0),
('碳酸饮料', 502, 3, 5, NULL, NOW(), NOW(), 0),
('矿泉水', 502, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 运动户外
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(701, '运动鞋服', 7, 2, 1, NULL, NOW(), NOW(), 0),
(702, '健身器材', 7, 2, 2, NULL, NOW(), NOW(), 0),
(703, '户外装备', 7, 2, 3, NULL, NOW(), NOW(), 0),
(704, '骑行运动', 7, 2, 4, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 运动鞋服
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('运动鞋', 701, 3, 1, NULL, NOW(), NOW(), 0),
('篮球鞋', 701, 3, 2, NULL, NOW(), NOW(), 0),
('跑步鞋', 701, 3, 3, NULL, NOW(), NOW(), 0),
('运动服', 701, 3, 4, NULL, NOW(), NOW(), 0),
('运动背包', 701, 3, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 美妆个护
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(801, '面部护肤', 8, 2, 1, NULL, NOW(), NOW(), 0),
(802, '彩妆', 8, 2, 2, NULL, NOW(), NOW(), 0),
(803, '香水', 8, 2, 3, NULL, NOW(), NOW(), 0),
(804, '个人护理', 8, 2, 4, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 面部护肤
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('洁面', 801, 3, 1, NULL, NOW(), NOW(), 0),
('爽肤水', 801, 3, 2, NULL, NOW(), NOW(), 0),
('乳液面霜', 801, 3, 3, NULL, NOW(), NOW(), 0),
('精华液', 801, 3, 4, NULL, NOW(), NOW(), 0),
('面膜', 801, 3, 5, NULL, NOW(), NOW(), 0),
('眼霜', 801, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 彩妆
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('口红', 802, 3, 1, NULL, NOW(), NOW(), 0),
('粉底液', 802, 3, 2, NULL, NOW(), NOW(), 0),
('眼影', 802, 3, 3, NULL, NOW(), NOW(), 0),
('眉笔', 802, 3, 4, NULL, NOW(), NOW(), 0),
('腮红', 802, 3, 5, NULL, NOW(), NOW(), 0),
('睫毛膏', 802, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 母婴玩具
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(901, '奶粉辅食', 9, 2, 1, NULL, NOW(), NOW(), 0),
(902, '纸尿裤', 9, 2, 2, NULL, NOW(), NOW(), 0),
(903, '玩具乐器', 9, 2, 3, NULL, NOW(), NOW(), 0),
(904, '童车童床', 9, 2, 4, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 玩具乐器
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('积木拼插', 903, 3, 1, NULL, NOW(), NOW(), 0),
('遥控玩具', 903, 3, 2, NULL, NOW(), NOW(), 0),
('毛绒玩具', 903, 3, 3, NULL, NOW(), NOW(), 0),
('益智玩具', 903, 3, 4, NULL, NOW(), NOW(), 0),
('模型玩具', 903, 3, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 二级分类 - 家居家装
-- ===================================================================

INSERT INTO category (id, categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
(1001, '家纺', 10, 2, 1, NULL, NOW(), NOW(), 0),
(1002, '灯具', 10, 2, 2, NULL, NOW(), NOW(), 0),
(1003, '家装建材', 10, 2, 3, NULL, NOW(), NOW(), 0),
(1004, '家具', 10, 2, 4, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 家纺
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('床上四件套', 1001, 3, 1, NULL, NOW(), NOW(), 0),
('被子', 1001, 3, 2, NULL, NOW(), NOW(), 0),
('枕头', 1001, 3, 3, NULL, NOW(), NOW(), 0),
('毛巾浴巾', 1001, 3, 4, NULL, NOW(), NOW(), 0),
('窗帘', 1001, 3, 5, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 三级分类 - 家具
-- ===================================================================

INSERT INTO category (categoryName, parentId, level, sortOrder, icon, createTime, updateTime, isDelete)
VALUES
('沙发', 1004, 3, 1, NULL, NOW(), NOW(), 0),
('床', 1004, 3, 2, NULL, NOW(), NOW(), 0),
('衣柜', 1004, 3, 3, NULL, NOW(), NOW(), 0),
('电视柜', 1004, 3, 4, NULL, NOW(), NOW(), 0),
('书桌', 1004, 3, 5, NULL, NOW(), NOW(), 0),
('餐桌', 1004, 3, 6, NULL, NOW(), NOW(), 0);

-- ===================================================================
-- 数据统计查询
-- ===================================================================

-- 查看所有一级分类
SELECT id, categoryName, sortOrder FROM category WHERE level = 1 ORDER BY sortOrder;

-- 查看分类统计
SELECT
    level AS '分类级别',
    COUNT(*) AS '数量'
FROM category
WHERE isDelete = 0
GROUP BY level
ORDER BY level;

-- 查看完整分类树（示例：手机数码）
SELECT
    c1.categoryName AS '一级分类',
    c2.categoryName AS '二级分类',
    c3.categoryName AS '三级分类'
FROM category c1
LEFT JOIN category c2 ON c2.parentId = c1.id AND c2.level = 2
LEFT JOIN category c3 ON c3.parentId = c2.id AND c3.level = 3
WHERE c1.id = 1 AND c1.isDelete = 0
ORDER BY c2.sortOrder, c3.sortOrder;

-- ===================================================================
-- 执行完成提示
-- ===================================================================
SELECT '商品分类数据插入完成！' AS 提示,
       (SELECT COUNT(*) FROM category WHERE level = 1) AS 一级分类数量,
       (SELECT COUNT(*) FROM category WHERE level = 2) AS 二级分类数量,
       (SELECT COUNT(*) FROM category WHERE level = 3) AS 三级分类数量,
       (SELECT COUNT(*) FROM category) AS 总分类数量;