/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wangylontoffline
 */

@Data
public class OrderRefundParam {

    @ApiModelProperty(value = "订单编号", required = true)
    @NotEmpty(message = "订单编号不能为空")
    private String orderNumber;

    @ApiModelProperty(value = "申请类型(1:仅退款 2退款退货)", required = true)
    @NotNull(message = "申请类型不能为空")
    private Integer applyType;

//    @ApiModelProperty(value = "订单金额", required = true)
//    @NotNull(message = "订单金额不能为空")
//    private Double orderAmount;

    @ApiModelProperty(value = "订单项id(全部退款是0)", required = true)
    @NotNull(message = "订单项id不能为空")
    private Long orderItemId;

//    @ApiModelProperty(value = "退款金额", required = true)
//    @NotNull(message = "退款金额不能为空")
//    private BigDecimal refundAmount;

    @ApiModelProperty(value = "凭证图片列表", required = true)
    private String photoFiles;

    @ApiModelProperty(value = "申请原因", required = true)
    @NotEmpty(message = "订单编号不能为空")
    private String buyerMsg;

}
