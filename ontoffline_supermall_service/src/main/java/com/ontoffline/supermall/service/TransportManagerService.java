/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.ontoffline.supermall.bean.app.dto.ProductItemDto;
import com.ontoffline.supermall.bean.model.UserAddr;

/**
 * @author wangylontoffline
 */

public interface TransportManagerService {

	Double calculateTransfee(ProductItemDto productItem, UserAddr userAddr);
}
