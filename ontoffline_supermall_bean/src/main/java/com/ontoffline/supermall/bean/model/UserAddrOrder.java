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
@TableName("oto_user_addr_order")
public class UserAddrOrder implements Serializable {
    /**
     * ID
     */
    @TableId


    private Long addrOrderId;

    /**
     * 地址ID
     */

    private Long addrId;

    /**
     * 用户ID
     */

    private String userId;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 省
     */

    private String province;
    
    /**
     * 城市
     */

    private String city;
    
    /**
     * 区
     */

    private String area;

    /**
     * 地址
     */
    private String addr;

    /**
     * 邮编
     */

    private String postCode;

    /**
     * 省ID
     */

    private Long provinceId;
    
    /**
     * 城市ID
     */

    private Long cityId;
    
    /**
     * 区域ID
     */

    private Long areaId;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 建立时间
     */

    private Date createTime;

    /**
     * 版本号
     */
    private Integer version;

	public Long getAddrOrderId() {
		return addrOrderId;
	}

	public void setAddrOrderId(Long addrOrderId) {
		this.addrOrderId = addrOrderId;
	}

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    
}