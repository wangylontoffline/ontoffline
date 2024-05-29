/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangylontoffline
 */

@Data
public class OrderShopDto implements Serializable {

    /**
     * 店铺ID
     **/
    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    /**
     * 店铺名称
     **/
    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "实际总值", required = true)
    private Double actualTotal;

    @ApiModelProperty(value = "商品总值", required = true)
    private Double total;

    @ApiModelProperty(value = "商品总数", required = true)
    private Integer totalNum;

    @ApiModelProperty(value = "地址Dto", required = true)
    private UserAddrDto userAddrDto;

    @ApiModelProperty(value = "产品信息", required = true)
    private List<OrderItemDto> orderItemDtos;

    @ApiModelProperty(value = "运费", required = true)
    private Double transfee;

    @ApiModelProperty(value = "优惠总额", required = true)
    private Double reduceAmount;

    @ApiModelProperty(value = "促销活动优惠金额", required = true)
    private Double discountMoney;

    @ApiModelProperty(value = "优惠券优惠金额", required = true)
    private Double couponMoney;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "订单创建时间", required = true)
    private Date createTime;

    /**
     * 订单备注信息
     */
    @ApiModelProperty(value = "订单备注信息", required = true)
    private String remarks;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态", required = true)
    private Integer status;

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

	public Double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public UserAddrDto getUserAddrDto() {
		return userAddrDto;
	}

	public void setUserAddrDto(UserAddrDto userAddrDto) {
		this.userAddrDto = userAddrDto;
	}

	public List<OrderItemDto> getOrderItemDtos() {
		return orderItemDtos;
	}

	public void setOrderItemDtos(List<OrderItemDto> orderItemDtos) {
		this.orderItemDtos = orderItemDtos;
	}

	public Double getTransfee() {
		return transfee;
	}

	public void setTransfee(Double transfee) {
		this.transfee = transfee;
	}

	public Double getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(Double reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public Double getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}
