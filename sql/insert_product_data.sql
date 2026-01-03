-- ===================================================================
-- 商品测试数据（每个分类至少5个商品）
-- 说明：为主要三级分类创建商品数据，确保前端有数据展示
-- 前置条件：需要先执行 insert_category_data.sql 和 insert_test_data.sql（店铺数据）
-- ===================================================================

USE yunshopping;

-- ===================================================================
-- 手机通讯 - iPhone (分类ID需要查询，这里假设从 insert_category_data.sql 获取)
-- ===================================================================

-- 查询 iPhone 分类ID（假设是动态生成的，这里用子查询）
SET @iphone_category_id = (SELECT id FROM category WHERE categoryName = 'iPhone' AND level = 3 LIMIT 1);
SET @huawei_category_id = (SELECT id FROM category WHERE categoryName = '华为手机' AND level = 3 LIMIT 1);
SET @xiaomi_category_id = (SELECT id FROM category WHERE categoryName = '小米手机' AND level = 3 LIMIT 1);

-- iPhone 商品
INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('iPhone 15 Pro Max', 'Apple iPhone 15 Pro Max 256GB 原色钛金属', 'A17 Pro芯片，钛金属设计，灵动岛，4800万像素主摄，支持5G', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15promax.jpg', '["https://img.example.com/iphone15-1.jpg","https://img.example.com/iphone15-2.jpg"]', 9999.00, 10999.00, 100, 520, '台', 0.25, '["5G","钛金属","灵动岛"]', '{"颜色":["原色钛金属","黑色钛金属","白色钛金属"],"存储":["256GB","512GB","1TB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15 Pro', 'Apple iPhone 15 Pro 128GB 深空黑色', 'A17 Pro芯片，钛金属设计，三摄系统，支持5G', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15pro.jpg', '["https://img.example.com/iphone15pro-1.jpg","https://img.example.com/iphone15pro-2.jpg"]', 7999.00, 8999.00, 150, 680, '台', 0.22, '["5G","钛金属","Pro"]', '{"颜色":["深空黑色","银色","金色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 15', 'Apple iPhone 15 128GB 粉色', '灵动岛设计，4800万像素主摄，A16仿生芯片', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone15.jpg', '["https://img.example.com/iphone15-1.jpg"]', 5999.00, 6499.00, 200, 1200, '台', 0.20, '["5G","灵动岛","双摄"]', '{"颜色":["粉色","蓝色","黑色","绿色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14 Plus', 'Apple iPhone 14 Plus 256GB 紫色', '6.7英寸超视网膜XDR显示屏，A15芯片，双摄系统', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14plus.jpg', '["https://img.example.com/iphone14plus-1.jpg"]', 6899.00, 7399.00, 80, 450, '台', 0.23, '["5G","大屏","长续航"]', '{"颜色":["紫色","午夜色","星光色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone 14', 'Apple iPhone 14 128GB 午夜色', '超视网膜XDR显示屏，A15芯片，超长续航', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphone14.jpg', '["https://img.example.com/iphone14-1.jpg"]', 5399.00, 5999.00, 120, 890, '台', 0.19, '["5G","经典款","性价比"]', '{"颜色":["午夜色","星光色","红色"],"存储":["128GB","256GB","512GB"]}', 1, 1, 2, NOW(), NOW(), 0),

('iPhone SE 第三代', 'Apple iPhone SE (第三代) 64GB 星光色', 'A15仿生芯片，Touch ID，经典设计', @iphone_category_id, 1, 'Apple', 'https://img.example.com/iphonese3.jpg', '["https://img.example.com/iphonese3-1.jpg"]', 3499.00, 3899.00, 150, 320, '台', 0.15, '["5G","小屏","经典"]', '{"颜色":["星光色","午夜色","红色"],"存储":["64GB","128GB","256GB"]}', 1, 1, 2, NOW(), NOW(), 0);

-- 华为手机商品
INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('华为Mate 60 Pro', 'HUAWEI Mate 60 Pro 12GB+512GB 雅川青', '第二代昆仑玻璃，鸿蒙4.0，5000万像素XMAGE影像', @huawei_category_id, 2, '华为', 'https://img.example.com/mate60pro.jpg', '["https://img.example.com/mate60pro-1.jpg","https://img.example.com/mate60pro-2.jpg"]', 6999.00, 7499.00, 100, 890, '台', 0.23, '["鸿蒙","昆仑玻璃","XMAGE"]', '{"颜色":["雅川青","雅丹黑","白沙银"],"内存":["12GB+256GB","12GB+512GB","12GB+1TB"]}', 1, 1, 3, NOW(), NOW(), 0),

('华为Mate 60', 'HUAWEI Mate 60 12GB+256GB 雅丹黑', '超可靠鸿蒙玄武架构，5000万像素超光变摄像头', @huawei_category_id, 2, '华为', 'https://img.example.com/mate60.jpg', '["https://img.example.com/mate60-1.jpg"]', 5999.00, 6499.00, 120, 670, '台', 0.21, '["鸿蒙","性价比","旗舰"]', '{"颜色":["雅丹黑","雅川青","白沙银"],"内存":["12GB+256GB","12GB+512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('华为P60 Pro', 'HUAWEI P60 Pro 8GB+256GB 洛可可白', 'XMAGE影像，超聚光夜视长焦，鸿蒙3.1', @huawei_category_id, 2, '华为', 'https://img.example.com/p60pro.jpg', '["https://img.example.com/p60pro-1.jpg"]', 5988.00, 6488.00, 90, 520, '台', 0.20, '["拍照","轻薄","高端"]', '{"颜色":["洛可可白","翡冷翠","雅黑"],"内存":["8GB+256GB","8GB+512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('华为nova 12 Pro', 'HUAWEI nova 12 Pro 12GB+256GB 12号色', '前置6000万超广角镜头，鸿蒙4.0，100W超级快充', @huawei_category_id, 2, '华为', 'https://img.example.com/nova12pro.jpg', '["https://img.example.com/nova12pro-1.jpg"]', 3999.00, 4299.00, 150, 780, '台', 0.19, '["自拍","快充","时尚"]', '{"颜色":["12号色","曜金黑","樱语粉"],"内存":["12GB+256GB","12GB+512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('华为畅享70', 'HUAWEI 畅享70 8GB+128GB 曜石黑', '6000mAh大电池，22.5W快充，鸿蒙系统', @huawei_category_id, 2, '华为', 'https://img.example.com/chanxiang70.jpg', '["https://img.example.com/chanxiang70-1.jpg"]', 1199.00, 1399.00, 200, 1500, '台', 0.22, '["长续航","性价比","学生机"]', '{"颜色":["曜石黑","冰霜银","樱语粉"],"内存":["8GB+128GB","8GB+256GB"]}', 1, 1, 3, NOW(), NOW(), 0);

-- 小米手机商品
INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('小米14 Pro', 'Xiaomi 14 Pro 16GB+512GB 钛金属', '第三代骁龙8领先版，徕卡光学镜头，澎湃OS', @xiaomi_category_id, 2, '小米', 'https://img.example.com/mi14pro.jpg', '["https://img.example.com/mi14pro-1.jpg","https://img.example.com/mi14pro-2.jpg"]', 5999.00, 6499.00, 100, 920, '台', 0.22, '["骁龙8Gen3","徕卡","高性能"]', '{"颜色":["钛金属","黑色","白色"],"内存":["12GB+256GB","16GB+512GB","16GB+1TB"]}', 1, 1, 3, NOW(), NOW(), 0),

('小米14', 'Xiaomi 14 12GB+256GB 黑色', '第三代骁龙8，徕卡光学镜头，小屏旗舰', @xiaomi_category_id, 2, '小米', 'https://img.example.com/mi14.jpg', '["https://img.example.com/mi14-1.jpg"]', 3999.00, 4299.00, 150, 1280, '台', 0.19, '["小屏","旗舰","徕卡"]', '{"颜色":["黑色","白色","绿色"],"内存":["8GB+256GB","12GB+256GB","12GB+512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('Redmi K70 Pro', 'Redmi K70 Pro 12GB+256GB 墨羽', '第三代骁龙8，2K高光屏，5000mAh+120W', @xiaomi_category_id, 2, '小米', 'https://img.example.com/k70pro.jpg', '["https://img.example.com/k70pro-1.jpg"]', 3299.00, 3599.00, 180, 1650, '台', 0.21, '["性价比","游戏","2K屏"]', '{"颜色":["墨羽","冰钛","竹月"],"内存":["12GB+256GB","16GB+512GB","16GB+1TB"]}', 1, 1, 3, NOW(), NOW(), 0),

('Redmi Note 13 Pro+', 'Redmi Note 13 Pro+ 12GB+256GB 冰川蓝', '2亿像素主摄，120W快充，曲面屏', @xiaomi_category_id, 2, '小米', 'https://img.example.com/note13proplus.jpg', '["https://img.example.com/note13proplus-1.jpg"]', 1999.00, 2199.00, 200, 2100, '台', 0.20, '["2亿像素","快充","中端"]', '{"颜色":["冰川蓝","曜石黑","月影白"],"内存":["8GB+256GB","12GB+256GB","12GB+512GB"]}', 1, 1, 3, NOW(), NOW(), 0),

('Redmi 13C', 'Redmi 13C 4GB+128GB 星夜黑', '5000mAh大电池，90Hz高刷屏，入门首选', @xiaomi_category_id, 2, '小米', 'https://img.example.com/13c.jpg', '["https://img.example.com/13c-1.jpg"]', 699.00, 799.00, 300, 3200, '台', 0.19, '["入门","长续航","学生机"]', '{"颜色":["星夜黑","冰川蓝","薄荷绿"],"内存":["4GB+128GB","6GB+128GB","8GB+256GB"]}', 1, 1, 3, NOW(), NOW(), 0);

-- ===================================================================
-- 数码配件 - 充电宝
-- ===================================================================

SET @powerbank_category_id = (SELECT id FROM category WHERE categoryName = '充电宝' AND level = 3 LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('小米移动电源3', '小米移动电源3 20000mAh 双向快充版', '20000mAh大容量，支持22.5W双向快充，三口输出', @powerbank_category_id, 2, '小米', 'https://img.example.com/powerbank-mi.jpg', '["https://img.example.com/powerbank-mi-1.jpg"]', 149.00, 179.00, 500, 5200, '个', 0.43, '["大容量","快充","性价比"]', '{"容量":["10000mAh","20000mAh"],"颜色":["黑色","白色"]}', 1, 1, 3, NOW(), NOW(), 0),

('华为超级快充移动电源', '华为超级快充移动电源 12000mAh', '66W超级快充，双向快充，多协议兼容', @powerbank_category_id, 2, '华为', 'https://img.example.com/powerbank-hw.jpg', '["https://img.example.com/powerbank-hw-1.jpg"]', 299.00, 349.00, 300, 2800, '个', 0.28, '["66W快充","华为","轻便"]', '{"容量":["12000mAh"],"颜色":["白色","黑色"]}', 1, 1, 3, NOW(), NOW(), 0),

('Anker安克充电宝', 'Anker 737充电宝 24000mAh 140W', '140W双向快充，24000mAh容量，支持笔记本充电', @powerbank_category_id, 1, 'Anker', 'https://img.example.com/powerbank-anker.jpg', '["https://img.example.com/powerbank-anker-1.jpg"]', 699.00, 799.00, 200, 1500, '个', 0.60, '["140W","大容量","笔记本"]', '{"容量":["24000mAh"],"颜色":["黑色"]}', 1, 1, 2, NOW(), NOW(), 0),

('品胜充电宝', '品胜 50000mAh 大容量充电宝 22.5W快充', '50000mAh超大容量，四口输出，LED数显', @powerbank_category_id, 2, '品胜', 'https://img.example.com/powerbank-pisen.jpg', '["https://img.example.com/powerbank-pisen-1.jpg"]', 199.00, 249.00, 400, 3600, '个', 1.05, '["超大容量","多口输出","数显"]', '{"容量":["30000mAh","50000mAh"],"颜色":["黑色","白色"]}', 1, 1, 3, NOW(), NOW(), 0),

('罗马仕充电宝', '罗马仕 sense8+ 30000mAh PD20W快充', '30000mAh大容量，PD20W快充，Type-C双向快充', @powerbank_category_id, 2, '罗马仕', 'https://img.example.com/powerbank-romoss.jpg', '["https://img.example.com/powerbank-romoss-1.jpg"]', 129.00, 159.00, 600, 7200, '个', 0.66, '["大容量","PD快充","经济实惠"]', '{"容量":["20000mAh","30000mAh"],"颜色":["黑色","白色"]}', 1, 1, 3, NOW(), NOW(), 0),

('绿联充电宝', '绿联磁吸无线充电宝 10000mAh 20W', 'MagSafe磁吸无线充电，20W有线快充，轻薄便携', @powerbank_category_id, 1, '绿联', 'https://img.example.com/powerbank-ugreen.jpg', '["https://img.example.com/powerbank-ugreen-1.jpg"]', 199.00, 249.00, 350, 2100, '个', 0.22, '["无线充电","磁吸","轻薄"]', '{"容量":["5000mAh","10000mAh"],"颜色":["黑色","白色","蓝色"]}', 1, 1, 2, NOW(), NOW(), 0);

-- ===================================================================
-- 电脑配件 - 显示器
-- ===================================================================

SET @monitor_category_id = (SELECT id FROM category WHERE categoryName = '显示器' AND level = 3 LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('小米显示器27英寸', '小米显示器 27英寸 2K 165Hz 电竞显示器', '2560x1440分辨率，165Hz高刷，1ms响应，HDR400', @monitor_category_id, 2, '小米', 'https://img.example.com/monitor-mi.jpg', '["https://img.example.com/monitor-mi-1.jpg"]', 1299.00, 1499.00, 150, 880, '台', 5.2, '["2K","165Hz","电竞"]', '{"尺寸":["24英寸","27英寸"],"分辨率":["1080P","2K"]}', 1, 1, 3, NOW(), NOW(), 0),

('戴尔显示器', 'DELL U2723DE 27英寸 4K USB-C显示器', '3840x2160 4K分辨率，Type-C 90W反向充电，IPS屏', @monitor_category_id, 1, '戴尔', 'https://img.example.com/monitor-dell.jpg', '["https://img.example.com/monitor-dell-1.jpg"]', 3699.00, 3999.00, 80, 320, '台', 6.8, '["4K","专业","Type-C"]', '{"尺寸":["27英寸"],"分辨率":["4K"]}', 1, 1, 2, NOW(), NOW(), 0),

('LG显示器', 'LG 27GP850 27英寸 2K 180Hz Nano IPS电竞显示器', 'Nano IPS技术，180Hz刷新率，1ms响应，G-SYNC兼容', @monitor_category_id, 1, 'LG', 'https://img.example.com/monitor-lg.jpg', '["https://img.example.com/monitor-lg-1.jpg"]', 2399.00, 2699.00, 100, 560, '台', 6.1, '["Nano IPS","180Hz","G-SYNC"]', '{"尺寸":["27英寸"],"刷新率":["144Hz","180Hz"]}', 1, 1, 2, NOW(), NOW(), 0),

('华硕显示器', 'ASUS ROG PG27AQDM 27英寸 2K OLED电竞显示器', 'OLED屏幕，240Hz刷新率，0.03ms响应，DisplayHDR True Black 400', @monitor_category_id, 1, '华硕', 'https://img.example.com/monitor-asus.jpg', '["https://img.example.com/monitor-asus-1.jpg"]', 5999.00, 6499.00, 50, 180, '台', 7.2, '["OLED","240Hz","顶级电竞"]', '{"尺寸":["27英寸"],"刷新率":["240Hz"]}', 1, 1, 2, NOW(), NOW(), 0),

('明基显示器', 'BenQ EW2780U 27英寸 4K IPS显示器', '4K分辨率，HDR10，内置音响，低蓝光护眼', @monitor_category_id, 1, '明基', 'https://img.example.com/monitor-benq.jpg', '["https://img.example.com/monitor-benq-1.jpg"]', 2299.00, 2599.00, 120, 620, '台', 6.5, '["4K","护眼","音响"]', '{"尺寸":["24英寸","27英寸"],"分辨率":["4K"]}', 1, 1, 2, NOW(), NOW(), 0),

('AOC显示器', 'AOC 24G2 23.8英寸 1080P 144Hz电竞显示器', '1920x1080分辨率，144Hz刷新率，1ms响应，性价比之选', @monitor_category_id, 2, 'AOC', 'https://img.example.com/monitor-aoc.jpg', '["https://img.example.com/monitor-aoc-1.jpg"]', 799.00, 999.00, 200, 1520, '台', 4.5, '["性价比","144Hz","入门电竞"]', '{"尺寸":["23.8英寸","27英寸"],"刷新率":["144Hz","165Hz"]}', 1, 1, 3, NOW(), NOW(), 0);

-- ===================================================================
-- 男装 - T恤
-- ===================================================================

SET @tshirt_category_id = (SELECT id FROM category WHERE categoryName = 'T恤' AND parentId = (SELECT id FROM category WHERE categoryName = '男装' AND level = 2 LIMIT 1) LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('优衣库圆领T恤', 'UNIQLO 男装AIRism棉质圆领T恤', '100%纯棉，AIRism科技，吸湿速干，多色可选', @tshirt_category_id, 3, '优衣库', 'https://img.example.com/tshirt-uniqlo.jpg', '["https://img.example.com/tshirt-uniqlo-1.jpg"]', 79.00, 99.00, 500, 3200, '件', 0.18, '["纯棉","基础款","百搭"]', '{"颜色":["白色","黑色","灰色","蓝色"],"尺码":["S","M","L","XL","XXL"]}', 1, 1, 4, NOW(), NOW(), 0),

('海澜之家T恤', '海澜之家 男士纯色圆领短袖T恤', '精梳棉，柔软舒适，经典款式', @tshirt_category_id, 3, '海澜之家', 'https://img.example.com/tshirt-hlzj.jpg', '["https://img.example.com/tshirt-hlzj-1.jpg"]', 89.00, 119.00, 400, 2100, '件', 0.20, '["精梳棉","经典","商务休闲"]', '{"颜色":["白色","黑色","藏青色"],"尺码":["M","L","XL","2XL","3XL"]}', 1, 1, 4, NOW(), NOW(), 0),

('Nike运动T恤', 'Nike 男子训练T恤 Dri-FIT科技', 'Dri-FIT速干面料，吸湿排汗，运动首选', @tshirt_category_id, 3, 'Nike', 'https://img.example.com/tshirt-nike.jpg', '["https://img.example.com/tshirt-nike-1.jpg"]', 199.00, 249.00, 300, 1850, '件', 0.15, '["速干","运动","透气"]', '{"颜色":["黑色","白色","灰色","蓝色"],"尺码":["S","M","L","XL"]}', 1, 1, 4, NOW(), NOW(), 0),

('Adidas三叶草T恤', 'Adidas Originals 男子经典三叶草T恤', '经典Logo设计，纯棉面料，街头潮流', @tshirt_category_id, 3, 'Adidas', 'https://img.example.com/tshirt-adidas.jpg', '["https://img.example.com/tshirt-adidas-1.jpg"]', 179.00, 229.00, 350, 2420, '件', 0.17, '["潮流","经典","纯棉"]', '{"颜色":["黑色","白色","军绿色"],"尺码":["S","M","L","XL","XXL"]}', 1, 1, 4, NOW(), NOW(), 0),

('森马印花T恤', '森马 男士创意印花短袖T恤', '趣味印花设计，纯棉面料，青春活力', @tshirt_category_id, 3, '森马', 'https://img.example.com/tshirt-semir.jpg', '["https://img.example.com/tshirt-semir-1.jpg"]', 59.00, 79.00, 600, 4200, '件', 0.16, '["印花","青春","休闲"]', '{"颜色":["白色","黑色","卡其色"],"尺码":["M","L","XL","2XL"]}', 1, 1, 4, NOW(), NOW(), 0),

('HM纯色T恤', 'H&M 男士修身纯色T恤', '修身版型，弹力面料，时尚简约', @tshirt_category_id, 3, 'H&M', 'https://img.example.com/tshirt-hm.jpg', '["https://img.example.com/tshirt-hm-1.jpg"]', 69.00, 89.00, 450, 2800, '件', 0.14, '["修身","弹力","简约"]', '{"颜色":["黑色","白色","灰色","蓝色","绿色"],"尺码":["XS","S","M","L","XL"]}', 1, 1, 4, NOW(), NOW(), 0);

-- ===================================================================
-- 厨房电器 - 电饭煲
-- ===================================================================

SET @ricecooker_category_id = (SELECT id FROM category WHERE categoryName = '电饭煲' AND level = 3 LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('美的电饭煲', '美的 MB-WFS4029 4L IH电磁加热电饭煲', 'IH电磁加热，24小时预约，多功能烹饪', @ricecooker_category_id, 1, '美的', 'https://img.example.com/ricecooker-midea.jpg', '["https://img.example.com/ricecooker-midea-1.jpg"]', 299.00, 399.00, 200, 1520, '台', 4.2, '["IH加热","预约","4L"]', '{"容量":["3L","4L","5L"],"颜色":["白色","香槟金"]}', 1, 1, 2, NOW(), NOW(), 0),

('苏泊尔电饭煲', '苏泊尔 CFXB40FC832 4L 智能电饭煲', '球釜内胆，蜂窝加热，柴火饭口感', @ricecooker_category_id, 1, '苏泊尔', 'https://img.example.com/ricecooker-supor.jpg', '["https://img.example.com/ricecooker-supor-1.jpg"]', 249.00, 329.00, 250, 1880, '台', 3.8, '["球釜","智能","性价比"]', '{"容量":["3L","4L","5L"],"颜色":["白色","黑色"]}', 1, 1, 2, NOW(), NOW(), 0),

('九阳电饭煲', '九阳 F-40T801 4L 铁釜电饭煲', '铁釜内胆，多段IH加热，原汁原味', @ricecooker_category_id, 2, '九阳', 'https://img.example.com/ricecooker-joyoung.jpg', '["https://img.example.com/ricecooker-joyoung-1.jpg"]', 279.00, 349.00, 180, 1320, '台', 4.0, '["铁釜","IH加热","多功能"]', '{"容量":["3L","4L","5L"],"颜色":["白色","灰色"]}', 1, 1, 3, NOW(), NOW(), 0),

('小米电饭煲', '米家 IH电饭煲 4L', 'IH立体加热，精准控温，App智能控制', @ricecooker_category_id, 2, '小米', 'https://img.example.com/ricecooker-mi.jpg', '["https://img.example.com/ricecooker-mi-1.jpg"]', 399.00, 499.00, 150, 980, '台', 4.5, '["智能","App控制","IH"]', '{"容量":["3L","4L"],"颜色":["白色"]}', 1, 1, 3, NOW(), NOW(), 0),

('象印电饭煲', 'ZOJIRUSHI 象印 NP-HBQ10C 3L IH电饭煲', '日本进口，铂金厚釜，微压力煮饭', @ricecooker_category_id, 1, '象印', 'https://img.example.com/ricecooker-zojirushi.jpg', '["https://img.example.com/ricecooker-zojirushi-1.jpg"]', 1299.00, 1499.00, 80, 420, '台', 5.2, '["日本进口","铂金釜","高端"]', '{"容量":["3L","5L"],"颜色":["白色","棕色"]}', 1, 1, 2, NOW(), NOW(), 0),

('虎牌电饭煲', 'TIGER 虎牌 JKT-D10C 3L 土锅IH电饭煲', '土锅内胆，9层远红外加热，米饭香甜', @ricecooker_category_id, 1, '虎牌', 'https://img.example.com/ricecooker-tiger.jpg', '["https://img.example.com/ricecooker-tiger-1.jpg"]', 999.00, 1199.00, 100, 560, '台', 4.8, '["土锅","IH加热","进口"]', '{"容量":["3L","5L"],"颜色":["白色","红色"]}', 1, 1, 2, NOW(), NOW(), 0);

-- ===================================================================
-- 休闲零食 - 坚果炒货
-- ===================================================================

SET @nuts_category_id = (SELECT id FROM category WHERE categoryName = '坚果炒货' AND level = 3 LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('三只松鼠每日坚果', '三只松鼠 每日坚果 混合坚果30包装', '7种坚果+果干，营养均衡，独立小包装', @nuts_category_id, 1, '三只松鼠', 'https://img.example.com/nuts-sqs.jpg', '["https://img.example.com/nuts-sqs-1.jpg"]', 89.90, 129.90, 500, 8500, '盒', 0.75, '["混合坚果","每日","营养"]', '{"规格":["15包","30包"],"口味":["原味","蔓越莓味"]}', 1, 1, 2, NOW(), NOW(), 0),

('百草味夏威夷果', '百草味 奶油味夏威夷果 500g', '奶油味，颗颗饱满，精选大果', @nuts_category_id, 1, '百草味', 'https://img.example.com/nuts-bcw.jpg', '["https://img.example.com/nuts-bcw-1.jpg"]', 49.90, 69.90, 600, 6200, '袋', 0.50, '["夏威夷果","奶油味","大果"]', '{"规格":["250g","500g","1000g"],"口味":["奶油味","原味"]}', 1, 1, 2, NOW(), NOW(), 0),

('良品铺子碧根果', '良品铺子 奶香碧根果 160g', '美国进口碧根果，奶香浓郁，易剥壳', @nuts_category_id, 1, '良品铺子', 'https://img.example.com/nuts-lpz.jpg', '["https://img.example.com/nuts-lpz-1.jpg"]', 39.90, 55.90, 450, 5600, '袋', 0.16, '["碧根果","进口","奶香"]', '{"规格":["160g","360g"],"口味":["奶香味","原味"]}', 1, 1, 2, NOW(), NOW(), 0),

('洽洽小黄袋瓜子', '洽洽 小黄袋每日坚果 恰恰瓜子 500g', '原香葵花籽，颗粒饱满，经典口味', @nuts_category_id, 2, '洽洽', 'https://img.example.com/nuts-qq.jpg', '["https://img.example.com/nuts-qq-1.jpg"]', 19.90, 29.90, 800, 12000, '袋', 0.50, '["瓜子","经典","原香"]', '{"规格":["300g","500g","1000g"],"口味":["原香","焦糖","五香"]}', 1, 1, 3, NOW(), NOW(), 0),

('沃隆每日坚果', '沃隆 每日坚果 混合坚果25g*30袋', '8种坚果，无添加，新鲜锁鲜', @nuts_category_id, 1, '沃隆', 'https://img.example.com/nuts-wolong.jpg', '["https://img.example.com/nuts-wolong-1.jpg"]', 99.00, 139.00, 400, 5800, '盒', 0.75, '["无添加","新鲜","混合"]', '{"规格":["15袋","30袋"],"类型":["成人款","儿童款"]}', 1, 1, 2, NOW(), NOW(), 0),

('盐津铺子开心果', '盐津铺子 盐焗开心果 258g', '美国进口开心果，盐焗工艺，自然开口', @nuts_category_id, 2, '盐津铺子', 'https://img.example.com/nuts-yjpz.jpg', '["https://img.example.com/nuts-yjpz-1.jpg"]', 29.90, 45.90, 550, 6800, '袋', 0.26, '["开心果","进口","盐焗"]', '{"规格":["128g","258g","500g"],"口味":["盐焗味","原味"]}', 1, 1, 3, NOW(), NOW(), 0);

-- ===================================================================
-- 运动鞋
-- ===================================================================

SET @sportshoes_category_id = (SELECT id FROM category WHERE categoryName = '运动鞋' AND level = 3 LIMIT 1);

INSERT INTO product (productName, productTitle, productDescription, categoryId, shopId, brandName, mainImageUrl, imageUrls, price, originalPrice, stock, sales, unit, weight, tags, specifications, status, reviewStatus, userId, createTime, updateTime, isDelete)
VALUES
('Nike Air Max 270', 'Nike Air Max 270 男子运动鞋', 'Max Air气垫，舒适缓震，潮流百搭', @sportshoes_category_id, 3, 'Nike', 'https://img.example.com/shoes-nike270.jpg', '["https://img.example.com/shoes-nike270-1.jpg"]', 1099.00, 1299.00, 150, 880, '双', 0.65, '["气垫","潮流","百搭"]', '{"颜色":["黑色","白色","蓝色"],"尺码":["40","41","42","43","44","45"]}', 1, 1, 4, NOW(), NOW(), 0),

('Adidas UltraBoost', 'Adidas UltraBoost 22 男子跑步鞋', 'Boost缓震科技，能量回弹，专业跑步', @sportshoes_category_id, 3, 'Adidas', 'https://img.example.com/shoes-ub22.jpg', '["https://img.example.com/shoes-ub22-1.jpg"]', 1299.00, 1499.00, 120, 620, '双', 0.58, '["Boost","跑步","专业"]', '{"颜色":["黑色","白色","灰色"],"尺码":["40","41","42","43","44"]}', 1, 1, 4, NOW(), NOW(), 0),

('李宁音速10', '李宁 音速10 男子篮球鞋', '䨻科技，轻质缓震，实战篮球鞋', @sportshoes_category_id, 3, '李宁', 'https://img.example.com/shoes-lining.jpg', '["https://img.example.com/shoes-lining-1.jpg"]', 599.00, 799.00, 200, 1520, '双', 0.72, '["篮球","䨻科技","实战"]', '{"颜色":["黑色","红色","白色"],"尺码":["40","41","42","43","44","45"]}', 1, 1, 4, NOW(), NOW(), 0),

('安踏氢跑鞋', '安踏 氢跑6.0 男子跑步鞋', 'A-FLASHFOAM超轻科技，舒适透气', @sportshoes_category_id, 3, '安踏', 'https://img.example.com/shoes-anta.jpg', '["https://img.example.com/shoes-anta-1.jpg"]', 399.00, 599.00, 250, 2100, '双', 0.52, '["超轻","跑步","透气"]', '{"颜色":["黑色","白色","蓝色"],"尺码":["39","40","41","42","43","44"]}', 1, 1, 4, NOW(), NOW(), 0),

('New Balance 574', 'New Balance 574 经典复古跑鞋', '经典复古设计，ENCAP中底，舒适耐穿', @sportshoes_category_id, 3, 'New Balance', 'https://img.example.com/shoes-nb574.jpg', '["https://img.example.com/shoes-nb574-1.jpg"]', 599.00, 699.00, 180, 1320, '双', 0.68, '["复古","经典","百搭"]', '{"颜色":["灰色","蓝色","黑色"],"尺码":["40","41","42","43","44"]}', 1, 1, 4, NOW(), NOW(), 0),

('特步动力巢', '特步 动力巢X 男子跑步鞋', '动力巢X科技，缓震回弹，性价比之选', @sportshoes_category_id, 3, '特步', 'https://img.example.com/shoes-xtep.jpg', '["https://img.example.com/shoes-xtep-1.jpg"]', 299.00, 449.00, 300, 2800, '双', 0.60, '["性价比","跑步","缓震"]', '{"颜色":["黑色","白色","绿色"],"尺码":["39","40","41","42","43","44","45"]}', 1, 1, 4, NOW(), NOW(), 0);

-- ===================================================================
-- 统计查询
-- ===================================================================

SELECT '商品数据插入完成！' AS 提示,
       COUNT(*) AS 商品总数,
       COUNT(DISTINCT categoryId) AS 涉及分类数,
       SUM(stock) AS 总库存,
       SUM(sales) AS 总销量;

-- 按分类统计商品数量
SELECT
    c.categoryName AS 分类名称,
    c.level AS 级别,
    COUNT(p.id) AS 商品数量,
    SUM(p.stock) AS 总库存,
    SUM(p.sales) AS 总销量
FROM category c
LEFT JOIN product p ON p.categoryId = c.id
WHERE c.level = 3 AND p.id IS NOT NULL
GROUP BY c.id, c.categoryName, c.level
ORDER BY COUNT(p.id) DESC;