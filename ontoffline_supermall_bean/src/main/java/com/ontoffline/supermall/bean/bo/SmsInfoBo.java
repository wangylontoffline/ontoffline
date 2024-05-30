/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.bo;


import java.util.Map;

import com.ontoffline.supermall.bean.enums.SmsType;

/**
 * @author wangylontoffline
 */

public class SmsInfoBo {
	
	private SmsType smsType;
	
	private String userId;
	
	private String mobile;
	
	private Map<String, String> params;

	
	
	
	public SmsInfoBo(SmsType smsType, String userId, String mobile, Map<String, String> params) {
		this.smsType = smsType;
		this.userId = userId;
		this.mobile = mobile;
		this.params = params;
	}

	public SmsType getSmsType() {
		return smsType;
	}

	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
