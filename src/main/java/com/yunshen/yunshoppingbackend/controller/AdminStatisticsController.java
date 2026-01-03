package com.yunshen.yunshoppingbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.constant.UserConstant;
import com.yunshen.yunshoppingbackend.model.entity.Order;
import com.yunshen.yunshoppingbackend.model.entity.Product;
import com.yunshen.yunshoppingbackend.model.entity.Shop;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.mapper.OrderMapper;
import com.yunshen.yunshoppingbackend.mapper.ProductMapper;
import com.yunshen.yunshoppingbackend.mapper.ShopMapper;
import com.yunshen.yunshoppingbackend.mapper.UserMapper;
import com.yunshen.yunshoppingbackend.model.vo.AdminStatisticsVO;
import com.yunshen.yunshoppingbackend.model.vo.DistributionDataVO;
import com.yunshen.yunshoppingbackend.model.vo.TrendDataVO;
import com.yunshen.yunshoppingbackend.service.OrderService;
import com.yunshen.yunshoppingbackend.service.ProductService;
import com.yunshen.yunshoppingbackend.service.ShopService;
import com.yunshen.yunshoppingbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理后台数据统计接口
 */
@Api(tags = "管理后台数据统计接口")
@RestController
@RequestMapping("/admin/statistics")
@Slf4j
public class AdminStatisticsController {

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    @Resource
    private ProductService productService;

    @Resource
    private ShopService shopService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ShopMapper shopMapper;

    /**
     * 获取数据概览统计
     */
    @ApiOperation("获取数据概览统计")
    @GetMapping("/overview")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<AdminStatisticsVO> getOverviewStatistics() {
        AdminStatisticsVO statisticsVO = new AdminStatisticsVO();

        // 统计总用户数
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        long totalUsers = userService.count(userQueryWrapper);
        statisticsVO.setTotalUsers(totalUsers);

        // 统计总订单数
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        long totalOrders = orderService.count(orderQueryWrapper);
        statisticsVO.setTotalOrders(totalOrders);

        // 统计待审核商品数
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("reviewStatus", 0);
        long pendingProducts = productService.count(productQueryWrapper);
        statisticsVO.setPendingProducts(pendingProducts);

        // 统计待审核店铺数
        QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
        shopQueryWrapper.eq("reviewStatus", 0);
        long pendingShops = shopService.count(shopQueryWrapper);
        statisticsVO.setPendingShops(pendingShops);

        log.info("管理后台数据统计 totalUsers:{} totalOrders:{} pendingProducts:{} pendingShops:{}",
                totalUsers, totalOrders, pendingProducts, pendingShops);

        return ResultUtils.success(statisticsVO);
    }

    /**
     * 获取用户总数
     */
    @ApiOperation("获取用户总数")
    @GetMapping("/users/count")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> getUserCount() {
        long count = userService.count();
        return ResultUtils.success(count);
    }

    /**
     * 获取订单总数
     */
    @ApiOperation("获取订单总数")
    @GetMapping("/orders/count")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> getOrderCount() {
        long count = orderService.count();
        return ResultUtils.success(count);
    }

    /**
     * 获取待审核商品数
     */
    @ApiOperation("获取待审核商品数")
    @GetMapping("/products/pending")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> getPendingProductCount() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewStatus", 0);
        long count = productService.count(queryWrapper);
        return ResultUtils.success(count);
    }

    /**
     * 获取待审核店铺数
     */
    @ApiOperation("获取待审核店铺数")
    @GetMapping("/shops/pending")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> getPendingShopCount() {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewStatus", 0);
        long count = shopService.count(queryWrapper);
        return ResultUtils.success(count);
    }

    /**
     * 订单趋势统计
     */
    @ApiOperation("订单趋势统计")
    @GetMapping("/orders/trend")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<TrendDataVO>> getOrderTrend(@RequestParam(defaultValue = "7") Integer days) {
        List<TrendDataVO> trendData = orderMapper.getOrderTrend(days);
        log.info("订单趋势统计 days:{} count:{}", days, trendData.size());
        return ResultUtils.success(trendData);
    }

    /**
     * 订单状态分布统计
     */
    @ApiOperation("订单状态分布统计")
    @GetMapping("/orders/status-distribution")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<DistributionDataVO>> getOrderStatusDistribution() {
        List<DistributionDataVO> distributionData = orderMapper.
                getOrderStatusDistribution();
        log.info("订单状态分布统计 count:{}", distributionData.size());
        return ResultUtils.success(distributionData);
    }

    /**
     * 销售额趋势统计
     */
    @ApiOperation("销售额趋势统计")
    @GetMapping("/sales/trend")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<TrendDataVO>> getSalesTrend(@RequestParam(defaultValue = "7") Integer days) {
        List<TrendDataVO> trendData = orderMapper.getSalesTrend(days);
        log.info("销售额趋势统计 days:{} count:{}", days, trendData.size());
        return ResultUtils.success(trendData);
    }

    /**
     * 用户角色分布统计
     */
    @ApiOperation("用户角色分布统计")
    @GetMapping("/users/role-distribution")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<DistributionDataVO>> getUserRoleDistribution() {
        List<DistributionDataVO> distributionData = userMapper.getUserRoleDistribution();
        log.info("用户角色分布统计 count:{}", distributionData.size());
        return ResultUtils.success(distributionData);
    }

    /**
     * 商品分类分布统计
     */
    @ApiOperation("商品分类分布统计")
    @GetMapping("/products/category-distribution")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<DistributionDataVO>> getProductCategoryDistribution() {
        List<DistributionDataVO> distributionData = productMapper.getProductCategoryDistribution();
        log.info("商品分类分布统计 count:{}", distributionData.size());
        return ResultUtils.success(distributionData);
    }

    /**
     * 店铺等级分布统计
     */
    @ApiOperation("店铺等级分布统计")
    @GetMapping("/shops/level-distribution")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<DistributionDataVO>> getShopLevelDistribution() {
        List<DistributionDataVO> distributionData = shopMapper.getShopLevelDistribution();
        log.info("店铺等级分布统计 count:{}", distributionData.size());
        return ResultUtils.success(distributionData);
    }
}