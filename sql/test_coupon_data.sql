-- 测试优惠券数据是否存在
SELECT '优惠券数据检查' AS 检查项;
SELECT
    id,
    couponName AS 优惠券名称,
    CASE couponType
        WHEN 1 THEN '满减券'
        WHEN 2 THEN '折扣券'
        WHEN 3 THEN '新人券'
    END AS 券类型,
    discountAmount AS 优惠金额,
    discountRate AS 折扣率,
    minAmount AS 最低消费,
    status AS 状态,
    createTime AS 创建时间
FROM coupon
WHERE isDelete = 0
ORDER BY createTime DESC;
