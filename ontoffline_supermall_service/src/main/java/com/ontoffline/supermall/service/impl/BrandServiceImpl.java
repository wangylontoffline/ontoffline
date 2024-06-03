/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.Brand;
import com.ontoffline.supermall.dao.BrandMapper;
import com.ontoffline.supermall.dao.CategoryBrandMapper;
import com.ontoffline.supermall.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;
    
	@Override
	public Brand getByBrandName(String brandName) {
		return brandMapper.getByBrandName(brandName);
	}

	@Override
	public void deleteByBrand(Long brandId) {
		brandMapper.deleteById(brandId);
		categoryBrandMapper.deleteByBrandId(brandId);
	}

	@Override
	public List<Brand> listByCategoryId(Long categoryId) {
		return brandMapper.listByCategoryId(categoryId);
	}

}
