/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.Sku;
import com.ontoffline.supermall.dao.SkuMapper;
import com.ontoffline.supermall.service.SkuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

	@Override
	@Cacheable(cacheNames = "skuList", key = "#prodId")
	public List<Sku> listByProdId(Long prodId) {
		return skuMapper.listByProdId(prodId);
	}

	@Override
	@Cacheable(cacheNames = "sku", key = "#skuId")
	public Sku getSkuBySkuId(Long skuId) {
		return skuMapper.selectById(skuId);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "sku", key = "#skuId"),
			@CacheEvict(cacheNames = "skuList", key = "#prodId")
	})
	public void removeSkuCacheBySkuId(Long skuId,Long prodId) {

	}

}
