package com.yunshen.yunshoppingbackend.model.dto.banner;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图批量排序请求
 */
@Data
public class BannerSortRequest implements Serializable {

    /**
     * 轮播图排序列表
     */
    private List<BannerSortItem> banners;

    @Data
    public static class BannerSortItem implements Serializable {
        /**
         * 主键ID
         */
        private Long id;

        /**
         * 排序值
         */
        private Integer sortOrder;

        private static final long serialVersionUID = 1L;
    }

    private static final long serialVersionUID = 1L;
}