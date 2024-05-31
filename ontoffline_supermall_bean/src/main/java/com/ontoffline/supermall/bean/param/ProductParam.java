/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.param;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ontoffline.supermall.bean.model.Product;
import com.ontoffline.supermall.bean.model.Sku;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class ProductParam {

    /**
     * 产品ID
     */
    private Long prodId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    @Size(max = 200, message = "商品名称长度应该小于{max}")
    private String prodName;


    /**
     * 商品价格
     */
    @NotNull(message = "请输入商品价格")
    private Double price;

    /**
     * 商品价格
     */
    @NotNull(message = "请输入商品原价")
    private Double oriPrice;

    /**
     * 库存量
     */
    @NotNull(message = "请输入商品库存")
    private Integer totalStocks;

    /**
     * 简要描述,卖点等
     */
    @Size(max = 500, message = "商品卖点长度应该小于{max}")
    private String brief;

    @NotBlank(message = "请选择图片上传")
    private String pic;

    /**
     * 商品图片
     */
    @NotBlank(message = "请选择图片上传")
    private String imgs;

    /**
     * 商品分类
     */
    @NotNull(message = "请选择商品分类")
    private Long categoryId;

    /**
     * sku列表字符串
     */
    private List<Sku> skuList;

    /**
     * content 商品详情
     */
    private String content;

    /**
     * 是否能够用户自提
     */
    private Product.DeliveryModeVO deliveryModeVo;

    /**
     * 运费模板id
     */
    private Long deliveryTemplateId;

    /**
     * 分组标签列表
     */
    private List<Long> tagList;

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Product.DeliveryModeVO getDeliveryModeVo() {
		return deliveryModeVo;
	}

	public void setDeliveryModeVo(Product.DeliveryModeVO deliveryModeVo) {
		this.deliveryModeVo = deliveryModeVo;
	}

	public Long getDeliveryTemplateId() {
		return deliveryTemplateId;
	}

	public void setDeliveryTemplateId(Long deliveryTemplateId) {
		this.deliveryTemplateId = deliveryTemplateId;
	}

	public List<Long> getTagList() {
		return tagList;
	}

	public void setTagList(List<Long> tagList) {
		this.tagList = tagList;
	}
    
    

}
