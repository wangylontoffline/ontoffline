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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ontoffline.supermall.bean.app.dto.ProductDto;
import com.ontoffline.supermall.bean.app.dto.TagProductDto;
import com.ontoffline.supermall.bean.dto.SearchProdDto;
import com.ontoffline.supermall.bean.model.Product;
import com.ontoffline.supermall.common.util.PageParam;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wangylontoffline
 */

public interface ProductMapper extends BaseMapper<Product> {

    int updateStocks(@Param("prod") Product product);

    Product getProductByProdNameAndShopId(@Param("prodName") String prodName, @Param("shopId") Long shopId);

    void returnStock(@Param("prodCollect") Map<Long, Integer> prodCollect);

    IPage<ProductDto> pageByPutawayTime(IPage<ProductDto> page);

    IPage<ProductDto> pageByTagId(Page<ProductDto> page, @Param("tagId") Long tagId);

    IPage<ProductDto> moreBuyProdList(Page<ProductDto> page);

    IPage<ProductDto> pageByCategoryId(Page<ProductDto> page, @Param("categoryId") Long categoryId);

    List<Product> listProdByCategoryId(@Param("categoryId") Long categoryId);

    IPage<SearchProdDto> getSearchProdDtoPageByProdName(Page page, @Param("prodName") String prodName, @Param("sort") Integer sort, @Param("orderBy") Integer orderBy);

    TagProductDto tagProdList(@Param("tagId") Long tagId);

    List<TagProductDto> tagProdList();

    /**
     * 查看店铺的所有活动商品
     */
    IPage<ProductDto> listByShopId(@Param("page") PageParam<ProductDto> page, @Param("shopId") Long shopId);

    /**
     * 通过优惠券适用商品类型、优惠券id、店铺id获取商品列表
     */
    IPage<ProductDto> listBySuitableProdTypeAndCouponIdAndShopId(@Param("page") PageParam<ProductDto> page, @Param("shopId") Long shopId, @Param("suitableProdType") Integer suitableProdType, @Param("couponId") Long couponId);

    /**
     * 获取用户的收藏商品列表
     *
     * @param userId
     * @return
     */
    IPage<ProductDto> collectionProds(@Param("page") PageParam page, @Param("userId") String userId);

}
