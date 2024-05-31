/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.param;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class OrderStatusParam extends AbstractAuthParam {

	/**
	 * 订单状态
	 */
	@NotNull(message = "订单状态不能为空")
	@ApiModelProperty(value = "订单状态号", required = true)
	private Integer sts;

	/**
	 * 订单号
	 */
	@NotBlank(message = "订单号不能为空")
	@ApiModelProperty(value = "订单号", required = true)
	private String orderNumbers;

	public int getSts() {
		return sts;
	}

	public void setSts(int sts) {
		this.sts = sts;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		super.userId = userId;
	}

}
