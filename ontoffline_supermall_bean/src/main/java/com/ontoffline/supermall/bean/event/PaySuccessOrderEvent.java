/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.event;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.ontoffline.supermall.bean.model.Order;

/**
 * @author wangylontoffline
 */

@Data
public class PaySuccessOrderEvent {

    public PaySuccessOrderEvent(List<Order> orders) {
		super();
		this.orders = orders;
	}

	private List<Order> orders;
}
