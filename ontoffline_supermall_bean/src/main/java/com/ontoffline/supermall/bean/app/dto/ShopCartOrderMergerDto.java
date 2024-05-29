/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class ShopCartOrderMergerDto implements Serializable{

    @ApiModelProperty(value = "实际总值", required = true)
    private Double actualTotal;

    @ApiModelProperty(value = "商品总值", required = true)
    private Double total;

    @ApiModelProperty(value = "商品总数", required = true)
    private Integer totalCount;

    @ApiModelProperty(value = "订单优惠金额(所有店铺优惠金额相加)", required = true)
    private Double orderReduce;

    @ApiModelProperty(value = "地址Dto", required = true)
    private UserAddrDto userAddr;

    @ApiModelProperty(value = "每个店铺的购物车信息", required = true)
    private List<ShopCartOrderDto> shopCartOrders;

    @ApiModelProperty(value = "整个订单可以使用的优惠券列表", required = true)
    private List<CouponOrderDto> coupons;

	public Double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Double getOrderReduce() {
		return orderReduce;
	}

	public void setOrderReduce(Double orderReduce) {
		this.orderReduce = orderReduce;
	}

	public UserAddrDto getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(UserAddrDto userAddr) {
		this.userAddr = userAddr;
	}

	public List<ShopCartOrderDto> getShopCartOrders() {
		return shopCartOrders;
	}

	public void setShopCartOrders(List<ShopCartOrderDto> shopCartOrders) {
		this.shopCartOrders = shopCartOrders;
	}

	public List<CouponOrderDto> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponOrderDto> coupons) {
		this.coupons = coupons;
	}
    
    
}
