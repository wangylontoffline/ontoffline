/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.exception;

import org.springframework.http.HttpStatus;

/**
 */
public class UnauthorizedExceptionBase extends BaseOntofflineAuth2Exception {

	public UnauthorizedExceptionBase(String msg) {
		super(msg);
	}


	public UnauthorizedExceptionBase(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
