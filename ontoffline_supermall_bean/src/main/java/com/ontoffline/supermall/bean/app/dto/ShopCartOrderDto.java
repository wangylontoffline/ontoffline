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
public class ShopCartOrderDto implements Serializable{

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "实际总值", required = true)
    private Double actualTotal;

    @ApiModelProperty(value = "商品总值", required = true)
    private Double total;

    @ApiModelProperty(value = "商品总数", required = true)
    private Integer totalCount;

    @ApiModelProperty(value = "运费", required = true)
    private Double transfee = 10.00;

    @ApiModelProperty(value = "促销活动优惠金额", required = true)
    private Double discountReduce;

    @ApiModelProperty(value = "优惠券优惠金额", required = true)
    private Double couponReduce;

    @ApiModelProperty(value = "店铺优惠金额(促销活动 + 优惠券 + 其他)", required = true)
    private Double shopReduce = 0.0;

    @ApiModelProperty(value = "订单备注信息", required = true)
    private String remarks;

    @ApiModelProperty(value = "购物车商品", required = true)
    private List<ShopCartItemDiscountDto> shopCartItemDiscounts;

    @ApiModelProperty(value = "整个店铺可以使用的优惠券列表", required = true)
    private List<CouponOrderDto> coupons;

    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNumber;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

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

	public Double getTransfee() {
		return transfee;
	}

	public void setTransfee(Double transfee) {
		this.transfee = transfee;
	}

	public Double getDiscountReduce() {
		return discountReduce;
	}

	public void setDiscountReduce(Double discountReduce) {
		this.discountReduce = discountReduce;
	}

	public Double getCouponReduce() {
		return couponReduce;
	}

	public void setCouponReduce(Double couponReduce) {
		this.couponReduce = couponReduce;
	}

	public Double getShopReduce() {
		return shopReduce;
	}

	public void setShopReduce(Double shopReduce) {
		this.shopReduce = shopReduce;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<ShopCartItemDiscountDto> getShopCartItemDiscounts() {
		return shopCartItemDiscounts;
	}

	public void setShopCartItemDiscounts(List<ShopCartItemDiscountDto> shopCartItemDiscounts) {
		this.shopCartItemDiscounts = shopCartItemDiscounts;
	}

	public List<CouponOrderDto> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponOrderDto> coupons) {
		this.coupons = coupons;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
    
    
}
