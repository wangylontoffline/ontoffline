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

/**
 * @author wangylontoffline
 */

@Data
public class DeliveryInfoDto {
	
	@ApiModelProperty(value = "详细信息",required=true)
	private String context;
	
	private String ftime;
	
	@ApiModelProperty(value = "快递所在区域",required=true)
	private String location;
	
	@ApiModelProperty(value = "物流更新时间",required=true)
	private String time;
	
}
