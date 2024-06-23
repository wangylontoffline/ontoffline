/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.listener;


import com.google.common.collect.Lists;
import com.ontoffline.supermall.bean.app.dto.*;
import com.ontoffline.supermall.bean.app.param.OrderParam;
import com.ontoffline.supermall.bean.event.ConfirmOrderEvent;
import com.ontoffline.supermall.bean.event.ShopCartEvent;
import com.ontoffline.supermall.bean.model.Product;
import com.ontoffline.supermall.bean.model.Sku;
import com.ontoffline.supermall.bean.model.UserAddr;
import com.ontoffline.supermall.bean.order.ConfirmOrderOrder;
import com.ontoffline.supermall.bean.order.ShopCartEventOrder;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.Arith;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.ProductService;
import com.ontoffline.supermall.service.SkuService;
import com.ontoffline.supermall.service.TransportManagerService;
import com.ontoffline.supermall.service.UserAddrService;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Component("defaultConfirmOrderListener")
@AllArgsConstructor
public class ConfirmOrderListener {

    private UserAddrService userAddrService;

    private TransportManagerService transportManagerService;

    private ProductService productService;

    private SkuService skuService;

    /**
     * 计算订单金额
     */
    @EventListener(ConfirmOrderEvent.class)
    @Order(ConfirmOrderOrder.DEFAULT)
    public void defaultConfirmOrderEvent(ConfirmOrderEvent event) {


        ShopCartOrderDto shopCartOrderDto = event.getShopCartOrderDto();

        OrderParam orderParam = event.getOrderParam();

        String userId = SecurityUtils.getUser().getUserId();

        // 订单的地址信息
        UserAddr userAddr = userAddrService.getUserAddrByUserId(orderParam.getAddrId(), userId);

        double total = 0.0;

        int totalCount = 0;

        double transfee = shopCartOrderDto.getTransfee();

        for (ShopCartItemDto shopCartItem : event.getShopCartItems()) {
            // 获取商品信息
            Product product = productService.getProductByProdId(shopCartItem.getProdId());
            // 获取sku信息
            Sku sku = skuService.getSkuBySkuId(shopCartItem.getSkuId());
            if (product == null || sku == null) {
                throw new OntofflineSupermallBindException("购物车包含无法识别的商品");
            }
            if (product.getStatus() != 1 || sku.getStatus() != 1) {
                throw new OntofflineSupermallBindException("商品[" + sku.getProdName() + "]已下架");
            }

            totalCount = shopCartItem.getProdCount() + totalCount;
            total = Arith.add(shopCartItem.getProductTotalAmount(), total);
            // 用户地址如果为空，则表示该用户从未设置过任何地址相关信息
//            if (userAddr != null) {
//                // 每个产品的运费相加
//                transfee = Arith.add(transfee, transportManagerService.calculateTransfee(shopCartItem, userAddr));
//            }

            shopCartItem.setActualTotal(shopCartItem.getProductTotalAmount());
            shopCartOrderDto.setActualTotal(Arith.add(total, transfee));
            shopCartOrderDto.setTotal(total);
            shopCartOrderDto.setTotalCount(totalCount);
            shopCartOrderDto.setTransfee(transfee);
        }
    }
}
