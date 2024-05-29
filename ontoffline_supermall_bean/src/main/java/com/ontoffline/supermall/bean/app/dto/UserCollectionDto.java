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

import java.util.Date;

/**
 * @author wangylontoffline
 */

@ApiModel("收藏对象")
@Data
public class UserCollectionDto {

    @ApiModelProperty(value = "收藏id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String prodName;

    @ApiModelProperty(value = "收藏时间")
    private Date createTime;

}
