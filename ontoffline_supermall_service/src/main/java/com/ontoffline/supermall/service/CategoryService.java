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
import com.ontoffline.supermall.bean.app.dto.CategoryDto;
import com.ontoffline.supermall.bean.model.Category;

/**
 * @author wangylontoffline
 */

public interface CategoryService extends IService<Category> {

	/**
	 * 根据parentId获取分类
	 * @param parentId 0 一级分类
	 * @return
	 */
	List<Category> listByParentId(Long parentId);

	/**
	 * 获取用于页面表单展现的category列表，根据seq排序
	 * @return
	 */
	List<Category> tableCategory(Long shopId);

	/**
	 * 保存分类、品牌、参数
	 * @return
	 */
	void saveCategroy(Category category);

	/**
	 * 修改分类、品牌、参数
	 * @return
	 */
	void updateCategroy(Category category);

	/**
	 * 删除分类、品牌、参数 以及分类对应的图片
	 * @param qiniu 
	 * @return
	 */
	void deleteCategroy(Long categoryId);

	/**
	 * 根据店铺id和层级，获取商品分类树
	 * @param shopId
	 * @param grade
	 * @return
	 */
	List<Category> treeSelect(Long shopId,int grade);

	List<CategoryDto> listCategoryDtoByShopId(Long shopId);

//	List<CategoryDto> listCategoryDtoByParentId(Long parentId);

}
