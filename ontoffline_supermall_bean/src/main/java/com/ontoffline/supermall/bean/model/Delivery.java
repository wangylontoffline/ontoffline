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
@TableName("oto_delivery")
public class Delivery implements Serializable {
    /**
     * ID
     */
    @TableId

    private Long dvyId;

    /**
     * 物流公司名称
     */

    private String dvyName;

    /**
     * 公司主页
     */

    private String companyHomeUrl;

    /**
     * 建立时间
     */

    private Date recTime;

    /**
     * 修改时间
     */

    private Date modifyTime;

    /**
     * 物流查询接口
     */

    private String queryUrl;

	public Long getDvyId() {
		return dvyId;
	}

	public void setDvyId(Long dvyId) {
		this.dvyId = dvyId;
	}

	public String getDvyName() {
		return dvyName;
	}

	public void setDvyName(String dvyName) {
		this.dvyName = dvyName;
	}

	public String getCompanyHomeUrl() {
		return companyHomeUrl;
	}

	public void setCompanyHomeUrl(String companyHomeUrl) {
		this.companyHomeUrl = companyHomeUrl;
	}

	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
    
    
}