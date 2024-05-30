/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangylontoffline
 */

public class OrderShopParam {

	/** 店铺ID **/
	@ApiModelProperty(value = "店铺id",required=true)
	private Long shopId;
	
	/**
	 * 订单备注信息
	 */
	@ApiModelProperty(value = "订单备注信息",required=true)
	private String remarks;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
