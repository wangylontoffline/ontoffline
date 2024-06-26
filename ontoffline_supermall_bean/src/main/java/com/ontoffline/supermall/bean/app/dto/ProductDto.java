/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.bean.model.Transport;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class ProductDto {
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String prodName;

    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格", required = true)
    private Double price;

    /**
     * 商品详情
     */
    @ApiModelProperty(value = "详细描述")
    private String content;

    /**
     * 商品原价
     */
    @ApiModelProperty(value = "商品原价", required = true)
    private Double oriPrice;
    
    /**
     * 商品原价
     */
    @ApiModelProperty(value = "商品销售量", required = true)
    private Integer soldNum;

    /**
     * 库存量
     */
    @ApiModelProperty(value = "库存量", required = true)
    private Integer totalStocks;

    /**
     * 简要描述,卖点等
     */
    @ApiModelProperty(value = "简要描述,卖点等", required = true)
    private String brief;

    /**
     * 商品主图
     */
    @JsonSerialize(using = ImgJsonSerializer.class)
    @ApiModelProperty(value = "商品主图", required = true)
    private String pic;

    @JsonSerialize(using = ImgJsonSerializer.class)
    @ApiModelProperty(value = "商品图片列表，以逗号分割", required = true)
    private String imgs;

    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类id", required = true)
    private Long categoryId;

    @ApiModelProperty(value = "sku列表")
    private List<SkuDto> skuList;

    @ApiModelProperty(value = "运费信息", required = true)
    private Transport transport;

    public static interface WithNoContent{}

    public static interface WithContent extends WithNoContent{}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public Integer getTotalStocks() {
		return totalStocks;
	}

	public void setTotalStocks(Integer totalStocks) {
		this.totalStocks = totalStocks;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<SkuDto> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<SkuDto> skuList) {
		this.skuList = skuList;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
    
    


}
