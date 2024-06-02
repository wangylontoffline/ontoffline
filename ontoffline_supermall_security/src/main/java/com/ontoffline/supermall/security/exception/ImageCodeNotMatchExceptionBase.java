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

public class ImageCodeNotMatchExceptionBase extends BaseOntofflineAuth2Exception {

    public ImageCodeNotMatchExceptionBase(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "image_code_not_match";
    }
}
