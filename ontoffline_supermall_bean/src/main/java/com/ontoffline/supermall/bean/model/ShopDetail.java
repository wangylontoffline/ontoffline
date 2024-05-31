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
@TableName("oto_shop_detail")
public class ShopDetail implements Serializable{
    private static final long serialVersionUID = 3300529542917772262L;
    /**
     * 店铺id
     */
    @TableId
    private Long shopId;

    /**
     * 店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一
     */
    private String shopName;

    /**
     * 店长用户id
     */
    private String userId;

    /**
     * 店铺类型
     */
    private Integer shopType;

    /**
     * 店铺简介(可修改)
     */
    private String intro;

    /**
     * 店铺公告(可修改)
     */
    private String shopNotice;

    /**
     * 店铺行业(餐饮、生鲜果蔬、鲜花等)
     */
    private Integer shopIndustry;

    /**
     * 店长
     */
    private String shopOwner;

    /**
     * 店铺绑定的手机(登录账号：唯一)
     */
    private String mobile;

    /**
     * 店铺联系电话
     */
    private String tel;

    /**
     * 店铺所在纬度(可修改)
     */
    private String shopLat;

    /**
     * 店铺所在经度(可修改)
     */
    private String shopLng;

    /**
     * 店铺详细地址
     */
    private String shopAddress;

    /**
     * 店铺所在省份（描述）
     */
    private String province;

    /**
     * 店铺所在城市（描述）
     */
    private String city;

    /**
     * 店铺所在区域（描述）
     */
    private String area;

    /**
     * 店铺省市区代码，用于回显
     */
    private String pcaCode;

    /**
     * 店铺logo(可修改)
     */
    private String shopLogo;

    /**
     * 店铺相册
     */
    private String shopPhotos;

    /**
     * 每天营业时间段(可修改)
     */
    private String openTime;

    /**
     * 店铺状态(-1:未开通 0: 停业中 1:营业中)，可修改
     */
    private Integer shopStatus;

    /**
     * 0:商家承担运费; 1:买家承担运费
     */
    private Integer transportType;

    /**
     * 固定运费
     */
    private Double fixedFreight;

    /**
     * 满X包邮
     */
    private Double fullFreeShipping;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分销设置(0关闭 1开启)
     */
    private Integer isDistribution;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getShopNotice() {
		return shopNotice;
	}

	public void setShopNotice(String shopNotice) {
		this.shopNotice = shopNotice;
	}

	public Integer getShopIndustry() {
		return shopIndustry;
	}

	public void setShopIndustry(Integer shopIndustry) {
		this.shopIndustry = shopIndustry;
	}

	public String getShopOwner() {
		return shopOwner;
	}

	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getShopLat() {
		return shopLat;
	}

	public void setShopLat(String shopLat) {
		this.shopLat = shopLat;
	}

	public String getShopLng() {
		return shopLng;
	}

	public void setShopLng(String shopLng) {
		this.shopLng = shopLng;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPcaCode() {
		return pcaCode;
	}

	public void setPcaCode(String pcaCode) {
		this.pcaCode = pcaCode;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopPhotos() {
		return shopPhotos;
	}

	public void setShopPhotos(String shopPhotos) {
		this.shopPhotos = shopPhotos;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public Integer getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}

	public Integer getTransportType() {
		return transportType;
	}

	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}

	public Double getFixedFreight() {
		return fixedFreight;
	}

	public void setFixedFreight(Double fixedFreight) {
		this.fixedFreight = fixedFreight;
	}

	public Double getFullFreeShipping() {
		return fullFreeShipping;
	}

	public void setFullFreeShipping(Double fullFreeShipping) {
		this.fullFreeShipping = fullFreeShipping;
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

	public Integer getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}
    
    
}
