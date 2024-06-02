/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.exception;

/**
 * @author wangylontoffline
 */

public class UsernameNotFoundExceptionBase extends BaseOntofflineAuth2Exception {

    public UsernameNotFoundExceptionBase(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "username_not_found";
    }
}
