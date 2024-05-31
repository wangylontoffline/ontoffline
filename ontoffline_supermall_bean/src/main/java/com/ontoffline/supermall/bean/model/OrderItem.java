/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 7307405761190788407L;
    /**
     * 订单项ID
     */
    @TableId(type = IdType.AUTO)
    private Long orderItemId;


    private Long shopId;

    /**
     * 订单sub_number
     */

    private String orderNumber;

    /**
     * 产品ID
     */

    private Long prodId;

    /**
     * 产品SkuID
     */

    private Long skuId;

    /**
     * 购物车产品个数
     */

    private Integer prodCount;

    /**
     * 产品名称
     */

    private String prodName;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 产品主图片路径
     */
    private String pic;

    /**
     * 产品价格
     */
    private Double price;

    /**
     * 用户Id
     */

    private String userId;

    /**
     * 商品总金额
     */
    private Double productTotalAmount;

    /**
     * 购物时间
     */

    private Date recTime;

    /**
     * 评论状态： 0 未评价  1 已评价
     */

    private Integer commSts;

    /**
     * 推广员使用的推销卡号
     */
    private String distributionCardNo;

    /**
     * 加入购物车的时间
     */
    private Date basketDate;

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

	public Integer getProdCount() {
		return prodCount;
	}

	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(Double productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}

	public Integer getCommSts() {
		return commSts;
	}

	public void setCommSts(Integer commSts) {
		this.commSts = commSts;
	}

	public String getDistributionCardNo() {
		return distributionCardNo;
	}

	public void setDistributionCardNo(String distributionCardNo) {
		this.distributionCardNo = distributionCardNo;
	}

	public Date getBasketDate() {
		return basketDate;
	}

	public void setBasketDate(Date basketDate) {
		this.basketDate = basketDate;
	}
    
    
}
