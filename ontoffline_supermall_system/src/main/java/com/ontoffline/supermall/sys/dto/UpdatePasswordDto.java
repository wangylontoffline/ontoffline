/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel(value= "更新密码参数")
public class UpdatePasswordDto {

	@NotBlank(message="旧密码不能为空")
	@Size(max = 50)
	@ApiModelProperty(value = "旧密码",required=true)
	private String password;
	
	@NotBlank(message="新密码不能为空")
	@Size(max = 50)
	@ApiModelProperty(value = "新密码",required=true)
	private String newPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
