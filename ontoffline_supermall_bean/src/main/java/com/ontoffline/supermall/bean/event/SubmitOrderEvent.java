/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.event;


import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderMergerDto;
import com.ontoffline.supermall.bean.app.param.OrderParam;
import com.ontoffline.supermall.bean.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class SubmitOrderEvent {
    private ShopCartOrderMergerDto mergerOrder = new ShopCartOrderMergerDto();

    private List<Order> orders;

	public SubmitOrderEvent(ShopCartOrderMergerDto mergerOrder, List<Order> orders) {
		super();
		this.mergerOrder = mergerOrder;
		this.orders = orders;
	}

	public ShopCartOrderMergerDto getMergerOrder() {
		return mergerOrder;
	}

	public void setMergerOrder(ShopCartOrderMergerDto mergerOrder) {
		this.mergerOrder = mergerOrder;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
    

}
