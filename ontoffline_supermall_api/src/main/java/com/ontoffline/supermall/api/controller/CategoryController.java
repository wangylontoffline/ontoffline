/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.app.dto.CategoryDto;
import com.ontoffline.supermall.bean.model.Category;
import com.ontoffline.supermall.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/category")
@Api(tags = "分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 分类信息列表接口
     */
    @GetMapping("/categoryInfo")
    @ApiOperation(value = "分类信息列表", notes = "获取所有的产品分类信息，顶级分类的parentId为0,默认为顶级分类")
    @ApiImplicitParam(name = "parentId", value = "分类ID", required = false, dataType = "Long")
    public ResponseEntity<List<CategoryDto>> categoryInfo(@RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        List<Category> categories = categoryService.listByParentId(parentId);
        List<CategoryDto> categoryDtos = mapperFacade.mapAsList(categories, CategoryDto.class);
        return ResponseEntity.ok(categoryDtos);
    }


}
