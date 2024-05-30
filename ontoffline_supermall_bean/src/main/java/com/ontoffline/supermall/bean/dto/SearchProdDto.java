/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel("搜索商品数据")
public class SearchProdDto {

    @ApiModelProperty(value = "商品id")
    private Long prodId;

    @ApiModelProperty(value = "商品照片")
    @JsonSerialize(using = ImgJsonSerializer.class)
    private String pic;

    @ApiModelProperty(value = "商品名字")
    private String prodName;

    @ApiModelProperty(value = "商品价格")
    private Double price;
    
    @ApiModelProperty(value = "销售量")
    private Integer soldNum;

    @ApiModelProperty(value = "商品评论数量")
    private Integer prodCommNumber;

    @ApiModelProperty(value = "好评率")
    private Double positiveRating;

    @ApiModelProperty(value = "好评数量")
    private Integer praiseNumber;

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getProdCommNumber() {
		return prodCommNumber;
	}

	public void setProdCommNumber(Integer prodCommNumber) {
		this.prodCommNumber = prodCommNumber;
	}

	public Double getPositiveRating() {
		return positiveRating;
	}

	public void setPositiveRating(Double positiveRating) {
		this.positiveRating = positiveRating;
	}

	public Integer getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(Integer praiseNumber) {
		this.praiseNumber = praiseNumber;
	}
    

}
