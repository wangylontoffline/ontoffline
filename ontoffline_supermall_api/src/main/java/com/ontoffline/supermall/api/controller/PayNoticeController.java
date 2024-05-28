/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ontoffline.supermall.service.PayService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wangylontoffline
 */

@ApiIgnore
@RestController
@RequestMapping("/notice/pay")
@AllArgsConstructor
public class PayNoticeController {

    /**
     * 小程序支付
     */
    private WxPayService wxMiniPayService;

    private PayService payService;


    @RequestMapping("/order")
    public ResponseEntity<Void> submit(@RequestBody String xmlData) throws WxPayException {
        WxPayOrderNotifyResult parseOrderNotifyResult = wxMiniPayService.parseOrderNotifyResult(xmlData);

        String payNo = parseOrderNotifyResult.getOutTradeNo();
        String bizPayNo = parseOrderNotifyResult.getTransactionId();

        // 根据内部订单号更新order settlement
        payService.paySuccess(payNo, bizPayNo);


        return ResponseEntity.ok().build();
    }
}