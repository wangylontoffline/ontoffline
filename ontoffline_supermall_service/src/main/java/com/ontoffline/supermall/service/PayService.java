/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.List;

import com.ontoffline.supermall.bean.app.param.PayParam;
import com.ontoffline.supermall.bean.pay.PayInfoDto;
import com.ontoffline.supermall.bean.pay.PayRefundDto;

/**
 * @author wangylontoffline
 */

public interface PayService {

	/**
	 * 支付交易接口
	 * @param userId
	 * @param payParam
	 * @return
	 */
    PayInfoDto pay(String userId, PayParam payParam);
    
    /**
     * 支付退款接口
     * @param userId
     * @param payRefundParam
     * @return
     */
    PayRefundDto refund(String userId,PayParam payRefundParam);

    /**
     * 支付成功回调接口
     * @param payNo
     * @param bizPayNo
     * @return
     */
    List<String> paySuccess(String payNo, String bizPayNo);

}
