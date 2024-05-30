/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.enums;

/**
 * @author wangylontoffline
 */

public enum MessageStatus {

	/**
	 * 小程序
	 */
	CANCEL(0),
	RELEASE(1);
	
	private Integer num;
	
	public Integer value() {
		return num;
	}
	
	MessageStatus(Integer num){
		this.num = num;
	}
}
