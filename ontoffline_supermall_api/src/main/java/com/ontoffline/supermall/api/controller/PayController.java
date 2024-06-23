/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ontoffline.supermall.api.config.ApiConfig;
import com.ontoffline.supermall.bean.app.param.PayParam;
import com.ontoffline.supermall.bean.pay.PayInfoDto;
import com.ontoffline.supermall.bean.pay.PayRefundDto;
import com.ontoffline.supermall.common.util.Arith;
import com.ontoffline.supermall.common.util.IPHelper;
import com.ontoffline.supermall.security.service.OntofflineUser;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.PayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/order")
@Api(tags = "订单接口")
@AllArgsConstructor
public class PayController {

    private PayService payService;

    @Autowired
    private ApiConfig apiConfig;

    private WxPayService wxMiniPayService;

    /**
     * 支付接口
     * @throws WxPayException 
     */
    @PostMapping("/pay")
    @ApiOperation(value = "根据订单号进行支付", notes = "根据订单号进行支付")
    @SneakyThrows
    public ResponseEntity<WxPayMpOrderResult> pay(@RequestBody PayParam payParam) throws WxPayException {
    	OntofflineUser user = SecurityUtils.getUser();
        String userId = user.getUserId();
        String openId = user.getBizUserId();


        PayInfoDto payInfo = payService.pay(userId, payParam);

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody(payInfo.getBody());
        orderRequest.setOutTradeNo(payInfo.getPayNo());
        orderRequest.setTotalFee((int) Arith.mul(payInfo.getPayAmount(), 100));
        orderRequest.setSpbillCreateIp(IPHelper.getIpAddr());
        orderRequest.setNotifyUrl(apiConfig.getDomainName() + "/notice/pay/order");
        orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        orderRequest.setOpenid(openId);

        return ResponseEntity.ok(wxMiniPayService.createOrder(orderRequest));
    }
    
    /**
     * 支付退款接口
     * @throws WxPayException 
     */
    @PostMapping("/refund")
    @ApiOperation(value = "根据订单号进行支付退款", notes = "根据订单号进行支付退款")
    @SneakyThrows
    public ResponseEntity<WxPayRefundResult> refund(@RequestBody PayParam payParam) throws WxPayException {
    	OntofflineUser user = SecurityUtils.getUser();
        String userId = user.getUserId();
        String openId = user.getBizUserId();


        PayRefundDto payRefundInfo = payService.refund(userId, payParam);

        WxPayRefundRequest orderRefundRequest = new WxPayRefundRequest();
        orderRefundRequest.setAppid("");
        orderRefundRequest.setDeviceInfo("");
        orderRefundRequest.setMchId("");
        orderRefundRequest.setNonceStr("");
        orderRefundRequest.setNotifyUrl(apiConfig.getDomainName() + "/notice/refund");
        orderRefundRequest.setOpUserId(openId);
        orderRefundRequest.setOutRefundNo("");
        orderRefundRequest.setOutTradeNo("");
        orderRefundRequest.setRefundAccount("");
        orderRefundRequest.setRefundDesc("");
        orderRefundRequest.setRefundFee(1);
        orderRefundRequest.setRefundFeeType("1");
        orderRefundRequest.setSign("");
        orderRefundRequest.setSignType("1");
        orderRefundRequest.setSubAppId("");
        orderRefundRequest.setSubMchId("");
        orderRefundRequest.setTotalFee((int) Arith.mul(payRefundInfo.getTotalAmt(), 100));
        orderRefundRequest.setTransactionId("");

        return ResponseEntity.ok(wxMiniPayService.refund(orderRefundRequest));
    }
}
