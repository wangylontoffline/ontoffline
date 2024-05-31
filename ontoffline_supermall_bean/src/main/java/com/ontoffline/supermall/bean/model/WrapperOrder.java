/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;

/**
 * @author wangylontoffline
 */

@SuppressWarnings("serial")
public class WrapperOrder extends Wrapper<Order> {
	
	public WrapperOrder() {
	}
	
	public Order order;
	

	@Override
	public String getSqlSegment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getEntity() {
		// TODO Auto-generated method stub
		return order;
	}

	@Override
	public MergeSegments getExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomSqlSegment() {
		// TODO Auto-generated method stub
		return null;
	}

}
