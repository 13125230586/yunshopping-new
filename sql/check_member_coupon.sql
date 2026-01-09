-- 检查会员等级配置
SELECT
    id,
    levelName AS 等级名称,
    levelCode AS 等级代码,
    discountRate AS 折扣率,
    welcomeCouponId AS 欢迎优惠券ID,
    birthdayCouponId AS 生日优惠券ID,
    createTime AS 创建时间
FROM member_level
WHERE isDelete = 0
ORDER BY sortOrder;

-- 检查是否有用户开通了会员
SELECT
    m.id AS 会员ID,
    m.userId AS 用户ID,
    u.userName AS 用户名,
    m.levelId AS 等级ID,
    ml.levelName AS 等级名称,
    m.status AS 状态,
    m.activateTime AS 开通时间
FROM member m
LEFT JOIN user u ON m.userId = u.id
LEFT JOIN member_level ml ON m.levelId = ml.id
WHERE m.isDelete = 0;

-- 检查用户领取的优惠券
SELECT
    uc.id,
    uc.userId AS 用户ID,
    u.userName AS 用户名,
    uc.couponId AS 优惠券ID,
    c.couponName AS 优惠券名称,
    uc.status AS 状态,
    CASE uc.status
        WHEN 0 THEN '未使用'
        WHEN 1 THEN '已使用'
        WHEN 2 THEN '已过期'
    END AS 状态描述,
    uc.receiveTime AS 领取时间,
    uc.useTime AS 使用时间
FROM user_coupon uc
LEFT JOIN user u ON uc.userId = u.id
LEFT JOIN coupon c ON uc.couponId = c.id
ORDER BY uc.receiveTime DESC;
