/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.bean.model.Product;
import com.ontoffline.supermall.bean.model.Sku;
import com.ontoffline.supermall.bean.param.ProductParam;
import com.ontoffline.supermall.common.enums.OntofflineHttpStatus;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.Json;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.BasketService;
import com.ontoffline.supermall.service.ProdTagReferenceService;
import com.ontoffline.supermall.service.ProductService;
import com.ontoffline.supermall.service.SkuService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author wangylontoffline
 */

@Controller
@RequestMapping("/prod/prod")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private SkuService skuService;

    @Autowired
    private ProdTagReferenceService prodTagReferenceService;

    @Autowired
    private BasketService basketService;

    /**
     * 分页获取商品信息
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('prod:prod:page')")
    public ResponseEntity<IPage<Product>> page(ProductParam product, PageParam<Product> page) {
        IPage<Product> products = productService.page(page,
                new LambdaQueryWrapper<Product>()
                        .like(StrUtil.isNotBlank(product.getProdName()), Product::getProdName, product.getProdName())
                        .eq(Product::getShopId, SecurityUtils.getSysUser().getShopId())
                        .eq(product.getStatus() != null, Product::getStatus, product.getStatus())
                        .orderByDesc(Product::getPutawayTime));
        return ResponseEntity.ok(products);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{prodId}")
    @PreAuthorize("@pms.hasPermission('prod:prod:info')")
    public ResponseEntity<Product> info(@PathVariable("prodId") Long prodId) {
        Product prod = productService.getProductByProdId(prodId);
        if (!Objects.equals(prod.getShopId(), SecurityUtils.getSysUser().getShopId())) {
            throw new OntofflineSupermallBindException("没有权限获取该商品规格信息");
        }
        List<Sku> skuList = skuService.listByProdId(prodId);
        prod.setSkuList(skuList);

        //获取分组标签
        List<Long> listTagId = prodTagReferenceService.listTagIdByProdId(prodId);
        prod.setTagList(listTagId);
        return ResponseEntity.ok(prod);
    }

    /**
     * 保存
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('prod:prod:save')")
    public ResponseEntity<String> save(@Valid @RequestBody ProductParam productParam) {
        checkParam(productParam);

        Product product = mapperFacade.map(productParam, Product.class);
        product.setDeliveryMode(Json.toJsonString(productParam.getDeliveryModeVo()));
        product.setShopId(SecurityUtils.getSysUser().getShopId());
        product.setUpdateTime(new Date());
        if (product.getStatus() == 1) {
            product.setPutawayTime(new Date());
        }
        product.setCreateTime(new Date());
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('prod:prod:update')")
    public ResponseEntity<String> update(@Valid @RequestBody ProductParam productParam) {
        checkParam(productParam);
        Product dbProduct = productService.getProductByProdId(productParam.getProdId());
        if (!Objects.equals(dbProduct.getShopId(), SecurityUtils.getSysUser().getShopId())) {
            return ResponseEntity.badRequest().body("无法修改非本店铺商品信息");
        }

        List<Sku> dbSkus = skuService.listByProdId(dbProduct.getProdId());


        Product product = mapperFacade.map(productParam, Product.class);
        product.setDeliveryMode(Json.toJsonString(productParam.getDeliveryModeVo()));
        product.setUpdateTime(new Date());

        if (dbProduct.getStatus() == 0 || productParam.getStatus() == 1) {
            product.setPutawayTime(new Date());
        }
        dbProduct.setSkuList(dbSkus);
        productService.updateProduct(product, dbProduct);


        List<String> userIds = basketService.listUserIdByProdId(product.getProdId());

        for (String userId : userIds) {
            basketService.removeShopCartItemsCacheByUserId(userId);
        }
        for (Sku sku : dbSkus) {
            skuService.removeSkuCacheBySkuId(sku.getSkuId(), sku.getProdId());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{prodId}")
    @PreAuthorize("@pms.hasPermission('prod:prod:delete')")
    public ResponseEntity<Void> delete(@PathVariable("prodId") Long prodId) {
        Product dbProduct = productService.getProductByProdId(prodId);
        if (!Objects.equals(dbProduct.getShopId(), SecurityUtils.getSysUser().getShopId())) {
            throw new OntofflineSupermallBindException("无法获取非本店铺商品信息");
        }
        List<Sku> dbSkus = skuService.listByProdId(dbProduct.getProdId());
        // 删除商品
        productService.removeProductByProdId(prodId);

        for (Sku sku : dbSkus) {
            skuService.removeSkuCacheBySkuId(sku.getSkuId(), sku.getProdId());
        }


        List<String> userIds = basketService.listUserIdByProdId(prodId);

        for (String userId : userIds) {
            basketService.removeShopCartItemsCacheByUserId(userId);
        }

        return ResponseEntity.ok().build();
    }


    /**
     * 更新商品状态
     */
    @PutMapping("/prodStatus")
    @PreAuthorize("@pms.hasPermission('prod:prod:status')")
    public ResponseEntity<Void> shopStatus(@RequestParam Long prodId, @RequestParam Integer prodStatus) {
        Product product = new Product();
        product.setProdId(prodId);
        product.setStatus(prodStatus);
        if (prodStatus == 1) {
            product.setPutawayTime(new Date());
        }
        productService.updateById(product);
        productService.removeProductCacheByProdId(prodId);
        List<String> userIds = basketService.listUserIdByProdId(prodId);

        for (String userId : userIds) {
            basketService.removeShopCartItemsCacheByUserId(userId);
        }
        return ResponseEntity.ok().build();
    }

    private void checkParam(ProductParam productParam) {
        if (CollectionUtil.isEmpty(productParam.getTagList())) {
            throw new OntofflineSupermallBindException("请选择产品分组");
        }

        Product.DeliveryModeVO deliveryMode = productParam.getDeliveryModeVo();
        boolean hasDeliverMode = deliveryMode != null
                && (deliveryMode.getHasShopDelivery() || deliveryMode.getHasUserPickUp());
        if (!hasDeliverMode) {
            throw new OntofflineSupermallBindException("请选择配送方式");
        }
        List<Sku> skuList = productParam.getSkuList();
        boolean isAllUnUse = true;
        for (Sku sku : skuList) {
            if (sku.getStatus() == 1) {
                isAllUnUse = false;
            }
        }
//        if (isAllUnUse) {
//            throw new SupermallBindException("至少要启用一种商品规格");
//        }
    }
}
