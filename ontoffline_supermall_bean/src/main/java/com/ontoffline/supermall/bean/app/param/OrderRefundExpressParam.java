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

/**
 * @author wangylontoffline
 */

@Data
public class OrderRefundExpressParam {

    @ApiModelProperty(value = "退款编号名称", required = true)
    @NotEmpty(message = "退款编号不能为空")
    private String refundSn;

    @ApiModelProperty(value = "物流公司名称", required = true)
    @NotEmpty(message = "物流公司名称不能为空")
    private String expressName;

    @ApiModelProperty(value = "物流单号", required = true)
    @NotEmpty(message = "物流单号不能为空")
    private String expressNo;


}
