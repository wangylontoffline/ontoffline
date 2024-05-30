/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@ApiModel(value= "购物车参数")
@Data
public class ShopCartParam {

    @ApiModelProperty(value = "购物项id")
    private Long basketId;

    @ApiModelProperty(value = "活动id,传0则不参与该活动")
    private Long discountId;
}
