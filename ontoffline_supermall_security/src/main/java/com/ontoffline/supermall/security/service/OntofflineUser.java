/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.service;


import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * @author wangylontoffline
 */

@Getter
public class OntofflineUser extends User {

	/**
	 * 用户ID
	 */
	private String userId;

	private String bizUserId;

	@Setter
	private String pic;

	@Setter
	private String name;

	@Setter
	private boolean debugger;

	public OntofflineUser(String userId, String bizUserId, Integer appId, boolean enabled) {
		super(appId + StrUtil.COLON + bizUserId, "", enabled,true, true, true , Collections.emptyList());
		this.userId = userId;
		this.bizUserId = bizUserId;
	}

	public void setDebugger(boolean debugger) {
		// TODO Auto-generated method stub
		this.debugger = debugger;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBizUserId() {
		return bizUserId;
	}

	public void setBizUserId(String bizUserId) {
		this.bizUserId = bizUserId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
