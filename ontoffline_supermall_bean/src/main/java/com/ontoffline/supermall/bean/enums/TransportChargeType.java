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

public enum TransportChargeType {

    /**
     * 0全部商品参与
     */
    COUNT(0),

    /**
     * 1指定商品参与
     */
    WEIGHT(1),

    /**
     * 2指定商品不参与
     */
    VOLUME(2)
    ;

    private Integer num;

    public Integer value() {
        return num;
    }

    TransportChargeType(Integer num){
        this.num = num;
    }

    public static TransportChargeType instance(Integer value) {
        TransportChargeType[] enums = values();
        for (TransportChargeType statusEnum : enums) {
            if (statusEnum.value().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }
}
