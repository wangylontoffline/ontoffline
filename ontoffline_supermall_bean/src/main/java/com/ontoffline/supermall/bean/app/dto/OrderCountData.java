/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel("我的订单数量")
public class OrderCountData {

    @ApiModelProperty(value = "所有订单数量")
    private Integer allCount;

    @ApiModelProperty(value = "待付款")
    private Integer unPay;

    @ApiModelProperty(value = "待发货")
    private Integer payed;

    @ApiModelProperty(value = "待收货")
    private Integer consignment;

    @ApiModelProperty(value = "待评价")
    private Integer confirm;

    @ApiModelProperty(value = "成功")
    private Integer success;

    @ApiModelProperty(value = "失败")
    private Integer close;


}
