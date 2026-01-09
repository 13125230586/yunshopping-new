-- ===================================================================
-- 更新会员等级配置，添加欢迎优惠券和生日优惠券
-- ===================================================================

USE yunshopping;

-- 更新普通会员：赠送新人专享券(ID:1)
UPDATE member_level
SET welcomeCouponId = 1,
    birthdayCouponId = 6,
    description = '注册即可成为普通会员，享受98折优惠，开通即送新人优惠券'
WHERE levelCode = 'NORMAL';

-- 更新银卡会员：赠送时尚服装馆满减券(ID:5)
UPDATE member_level
SET welcomeCouponId = 5,
    birthdayCouponId = 6,
    description = '累计消费满1000元升级，享受95折优惠，升级赠送满减券'
WHERE levelCode = 'SILVER';

-- 更新金卡会员：赠送春节大促券(ID:2)
UPDATE member_level
SET welcomeCouponId = 2,
    birthdayCouponId = 4,
    description = '累计消费满5000元升级，享受92折优惠，升级赠送大额满减券'
WHERE levelCode = 'GOLD';

-- 更新钻石会员：赠送全场8折券(ID:3)
UPDATE member_level
SET welcomeCouponId = 3,
    birthdayCouponId = 4,
    description = '累计消费满20000元升级，享受88折优惠，升级赠送全场8折券'
WHERE levelCode = 'DIAMOND';

-- 查看更新后的结果
SELECT
    id,
    levelName AS 等级名称,
    levelCode AS 等级代码,
    discountRate AS 折扣率,
    welcomeCouponId AS 欢迎优惠券ID,
    birthdayCouponId AS 生日优惠券ID,
    description AS 描述
FROM member_level
WHERE isDelete = 0
ORDER BY sortOrder;