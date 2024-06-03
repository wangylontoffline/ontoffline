/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.Sku;

import org.springframework.cache.annotation.CacheEvict;

/**
 * @author wangylontoffline
 */

public interface SkuService extends IService<Sku> {

	/**
	 * 根据商品id获取商品中的sku列表（将会被缓存起来）
	 * @param prodId 商品id
	 * @return sku列表
	 */
	List<Sku> listByProdId(Long prodId);

	/**
	 * 根据skuId获取sku信息（将会被缓存起来）
	 * @param skuId
	 * @return
	 */
	Sku getSkuBySkuId(Long skuId);

	void removeSkuCacheBySkuId(Long skuId,Long prodId);
}
