/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel(value= "提交订单参数")
public class SubmitOrderParam {
	@ApiModelProperty(value = "每个店铺提交的订单信息",required=true)
	private List<OrderShopParam> orderShopParam;

	public List<OrderShopParam> getOrderShopParam() {
		return orderShopParam;
	}

	public void setOrderShopParam(List<OrderShopParam> orderShopParam) {
		this.orderShopParam = orderShopParam;
	}
	
	
}
