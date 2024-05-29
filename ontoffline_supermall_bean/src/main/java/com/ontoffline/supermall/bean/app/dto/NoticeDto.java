/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wangylontoffline
 */

@ApiModel("公告对象")
@Data
public class NoticeDto {

    @JsonView(NoContent.class)
    @ApiModelProperty(value = "公告id")
    private Long id;

    @JsonView(NoContent.class)
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @JsonView(NoContent.class)
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "公告内容")
    @JsonView(WithContent.class)
    private String content;

    @JsonView(NoContent.class)
    @ApiModelProperty(value = "公告发布时间")
    private Date publishTime;

    public static interface NoContent{}

    public static interface WithContent extends NoContent{}

}
