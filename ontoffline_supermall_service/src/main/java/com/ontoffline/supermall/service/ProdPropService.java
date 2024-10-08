/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.ProdProp;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface ProdPropService extends IService<ProdProp> {

	/**
	 * 获取属性与属性值
	 * @param prodProp
	 * @param page
	 * @return
	 */
	IPage<ProdProp> pagePropAndValue(ProdProp prodProp, Page<ProdProp> page);

	/**
	 * 保存属性、属性值
	 * @param prodProp
	 */
	void saveProdPropAndValues(ProdProp prodProp);

	/**
	 * 更新属性、属性值
	 * @param prodProp
	 */
	void updateProdPropAndValues(ProdProp prodProp);

	/**
	 * 删除属性、属性值
	 * 如果propRule为2，同时删除分类与属性值之间的关联关系
	 */
	void deleteProdPropAndValues(Long propId,Integer propRule,Long shopId);

	/**
	 * 根据分类id获取分类所关联的属性
	 * @param categoryId
	 * @return
	 */
	List<ProdProp> listByCategoryId(Long categoryId);

}
