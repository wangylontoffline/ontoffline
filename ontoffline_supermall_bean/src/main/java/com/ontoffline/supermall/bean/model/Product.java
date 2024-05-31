/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod")
public class Product implements Serializable {

    private static final long serialVersionUID = -4644407386444894349L;
    /**
     * 商品ID
     */
    @TableId
    private Long prodId;

    /**
     * 店铺id
     */
    private Long shopId;
    
    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 原价
     */
    private Double oriPrice;

    /**
     * 现价
     */
    private Double price;

    /**
     * 简要描述,卖点等
     */
    private String brief;

    /**
     * 商品主图
     */
    @JsonSerialize(using = ImgJsonSerializer.class)
    private String pic;

    /**
     * 商品图片
     */
    private String imgs;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架
     */
    private Integer status;

    /**
     * 商品分类
     */
    private Long categoryId;

    /**
     * 已经销售数量
     */
    private Integer soldNum;

    /**
     * 库存量
     */
    private Integer totalStocks;

    /**
     * 配送方式json
     */
    private String deliveryMode;

    /**
     * 运费模板id
     */
    private Long deliveryTemplateId;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 详细描述
     */
    private String content;


    /**
     * 上架时间
     */
    private Date putawayTime;

    /**
     * 版本
     */
    @Version
    private Integer version;

    /**
     * sku列表
     */
    @TableField(exist = false)
    private List<Sku> skuList;

    /**
     * 店铺名称
     */
    @TableField(exist = false)
    private String shopName;

    @TableField(exist = false)
    private List<Long> tagList;
    
    


    public Long getProdId() {
		return prodId;
	}




	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}




	public Long getShopId() {
		return shopId;
	}




	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}


	public String getProdName() {
		return prodName;
	}




	public void setProdName(String prodName) {
		this.prodName = prodName;
	}




	public Double getOriPrice() {
		return oriPrice;
	}




	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}




	public Double getPrice() {
		return price;
	}




	public void setPrice(Double price) {
		this.price = price;
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




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public Long getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}




	public Integer getSoldNum() {
		return soldNum;
	}




	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}




	public Integer getTotalStocks() {
		return totalStocks;
	}




	public void setTotalStocks(Integer totalStocks) {
		this.totalStocks = totalStocks;
	}




	public String getDeliveryMode() {
		return deliveryMode;
	}




	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}




	public Long getDeliveryTemplateId() {
		return deliveryTemplateId;
	}




	public void setDeliveryTemplateId(Long deliveryTemplateId) {
		this.deliveryTemplateId = deliveryTemplateId;
	}




	public Date getCreateTime() {
		return createTime;
	}




	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	public Date getUpdateTime() {
		return updateTime;
	}




	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Date getPutawayTime() {
		return putawayTime;
	}




	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}




	public Integer getVersion() {
		return version;
	}




	public void setVersion(Integer version) {
		this.version = version;
	}




	public List<Sku> getSkuList() {
		return skuList;
	}




	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}




	public String getShopName() {
		return shopName;
	}




	public void setShopName(String shopName) {
		this.shopName = shopName;
	}




	public List<Long> getTagList() {
		return tagList;
	}




	public void setTagList(List<Long> tagList) {
		this.tagList = tagList;
	}




	@Data
    public static class DeliveryModeVO {

        /**
         * 用户自提
         */
        private Boolean hasUserPickUp;

        /**
         * 店铺配送
         */
        private Boolean hasShopDelivery;

		public Boolean getHasUserPickUp() {
			return hasUserPickUp;
		}

		public void setHasUserPickUp(Boolean hasUserPickUp) {
			this.hasUserPickUp = hasUserPickUp;
		}

		public Boolean getHasShopDelivery() {
			return hasShopDelivery;
		}

		public void setHasShopDelivery(Boolean hasShopDelivery) {
			this.hasShopDelivery = hasShopDelivery;
		}
        
        

    }
}
