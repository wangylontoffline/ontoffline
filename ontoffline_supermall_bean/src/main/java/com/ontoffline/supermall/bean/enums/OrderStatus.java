/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.enums;

/**
 * @author wangylontoffline
 */

public enum OrderStatus {

    /**
     * 没有付款.待付款
     */
    UNPAY(1),

    /**
     * 已经付款,但卖家没有发货.待发货
     */
    PADYED(2),

    /**
     * 发货，导致实际库存减少，没有确认收货.待收货
     */
    CONSIGNMENT(3),

    /**
     * 收货，没有评价.待评价
     */
    CONFIRM(4),

    /**
     * 评价后交易成功，购买数增加1.
     */
    SUCCESS(5),

    /**
     * 交易失败.,还原库存
     */
    CLOSE(6);

    private Integer num;

    public Integer value() {
        return num;
    }

    OrderStatus(Integer num) {
        this.num = num;
    }

    public static OrderStatus instance(Integer value) {
        OrderStatus[] enums = values();
        for (OrderStatus statusEnum : enums) {
            if (statusEnum.value().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }
}