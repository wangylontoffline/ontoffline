/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.param;


import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public abstract class AbstractAuthParam {

	/**
	 * 用户ID
	 */
	@NotBlank(message = "用户ID不能为空")
	@ApiModelProperty(value = "用户ID订单号", required = true)
	protected String userId;
	
	public abstract String getUserId();
	
	public abstract void setUserId(String userId);

}
