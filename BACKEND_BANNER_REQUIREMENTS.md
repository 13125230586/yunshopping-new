# 轮播图管理功能 - 后端需求文档

## 一、需求概述

前端需要实现首页轮播图的动态配置功能,支持管理员在后台配置轮播图的图片、标题、跳转链接等信息,并能够点击轮播图跳转到对应的商品列表或其他页面。

## 二、数据库设计

### 2.1 轮播图表 (banner)

| 字段名 | 类型 | 长度 | 是否必填 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | BIGINT | - | 是 | 自增 | 主键ID |
| title | VARCHAR | 100 | 是 | - | 轮播图标题 |
| image_url | VARCHAR | 500 | 是 | - | 轮播图图片地址 |
| link_type | VARCHAR | 20 | 是 | - | 链接类型: category(分类)、product(商品)、shop(店铺)、url(外链) |
| link_value | VARCHAR | 200 | 是 | - | 链接值:分类ID、商品ID、店铺ID或完整URL |
| sort_order | INT | - | 是 | 0 | 排序字段,数字越小越靠前 |
| status | TINYINT | - | 是 | 1 | 状态: 0-禁用 1-启用 |
| create_time | DATETIME | - | 是 | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | - | 是 | CURRENT_TIMESTAMP ON UPDATE | 更新时间 |
| is_deleted | TINYINT | - | 是 | 0 | 逻辑删除: 0-未删除 1-已删除 |

**索引设计:**
- 主键索引: `PRIMARY KEY (id)`
- 状态排序索引: `idx_status_sort (status, sort_order, is_deleted)`

## 三、后端接口需求

### 3.1 前台接口 (用户端)

#### 3.1.1 获取启用的轮播图列表

**接口路径**: `GET /api/banner/list`

**接口描述**: 获取所有已启用的轮播图列表,按照排序字段升序排列

**请求参数**: 无

**响应数据**:
```json
{
  "code": 0,
  "data": [
    {
      "id": 1,
      "title": "冬季新品上市",
      "imageUrl": "https://xxx.com/image1.jpg",
      "linkType": "category",
      "linkValue": "1",
      "sortOrder": 1,
      "status": 1,
      "createTime": "2024-01-01 00:00:00"
    },
    {
      "id": 2,
      "title": "品质家电",
      "imageUrl": "https://xxx.com/image2.jpg",
      "linkType": "product",
      "linkValue": "123",
      "sortOrder": 2,
      "status": 1,
      "createTime": "2024-01-02 00:00:00"
    }
  ],
  "message": "success"
}
```

**业务逻辑**:
- 只查询 status=1 且 is_deleted=0 的记录
- 按 sort_order 升序排列
- 返回所有字段(除is_deleted、update_time外)

### 3.2 后台管理接口 (管理员端)

#### 3.2.1 分页查询轮播图列表

**接口路径**: `POST /api/admin/banner/list`

**接口描述**: 分页查询所有轮播图,支持按标题、状态筛选

**请求参数**:
```json
{
  "current": 1,
  "pageSize": 10,
  "title": "新品",
  "status": 1
}
```

**响应数据**:
```json
{
  "code": 0,
  "data": {
    "records": [
      {
        "id": 1,
        "title": "冬季新品上市",
        "imageUrl": "https://xxx.com/image1.jpg",
        "linkType": "category",
        "linkValue": "1",
        "sortOrder": 1,
        "status": 1,
        "createTime": "2024-01-01 00:00:00",
        "updateTime": "2024-01-01 00:00:00"
      }
    ],
    "total": 10,
    "current": 1,
    "size": 10
  },
  "message": "success"
}
```

#### 3.2.2 新增轮播图

**接口路径**: `POST /api/admin/banner/add`

**接口描述**: 新增一条轮播图记录

**请求参数**:
```json
{
  "title": "冬季新品上市",
  "imageUrl": "https://xxx.com/image1.jpg",
  "linkType": "category",
  "linkValue": "1",
  "sortOrder": 1,
  "status": 1
}
```

**参数校验**:
- title: 必填,长度1-100
- imageUrl: 必填,长度1-500,需为有效的URL格式
- linkType: 必填,枚举值: category、product、shop、url
- linkValue: 必填,长度1-200
- sortOrder: 必填,大于等于0
- status: 必填,枚举值: 0、1

**响应数据**:
```json
{
  "code": 0,
  "data": 1,
  "message": "添加成功"
}
```

**业务逻辑**:
- 根据linkType验证linkValue的有效性:
  - category: 验证分类ID是否存在
  - product: 验证商品ID是否存在
  - shop: 验证店铺ID是否存在
  - url: 验证URL格式是否正确

#### 3.2.3 修改轮播图

**接口路径**: `POST /api/admin/banner/update`

**接口描述**: 修改轮播图信息

**请求参数**:
```json
{
  "id": 1,
  "title": "冬季新品上市",
  "imageUrl": "https://xxx.com/image1.jpg",
  "linkType": "category",
  "linkValue": "1",
  "sortOrder": 1,
  "status": 1
}
```

