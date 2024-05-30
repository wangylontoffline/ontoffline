/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.event;


import com.ontoffline.supermall.bean.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class CancelOrderEvent {

    private Order order;

	public CancelOrderEvent(Order order) {
		super();
		this.order = order;
	}
    
    
}
