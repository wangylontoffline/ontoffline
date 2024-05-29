/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import com.ontoffline.supermall.bean.model.ProdTag;

/**
 * @author wangylontoffline
 */

@Data
public class TagProductDto {

    @ApiModelProperty(value = "分组标签id")
    private Long id;

    @ApiModelProperty(value = "分组标签标题")
    private String title;

    @ApiModelProperty(value = "排序（数值越高越靠前）")
    private String seq;

    @ApiModelProperty(value = "列表样式(0:一列一个,1:一列两个,2:一列三个)")
    private String style;

    private List<ProductDto> productDtoList;
}