**参数校验**: 同新增接口,额外增加:
- id: 必填

**响应数据**:
```json
{
  "code": 0,
  "data": true,
  "message": "修改成功"
}
```

**业务逻辑**:
- 验证轮播图ID是否存在
- 同新增接口的linkValue验证逻辑
- 更新update_time字段

#### 3.2.4 删除轮播图

**接口路径**: `POST /api/admin/banner/delete`

**接口描述**: 删除轮播图(逻辑删除)

**请求参数**:
```json
{
  "id": 1
}
```

**响应数据**:
```json
{
  "code": 0,
  "data": true,
  "message": "删除成功"
}
```

**业务逻辑**:
- 逻辑删除,设置 is_deleted=1
- 更新update_time字段

#### 3.2.5 修改轮播图状态

**接口路径**: `POST /api/admin/banner/status`

**接口描述**: 启用或禁用轮播图

**请求参数**:
```json
{
  "id": 1,
  "status": 1
}
```

**参数校验**:
- id: 必填
- status: 必填,枚举值: 0、1

**响应数据**:
```json
{
  "code": 0,
  "data": true,
  "message": "状态修改成功"
}
```

#### 3.2.6 批量修改轮播图排序

**接口路径**: `POST /api/admin/banner/sort`

**接口描述**: 批量修改轮播图的排序值

**请求参数**:
```json
{
  "banners": [
    {
      "id": 1,
      "sortOrder": 1
    },
    {
      "id": 2,
      "sortOrder": 2
    }
  ]
}
```

**响应数据**:
```json
{
  "code": 0,
  "data": true,
  "message": "排序修改成功"
}
```

**业务逻辑**:
- 批量更新各个轮播图的sort_order字段
- 建议使用事务保证数据一致性

## 四、权限控制

### 4.1 前台接口
- `/api/banner/list`: 无需登录即可访问

### 4.2 后台管理接口
- 所有 `/api/admin/banner/*` 接口需要管理员权限(userRole='admin')
- 建议使用拦截器统一验证管理员权限

## 五、日志规范

按照项目规范,关键操作需要记录日志:

```java
// 新增轮播图
log.info("新增轮播图 title:{} linkType:{} linkValue:{}", title, linkType, linkValue);

// 修改轮播图
log.info("修改轮播图 id:{} title:{}", id, title);

// 删除轮播图
log.info("删除轮播图 id:{}", id);

// 修改状态
log.info("修改轮播图状态 id:{} status:{}", id, status);

// 批量排序
log.info("批量修改轮播图排序 count:{}", banners.size());
```

## 六、前端对接说明

### 6.1 前端已完成的工作

1. 创建了轮播图数据模型 (`src/types/models.ts`):
```typescript
export interface Banner {
  id: number
  title: string
  imageUrl: string
  linkType: 'category' | 'product' | 'shop' | 'url'
  linkValue: string
  sortOrder: number
  status: 0 | 1
  createTime: string
}
```

2. 创建了轮播图Store (`src/stores/banner.ts`):
- 提供了 `fetchBannerList()` 方法获取轮播图列表
- 目前使用临时静态数据,等待后端接口实现

3. 首页轮播图展示 (`src/views/home/IndexPage.vue`):
- 已实现轮播图动态渲染
- 已实现点击跳转逻辑:
  - linkType='category': 跳转到商品列表页并传递categoryId参数
  - linkType='product': 跳转到商品详情页
  - linkType='shop': 跳转到店铺页
  - linkType='url': 新窗口打开外链

### 6.2 后端完成后的对接步骤

1. 后端实现所有接口后,运行 `npm run openapi` 生成API接口函数
2. 修改 `src/stores/banner.ts` 文件,替换临时数据为真实API调用:
```typescript
import { listBannerUsingGet } from '@/api/bannerController'

async function fetchBannerList() {
  loading.value = true
  try {
    const res = await listBannerUsingGet()
    if (res.code === 0 && res.data) {
      bannerList.value = res.data
    }
  } finally {
    loading.value = false
  }
}
```

## 七、注意事项

1. 图片存储建议使用对象存储服务(如阿里云OSS、腾讯云COS)
2. 轮播图数量建议控制在3-5张,避免加载过慢
3. 建议对轮播图列表接口增加缓存,减少数据库压力
4. linkValue的验证需要确保关联数据的有效性
5. 排序字段建议设置合理的默认值,避免冲突
6. 删除操作建议使用逻辑删除,保留历史数据便于追溯

## 八、实现优先级

**P0 (必须实现)**:
- 数据库表创建
- 前台获取轮播图列表接口 (`GET /api/banner/list`)

**P1 (尽快实现)**:
- 后台分页查询接口
- 后台新增、修改、删除接口
- 后台修改状态接口

**P2 (可后续优化)**:
- 批量修改排序接口
- 图片上传功能优化
- 接口缓存优化