/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.listener;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDiscountDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderMergerDto;
import com.ontoffline.supermall.bean.enums.OrderStatus;
import com.ontoffline.supermall.bean.event.SubmitOrderEvent;
import com.ontoffline.supermall.bean.model.*;
import com.ontoffline.supermall.bean.order.SubmitOrderOrder;
import com.ontoffline.supermall.common.exception.YamiShopBindException;
import com.ontoffline.supermall.common.util.Arith;
import com.ontoffline.supermall.dao.*;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.ProductService;
import com.ontoffline.supermall.service.SkuService;
import com.ontoffline.supermall.service.UserAddrOrderService;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wangylontoffline
 */

@Component("defaultSubmitOrderListener")
@AllArgsConstructor
public class SubmitOrderListener {


    private MapperFacade mapperFacade;

    private UserAddrOrderService userAddrOrderService;

    private ProductService productService;

    private SkuService skuService;

    private Snowflake snowflake;

    private OrderItemMapper orderItemMapper;

    private SkuMapper skuMapper;

    private ProductMapper productMapper;

    private OrderMapper orderMapper;

    private OrderSettlementMapper orderSettlementMapper;

    private BasketMapper basketMapper;

    /**
     * 计算订单金额
     */
    @EventListener(SubmitOrderEvent.class)
    @Order(SubmitOrderOrder.DEFAULT)
    public void defaultSubmitOrderListener(SubmitOrderEvent event) {
        Date now = new Date();
        String userId = SecurityUtils.getUser().getUserId();

        ShopCartOrderMergerDto mergerOrder = event.getMergerOrder();

        // 订单商品参数
        List<ShopCartOrderDto> shopCartOrders = mergerOrder.getShopCartOrders();

        List<Long> basketIds = new ArrayList<>();
        // 商品skuid为key 需要更新的sku为value的map
        Map<Long, Sku> skuStocksMap = new HashMap<>(16);
        // 商品productid为key 需要更新的product为value的map
        Map<Long, Product> prodStocksMap = new HashMap<>(16);

        // 把订单地址保存到数据库
        UserAddrOrder userAddrOrder = mapperFacade.map(mergerOrder.getUserAddr(), UserAddrOrder.class);
        if (userAddrOrder == null) {
            throw new YamiShopBindException("请填写收货地址");
        }
        userAddrOrder.setUserId(userId);
        userAddrOrder.setCreateTime(now);
        userAddrOrderService.save(userAddrOrder);

        // 订单地址id
        Long addrOrderId = userAddrOrder.getAddrOrderId();


        // 每个店铺生成一个订单
        for (ShopCartOrderDto shopCartOrderDto : shopCartOrders) {
            // 使用雪花算法生成的订单号
            String orderNumber = String.valueOf(snowflake.nextId());
            shopCartOrderDto.setOrderNumber(orderNumber);

            Long shopId = shopCartOrderDto.getShopId();

            // 订单商品名称
            StringBuilder orderProdName = new StringBuilder(100);

            List<OrderItem> orderItems = new ArrayList<>();

            List<ShopCartItemDiscountDto> shopCartItemDiscounts = shopCartOrderDto.getShopCartItemDiscounts();
            for (ShopCartItemDiscountDto shopCartItemDiscount : shopCartItemDiscounts) {
                List<ShopCartItemDto> shopCartItems = shopCartItemDiscount.getShopCartItems();
                for (ShopCartItemDto shopCartItem : shopCartItems) {
                    Sku sku = checkAndGetSku(shopCartItem.getSkuId(), shopCartItem, skuStocksMap);
                    Product product = checkAndGetProd(shopCartItem.getProdId(), shopCartItem, prodStocksMap);

                    OrderItem orderItem = new OrderItem();
                    orderItem.setShopId(shopId);
                    orderItem.setOrderNumber(orderNumber);
                    orderItem.setProdId(sku.getProdId());
                    orderItem.setSkuId(sku.getSkuId());
                    orderItem.setSkuName(sku.getSkuName());
                    orderItem.setProdCount(shopCartItem.getProdCount());
                    orderItem.setProdName(sku.getProdName());
                    orderItem.setPic(StrUtil.isBlank(sku.getPic()) ? product.getPic() : sku.getPic());
                    orderItem.setPrice(shopCartItem.getPrice());
                    orderItem.setUserId(userId);
                    orderItem.setProductTotalAmount(shopCartItem.getProductTotalAmount());
                    orderItem.setRecTime(now);
                    orderItem.setCommSts(0);
                    orderItem.setBasketDate(shopCartItem.getBasketDate());
                    orderProdName.append(orderItem.getProdName()).append(",");
                    //推广员卡号
                    orderItem.setDistributionCardNo(shopCartItem.getDistributionCardNo());

                    orderItems.add(orderItem);

                    if (shopCartItem.getBasketId() != null && shopCartItem.getBasketId() != 0) {
                        basketIds.add(shopCartItem.getBasketId());
                    }
                }

            }


            orderProdName.subSequence(0, Math.min(orderProdName.length() - 1, 100));
            if (orderProdName.lastIndexOf(",") == orderProdName.length() - 1) {
                orderProdName.deleteCharAt(orderProdName.length() - 1);
            }


            // 订单信息
            com.ontoffline.supermall.bean.model.Order order = new com.ontoffline.supermall.bean.model.Order();

            order.setShopId(shopId);
            order.setOrderNumber(orderNumber);
            // 订单商品名称
            order.setProdName(orderProdName.toString());
            // 用户id
            order.setUserId(userId);
            // 商品总额
            order.setTotal(shopCartOrderDto.getTotal());
            // 实际总额
            order.setActualTotal(shopCartOrderDto.getActualTotal());
            order.setStatus(OrderStatus.UNPAY.value());
            order.setUpdateTime(now);
            order.setCreateTime(now);
            order.setIsPayed(0);
            order.setDeleteStatus(0);
            order.setProductNums(shopCartOrderDto.getTotalCount());
            order.setAddrOrderId(addrOrderId);
            order.setReduceAmount(Arith.sub(shopCartOrderDto.getTotal(), shopCartOrderDto.getActualTotal()));
            order.setFreightAmount(shopCartOrderDto.getTransfee());
            order.setRemarks(shopCartOrderDto.getRemarks());

            order.setOrderItems(orderItems);
            event.getOrders().add(order);
            // 插入订单结算表
            OrderSettlement orderSettlement = new OrderSettlement();
            orderSettlement.setUserId(userId);
            orderSettlement.setIsClearing(0);
            orderSettlement.setCreateTime(now);
            orderSettlement.setOrderNumber(orderNumber);
            orderSettlement.setPayAmount(order.getActualTotal());
            orderSettlement.setPayStatus(0);
            orderSettlement.setVersion(0);
            orderSettlementMapper.insert(orderSettlement);

        }

        // 删除购物车的商品信息
        if (!basketIds.isEmpty()) {
            basketMapper.deleteShopCartItemsByBasketIds(userId, basketIds);

        }


        // 更新sku库存
        skuStocksMap.forEach((key, sku) -> {

            if (skuMapper.updateStocks(sku) == 0) {
                skuService.removeSkuCacheBySkuId(key, sku.getProdId());
                throw new YamiShopBindException("商品：[" + sku.getProdName() + "]库存不足");
            }
        });

        // 更新商品库存
        prodStocksMap.forEach((prodId, prod) -> {

            if (productMapper.updateStocks(prod) == 0) {
                productService.removeProductCacheByProdId(prodId);
                throw new YamiShopBindException("商品：[" + prod.getProdName() + "]库存不足");
            }
        });

    }

