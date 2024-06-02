/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.enums;


/**
 * @author wangylontoffline
 */

public enum App {

	/**
	 * 小程序
	 */
	MINI(1),

	/**
	 * 微信公众号
	 */
	MP(2)
	;
	
	private Integer num;
	
	public Integer value() {
		return num;
	}
	
	App(Integer num){
		this.num = num;
	}
	
	public static App instance(Integer value) {
		App[] enums = values();
		for (App statusEnum : enums) {
			if (statusEnum.value().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}
}
