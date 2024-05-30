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

public enum PayType {

	/** 微信支付*/
	WECHATPAY(1,"微信支付"),
	
	/** "支付宝*/
	ALIPAY(2,"支付宝");

	private Integer num;
	
	private String payTypeName;
	
	public Integer value() {
		return num;
	}
	
	public String getPayTypeName() {
		return payTypeName;
	}
	
	PayType(Integer num,String payTypeName){
		this.num = num;
		this.payTypeName = payTypeName;
	}
	
	public static PayType instance(Integer value) {
		PayType[] enums = values();
		for (PayType statusEnum : enums) {
			if (statusEnum.value().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}
}
