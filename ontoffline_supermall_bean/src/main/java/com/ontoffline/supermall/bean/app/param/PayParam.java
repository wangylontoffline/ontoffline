/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangylontoffline
 */

@ApiModel(value= "支付参数")
public class PayParam {

	/**
	  * 订单号
	 */
	@NotBlank(message="订单号不能为空")
	@ApiModelProperty(value = "订单号",required=true)
	private String orderNumbers;

	/**
	 * 支付方式
	 */
	@NotNull(message="支付方式不能为空")
	@ApiModelProperty(value = "支付方式 (1:微信支付 2:支付宝)",required=true)
	private Integer payType;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

}
