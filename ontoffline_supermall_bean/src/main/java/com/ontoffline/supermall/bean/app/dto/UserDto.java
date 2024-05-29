/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangylontoffline
 */

public class UserDto {


	@ApiModelProperty(value = "用户状态：0禁用 1正常",required=true)
	private Integer status;
	@ApiModelProperty(value = "token",required=true)
	private String token;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}