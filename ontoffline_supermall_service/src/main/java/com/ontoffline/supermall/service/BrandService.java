/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.Brand;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface BrandService extends IService<Brand> {

	/**
	 * 根据品牌名称获取该品牌
	 * @param brandName
	 * @return
	 */
	Brand getByBrandName(String brandName);

	/**
	 * 删除品牌，同时删除品牌与分类之间的关联关系
	 * @param brandId
	 */
	void deleteByBrand(Long brandId);

	/**
	 * 根据分类id获取品牌列表
	 * @param categoryId 分类id
	 * @return
	 */
	List<Brand> listByCategoryId(Long categoryId);

}
