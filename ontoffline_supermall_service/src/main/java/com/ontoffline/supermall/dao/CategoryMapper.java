/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.Category;

/**
 * @author wangylontoffline
 */

public interface CategoryMapper extends BaseMapper<Category> {

	List<Category> listByParentId(Long parentId);

	List<Category> tableCategory(Long shopId);

	List<Category> listCategoryAndProdByShopId(Long shopId);
}