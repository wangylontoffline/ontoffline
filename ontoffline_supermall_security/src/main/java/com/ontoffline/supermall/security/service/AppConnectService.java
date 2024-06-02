/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.model.AppConnect;

/**
 * @author wangylontoffline
 */

public interface AppConnectService extends IService<AppConnect> {

	AppConnect getByBizUserId(String bizUserId, App app);
}
