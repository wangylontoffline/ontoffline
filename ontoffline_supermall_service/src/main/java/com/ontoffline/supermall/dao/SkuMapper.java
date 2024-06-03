/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.Sku;

/**
 * @author wangylontoffline
 */

public interface SkuMapper extends BaseMapper<Sku> {

	/**
	 * 批量插入sku
	 * @param prodId 商品id
	 * @param skus sku列表
	 */
	void insertBatch(@Param("prodId") Long prodId, @Param("skuList") List<Sku> skuList);

	List<Sku> listByProdId(Long prodId);

	int updateStocks(@Param("sku") Sku sku);
	
	void deleteByProdId(@Param("prodId") Long prodId);

	void returnStock(@Param("skuCollect") Map<Long, Integer> skuCollect);

}