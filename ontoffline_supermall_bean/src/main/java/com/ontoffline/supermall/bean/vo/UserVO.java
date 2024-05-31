/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.vo;


import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class UserVO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    private String userMobile;

    /**
     * 用户头像
     */
    private String pic;
}
