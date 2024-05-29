/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class ProductItemDto implements Serializable {

	@ApiModelProperty(value = "产品名称",required=true)
	private String prodName;

	@ApiModelProperty(value = "产品个数",required=true)
	private Integer prodCount;

	@ApiModelProperty(value = "产品图片路径",required=true)
	@JsonSerialize(using = ImgJsonSerializer.class)
	private String pic;

	@ApiModelProperty(value = "产品价格",required=true)
	private Double price;

	@ApiModelProperty(value = "商品总金额",required=true)
	private Double productTotalAmount;

	@ApiModelProperty(value = "产品ID",required=true)
	private Long prodId;

    @ApiModelProperty(value = "skuId",required=true)
    private Long skuId;

	@ApiModelProperty(value = "规格名称", required = true)
	private String skuName;

	@ApiModelProperty(value = "basketId",required=true)
	private Long basketId;

	@ApiModelProperty(value = "商品实际金额 = 商品总金额 - 分摊的优惠金额")
	private Double actualTotal;

	@ApiModelProperty(value = "满减满折优惠id，0不主动参与活动（用户没有主动参与该活动），-1主动不参与活动")
	private Long discountId = 0L;

	@ApiModelProperty(value = "分摊的优惠金额")
	private Double shareReduce;

	@ApiModelProperty("参与满减活动列表")
	private List<DiscountDto> discounts = new ArrayList<>();

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getProdCount() {
		return prodCount;
	}

	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(Double productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public Double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Double getShareReduce() {
		return shareReduce;
	}

	public void setShareReduce(Double shareReduce) {
		this.shareReduce = shareReduce;
	}

	public List<DiscountDto> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountDto> discounts) {
		this.discounts = discounts;
	}
	
	
}
