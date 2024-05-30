/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ontoffline.supermall.bean.enums.OrderEntry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel(value= "订单参数")
public class OrderParam {


	@ApiModelProperty(value = "购物车id 数组")
	private List<Long> basketIds;

	@ApiModelProperty(value = "立即购买时提交的商品项")
	private OrderItemParam orderItem;

	@ApiModelProperty(value = "地址ID，0为默认地址",required=true)
	@NotNull(message = "地址不能为空")
	private Long addrId;

	@ApiModelProperty(value = "用户是否改变了优惠券的选择，如果用户改变了优惠券的选择，则完全根据传入参数进行优惠券的选择")
	private Integer userChangeCoupon;

	@ApiModelProperty(value = "优惠券id数组")
	private List<Long> couponIds;

	public List<Long> getBasketIds() {
		return basketIds;
	}

	public void setBasketIds(List<Long> basketIds) {
		this.basketIds = basketIds;
	}

	public OrderItemParam getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItemParam orderItem) {
		this.orderItem = orderItem;
	}

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public Integer getUserChangeCoupon() {
		return userChangeCoupon;
	}

	public void setUserChangeCoupon(Integer userChangeCoupon) {
		this.userChangeCoupon = userChangeCoupon;
	}

	public List<Long> getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(List<Long> couponIds) {
		this.couponIds = couponIds;
	}
	
	

//	@ApiModelProperty(value = "每次订单提交时的uuid")
//	private String uuid;
//	@ApiModelProperty(value = "订单入口 SHOP_CART购物车，BUY_NOW立即购买")
//	private OrderEntry orderEntry;



}
