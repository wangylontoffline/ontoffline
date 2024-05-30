/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangylontoffline
 */

@ApiModel(value= "登陆参数")
public class LoginParam {

	@ApiModelProperty(value = "小程序登陆时返回的code(使用code登陆必填)",required=true)
	private String code;
	@ApiModelProperty(value = "登陆时的用户名(账号密码登陆必填)",required=true)
	private String mobile;
	@ApiModelProperty(value = "登陆时的密码(账号密码登陆必填)",required=true)
	private String password;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
