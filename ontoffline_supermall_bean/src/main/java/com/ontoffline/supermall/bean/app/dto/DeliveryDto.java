/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class DeliveryDto {

	@ApiModelProperty(value = "物流公司名称",required=true)
	private String companyName;
	
	@ApiModelProperty(value = "物流公司官网",required=true)
	private String companyHomeUrl;
	
	@ApiModelProperty(value = "物流订单号",required=true)
	private String dvyFlowId;
	
	@ApiModelProperty(value = "查询出的物流信息",required=true)
	private List<DeliveryInfoDto> data;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyHomeUrl() {
		return companyHomeUrl;
	}

	public void setCompanyHomeUrl(String companyHomeUrl) {
		this.companyHomeUrl = companyHomeUrl;
	}

	public String getDvyFlowId() {
		return dvyFlowId;
	}

	public void setDvyFlowId(String dvyFlowId) {
		this.dvyFlowId = dvyFlowId;
	}

	public List<DeliveryInfoDto> getData() {
		return data;
	}

	public void setData(List<DeliveryInfoDto> data) {
		this.data = data;
	}

	
}
