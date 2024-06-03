/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ontoffline.supermall.bean.app.param.PayParam;
import com.ontoffline.supermall.bean.enums.PayType;
import com.ontoffline.supermall.bean.event.PaySuccessOrderEvent;
import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.bean.model.OrderSettlement;
import com.ontoffline.supermall.bean.pay.PayInfoDto;
import com.ontoffline.supermall.bean.pay.PayRefundDto;
import com.ontoffline.supermall.dao.OrderMapper;
import com.ontoffline.supermall.dao.OrderSettlementMapper;
import com.ontoffline.supermall.service.PayService;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.Arith;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;

/**
 * @author wangylontoffline
 */

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSettlementMapper orderSettlementMapper;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private Snowflake snowflake;

    /**
     * 不同的订单号，同一个支付流水号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayInfoDto pay(String userId, PayParam payParam) {


        // 不同的订单号的产品名称
        StringBuilder prodName = new StringBuilder();
        // 支付单号
        String payNo = String.valueOf(snowflake.nextId());
        String[] orderNumbers = payParam.getOrderNumbers().split(StrUtil.COMMA);
        // 修改订单信息
        for (String orderNumber : orderNumbers) {
            OrderSettlement orderSettlement = new OrderSettlement();
            orderSettlement.setPayNo(payNo);
            orderSettlement.setPayType(payParam.getPayType());
            orderSettlement.setUserId(userId);
            orderSettlement.setOrderNumber(orderNumber);
            orderSettlementMapper.updateByOrderNumberAndUserId(orderSettlement);

            Order order = orderMapper.getOrderByOrderNumber(orderNumber);
            prodName.append(order.getProdName()).append(StrUtil.COMMA);
        }
        // 除了ordernumber不一样，其他都一样
        List<OrderSettlement> settlements = orderSettlementMapper.getSettlementsByPayNo(payNo);
        // 应支付的总金额
        double payAmount = 0.0;
        for (OrderSettlement orderSettlement : settlements) {
            payAmount = Arith.add(payAmount, orderSettlement.getPayAmount());
        }

        prodName.substring(0, Math.min(100, prodName.length() - 1));

        PayInfoDto payInfoDto = new PayInfoDto();
        payInfoDto.setBody(prodName.toString());
        payInfoDto.setPayAmount(payAmount);
        payInfoDto.setPayNo(payNo);
        return payInfoDto;
    }
    
    /**
     * 单个订单支付退款
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayRefundDto refund(String userId, PayParam payParam) {

        // 不同的订单号的产品名称
        StringBuilder prodName = new StringBuilder();
        // 支付单号
        String payNo = String.valueOf(snowflake.nextId());
        String[] orderNumbers = payParam.getOrderNumbers().split(StrUtil.COMMA);
        // 修改订单信息
        for (String orderNumber : orderNumbers) {
            OrderSettlement orderSettlement = new OrderSettlement();
            orderSettlement.setPayNo(payNo);
            orderSettlement.setPayType(payParam.getPayType());
            orderSettlement.setUserId(userId);
            orderSettlement.setOrderNumber(orderNumber);
            orderSettlementMapper.updateByOrderNumberAndUserId(orderSettlement);

            Order order = orderMapper.getOrderByOrderNumber(orderNumber);
            prodName.append(order.getProdName()).append(StrUtil.COMMA);
        }
        // 除了ordernumber不一样，其他都一样
        List<OrderSettlement> settlements = orderSettlementMapper.getSettlementsByPayNo(payNo);
        // 应支付的总金额
        double payAmount = 0.0;
        for (OrderSettlement orderSettlement : settlements) {
            payAmount = Arith.add(payAmount, orderSettlement.getPayAmount());
        }

        prodName.substring(0, Math.min(100, prodName.length() - 1));

        PayRefundDto payRefundDto = new PayRefundDto();
        payRefundDto.setRefundAmt(payAmount);
        payRefundDto.setRefundNo(payNo);
        payRefundDto.setTradeNo(orderNumbers[0]);
        payRefundDto.setTxnId("1");
        return payRefundDto;
    }

    /**
     * 单个订单支付成功回调
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> paySuccess(String payNo, String bizPayNo) {
        List<OrderSettlement> orderSettlements = orderSettlementMapper.selectList(new LambdaQueryWrapper<OrderSettlement>().eq(OrderSettlement::getPayNo, payNo));

        OrderSettlement settlement = orderSettlements.get(0);

        // 订单已支付
        if (settlement.getPayStatus() == 1) {
            throw new OntofflineSupermallBindException("订单已支付");
        }
        // 修改订单结算信息
        if (orderSettlementMapper.updateToPay(payNo, settlement.getVersion()) < 1) {
            throw new OntofflineSupermallBindException("结算信息已更改");
        }


        List<String> orderNumbers = orderSettlements.stream().map(OrderSettlement::getOrderNumber).collect(Collectors.toList());

        // 将订单改为已支付状态
        orderMapper.updateByToPaySuccess(orderNumbers, PayType.WECHATPAY.value());

        List<Order> orders = orderNumbers.stream().map(orderNumber -> orderMapper.getOrderByOrderNumber(orderNumber)).collect(Collectors.toList());
        eventPublisher.publishEvent(new PaySuccessOrderEvent(orders));
        return orderNumbers;
    }

}
