/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.order;

/**
 * @author wangylontoffline
 */

public interface ConfirmOrderOrder {

    /**
     * 没有任何活动时的顺序
     */
    int DEFAULT = 0;

    /**
     * 满减，排在DEFAULT后面
     */
    int DISCOUNT = 100;

    /**
     * 优惠券，排在DISCOUNT后面
     */
    int COUPON = 200;

    /**
     * 分销，排在COUPON后面
     */
    int DISTRIBUTION = 300;
}
