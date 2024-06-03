/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.Brand;

/**
 * @author wangylontoffline
 */

public interface BrandMapper extends BaseMapper<Brand> {

	Brand getByBrandName(String brandName);

	/**
	 * 根据分类id获取品牌列表
	 *
	 * @param categoryId 分类id
	 * @return
	 */
	List<Brand> listByCategoryId(@Param("categoryId")Long categoryId);
}