    @SuppressWarnings({"Duplicates"})
    private Product checkAndGetProd(Long prodId, ShopCartItemDto shopCartItem, Map<Long, Product> prodStocksMap) {
        Product product = productService.getProductByProdId(prodId);
        if (product == null) {
            throw new YamiShopBindException("购物车包含无法识别的商品");
        }

        if (product.getStatus() != 1) {
            throw new YamiShopBindException("商品[" + product.getProdName() + "]已下架");
        }

        // 商品需要改变的库存
        Product mapProduct = prodStocksMap.get(prodId);

        if (mapProduct == null) {
            mapProduct = new Product();
            mapProduct.setTotalStocks(0);
            mapProduct.setProdId(prodId);
            mapProduct.setProdName(product.getProdName());

        }

        if (product.getTotalStocks() != -1) {
            mapProduct.setTotalStocks(mapProduct.getTotalStocks() + shopCartItem.getProdCount());
            prodStocksMap.put(product.getProdId(), mapProduct);
        }

        // -1为无限库存
        if (product.getTotalStocks() != -1 && mapProduct.getTotalStocks() > product.getTotalStocks()) {
            throw new YamiShopBindException("商品：[" + product.getProdName() + "]库存不足");
        }

        return product;
    }

    @SuppressWarnings({"Duplicates"})
    private Sku checkAndGetSku(Long skuId, ShopCartItemDto shopCartItem, Map<Long, Sku> skuStocksMap) {
        // 获取sku信息
        Sku sku = skuService.getSkuBySkuId(skuId);
        if (sku == null) {
            throw new YamiShopBindException("购物车包含无法识别的商品");
        }

        if (sku.getStatus() != 1) {
            throw new YamiShopBindException("商品[" + sku.getProdName() + "]已下架");
        }
        // -1为无限库存
        if (sku.getStocks() != -1 && shopCartItem.getProdCount() > sku.getStocks()) {
            throw new YamiShopBindException("商品：[" + sku.getProdName() + "]库存不足");
        }

        if (sku.getStocks() != -1) {
            Sku mapSku = new Sku();
            mapSku.setProdId(sku.getProdId());
            // 这里的库存是改变的库存
            mapSku.setStocks(shopCartItem.getProdCount());
            mapSku.setSkuId(sku.getSkuId());
            mapSku.setProdName(sku.getProdName());
            skuStocksMap.put(sku.getSkuId(), mapSku);
        }
        return sku;
    }


}
