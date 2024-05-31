/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_basket")
public class Basket implements Serializable {
    /**
     * 主键
     */
    @TableId

    private Long basketId;

    /**
     * 店铺ID
     */

    private Long shopId;

    /**
     * 产品ID
     */

    private Long prodId;

    /**
     * SkuID
     */

    private Long skuId;

    /**
     * 用户ID
     */

    private String userId;

    /**
     * 购物车产品个数
     */

    private Integer basketCount;

    /**
     * 购物时间
     */

    private Date basketDate;

    /**
     * 满减活动ID
     */
    private Long discountId;

    /**
     * 分销推广人卡号
     */
    private String distributionCardNo;

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBasketCount() {
		return basketCount;
	}

	public void setBasketCount(Integer basketCount) {
		this.basketCount = basketCount;
	}

	public Date getBasketDate() {
		return basketDate;
	}

	public void setBasketDate(Date basketDate) {
		this.basketDate = basketDate;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public String getDistributionCardNo() {
		return distributionCardNo;
	}

	public void setDistributionCardNo(String distributionCardNo) {
		this.distributionCardNo = distributionCardNo;
	}
    
    
}