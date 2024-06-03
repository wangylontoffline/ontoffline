/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.OrderItem;

/**
 * @author wangylontoffline
 */

public interface OrderItemService extends IService<OrderItem> {

	List<OrderItem> getOrderItemsByOrderNumber(String orderNumber);

}
