/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.controller;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ontoffline.supermall.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.model.Category;
import com.ontoffline.supermall.common.annotation.SysLog;
import com.ontoffline.supermall.service.BrandService;
import com.ontoffline.supermall.service.CategoryService;
import com.ontoffline.supermall.service.ProdPropService;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/prod/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 获取菜单页面的表
	 * @return
	 */
	@GetMapping("/table")
	@PreAuthorize("@pms.hasPermission('prod:category:page')")
	public ResponseEntity<List<Category>> table(){
		List<Category> categoryMenuList = categoryService.tableCategory(SecurityUtils.getSysUser().getShopId());
		return ResponseEntity.ok(categoryMenuList);
	}
	
	/**
	 * 获取分类信息
	 */
	@GetMapping("/info/{categoryId}")
	public ResponseEntity<Category> info(@PathVariable("categoryId") Long categoryId){
		Category category = categoryService.getById(categoryId);
		return ResponseEntity.ok(category);
	}
	
	
	
	/**
	 * 保存分类
	 */
	@SysLog("保存分类")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('prod:category:save')")
	public ResponseEntity<Void> save(@RequestBody Category category){
		category.setShopId(SecurityUtils.getSysUser().getShopId());
		category.setRecTime(new Date());
		categoryService.saveCategroy(category);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 更新分类
	 */
	@SysLog("更新分类")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('prod:category:update')")
	public ResponseEntity<String> update(@RequestBody Category category){
		category.setShopId(SecurityUtils.getSysUser().getShopId());
		if (Objects.equals(category.getParentId(),category.getCategoryId())) {
			return ResponseEntity.badRequest().body("分类的上级不能是自己本身");
		}
		categoryService.updateCategroy(category);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 删除分类
	 */
	@SysLog("删除分类")
	@DeleteMapping("/{categoryId}")
	@PreAuthorize("@pms.hasPermission('prod:category:delete')")
	public ResponseEntity<String> delete(@PathVariable("categoryId") Long categoryId){
		if (categoryService.count(new LambdaQueryWrapper<Category>().eq(Category::getParentId,categoryId)) >0) {
			return ResponseEntity.badRequest().body("请删除子分类，再删除该分类");
		}
		categoryService.deleteCategroy(categoryId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 所有的
	 */
	@GetMapping("/listCategory")
	public ResponseEntity<List<Category>> listCategory(){

		return ResponseEntity.ok(categoryService.list(new LambdaQueryWrapper<Category>()
														.le(Category::getGrade, 2)
														.eq(Category::getShopId, SecurityUtils.getSysUser().getShopId())
														.orderByAsc(Category::getSeq)));
	}
	
	/**
	 * 所有的产品分类
	 */
	@GetMapping("/listProdCategory")
	public ResponseEntity<List<Category>> listProdCategory(){
    	List<Category> categories = categoryService.treeSelect(SecurityUtils.getSysUser().getShopId(),2);
		return ResponseEntity.ok(categories);
	}
}
