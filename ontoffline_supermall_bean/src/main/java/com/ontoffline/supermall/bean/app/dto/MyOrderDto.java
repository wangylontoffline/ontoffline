/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel("我的订单")
public class MyOrderDto {
	
	@ApiModelProperty(value = "订单项",required=true)
	private List<MyOrderItemDto> orderItemDtos;
	
	@ApiModelProperty(value = "订单号",required=true)
	private String orderNumber;
	
	@ApiModelProperty(value = "总价",required=true)
	private Double actualTotal;
	
	@ApiModelProperty(value = "订单状态",required=true)
	private Integer status;
	
	@ApiModelProperty(value = "订单商品数量",required=true)
	private Integer prdCnt;
	
}
