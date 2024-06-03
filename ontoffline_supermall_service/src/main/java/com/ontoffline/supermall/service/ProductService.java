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
import com.ontoffline.supermall.bean.app.dto.ProductDto;
import com.ontoffline.supermall.bean.app.dto.TagProductDto;
import com.ontoffline.supermall.bean.dto.SearchProdDto;
import com.ontoffline.supermall.bean.model.Product;
import com.ontoffline.supermall.common.util.PageParam;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface ProductService extends IService<Product> {

    /**
     * 保存商品
     *
     * @param product 商品信息
     */
    void saveProduct(Product product);

    /**
     * 更新商品
     *
     * @param product 商品信息
     */
    void updateProduct(Product product, Product dbProduct);

    /**
     * 根据商品id获取商品信息
     *
     * @param prodId
     * @return
     */
    Product getProductByProdId(Long prodId);


    void removeProductByProdId(Long prodId);

    void removeProductCacheByProdId(Long prodId);

    IPage<ProductDto> pageByPutawayTime(IPage<ProductDto> page);

    IPage<ProductDto> pageByTagId(Page<ProductDto> page, Long tagId);

    IPage<ProductDto> moreBuyProdList(Page<ProductDto> page);

    IPage<ProductDto> pageByCategoryId(Page<ProductDto> page, Long categoryId);

    List<Product> listProdByCategoryId(Long categoryId);

    IPage<SearchProdDto> getSearchProdDtoPageByProdName(Page page, String prodName, Integer sort, Integer orderBy);

    List<TagProductDto> tagProdList();

    IPage<ProductDto> collectionProds(PageParam page, String userId);
}
