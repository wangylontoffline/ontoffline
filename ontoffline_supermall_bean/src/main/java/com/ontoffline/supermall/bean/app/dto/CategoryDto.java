/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class CategoryDto {

	@ApiModelProperty(value = "分类id",required=true)
	private Long categoryId;

	@ApiModelProperty(value = "分类父id",required=true)
	private Long parentId;

	@ApiModelProperty(value = "分类名称",required=true)
	private String categoryName;

	@ApiModelProperty(value = "分类图片",required=true)
	@JsonSerialize(using = ImgJsonSerializer.class)
	private String pic;

}
