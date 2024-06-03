/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.OrderSettlement;

/**
 * @author wangylontoffline
 */

public interface OrderSettlementMapper extends BaseMapper<OrderSettlement> {

	void updateByOrderNumberAndUserId(@Param("orderSettlement") OrderSettlement orderSettlement);

	List<OrderSettlement> getSettlementsByPayNo(@Param("payNo") String payNo);

	void updateSettlementsByPayNo(@Param("outTradeNo") String outTradeNo, @Param("transactionId") String transactionId);

	int updateToPay(@Param("payNo") String payNo, @Param("version") Integer version);
}