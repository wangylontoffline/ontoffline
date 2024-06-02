/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author wangylontoffline
 */

public abstract class BaseOntofflineAuth2Exception extends AuthenticationException {

	public BaseOntofflineAuth2Exception(String msg) {
		super(msg);
	}

	public int getHttpErrorCode() {
		// 400 not 401
		return HttpStatus.BAD_REQUEST.value();
	}

	public abstract String getOAuth2ErrorCode();
}
