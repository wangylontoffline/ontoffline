/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.bean.dto.HotSearchDto;
import com.ontoffline.supermall.bean.dto.SearchProdDto;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.service.HotSearchService;
import com.ontoffline.supermall.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/search")
@Api(tags = "搜索接口")
@AllArgsConstructor
public class SearchController {

    private HotSearchService hotSearchService;

    private ProductService productService;

    @GetMapping("/hotSearchByShopId")
    @ApiOperation(value = "查看店铺热搜", notes = "根据店铺id,热搜数量获取热搜")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "number", value = "取数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "是否按照顺序(0 否 1是)", required = false, dataType = "Integer"),
    })
    public ResponseEntity<List<HotSearchDto>> hotSearchByShopId(Long shopId,Integer number,Integer sort) {
        List<HotSearchDto> list = hotSearchService.getHotSearchDtoByshopId(shopId);

        return getListResponseEntity(number, sort, list);
    }

    @GetMapping("/hotSearch")
    @ApiOperation(value = "查看全局热搜", notes = "根据店铺id,热搜数量获取热搜")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "取数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "是否按照顺序(0 否 1是)", required = false, dataType = "Integer"),
    })
    public ResponseEntity<List<HotSearchDto>> hotSearch(Integer number,Integer sort) {
        List<HotSearchDto> list = hotSearchService.getHotSearchDtoByshopId(0L);
        return getListResponseEntity(number, sort, list);
    }

    private ResponseEntity<List<HotSearchDto>> getListResponseEntity(Integer number, Integer sort, List<HotSearchDto> list) {
        if(sort == null || sort == 0){
            Collections.shuffle(list);
        }
        if(!CollectionUtil.isNotEmpty(list) || list.size()< number){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(list.subList(0, number));
    }

    @GetMapping("/searchProdPage")
    @ApiOperation(value = "分页排序搜索商品", notes = "根据商品名搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodName", value = "商品名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序(0 默认排序 1销量排序 2价格排序)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", value = "排序(0升序 1降序)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Long"),
    })
    public ResponseEntity<IPage<SearchProdDto>> searchProdPage(PageParam page, String prodName, Integer sort, Integer orderBy, Long shopId) {

        return ResponseEntity.ok(productService.getSearchProdDtoPageByProdName(page,prodName,sort,orderBy));
    }




}
