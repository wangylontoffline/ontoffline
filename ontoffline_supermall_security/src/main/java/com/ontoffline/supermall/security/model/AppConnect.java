/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.model;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_app_connect")
public class AppConnect {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 本系统userId
     */

    private String userId;

    /**
     * 第三方系统id 1：微信小程序
     */

    private Integer appId;

    /**
     * 第三方系统昵称
     */

    private String nickName;

    /**
     * 第三方系统头像
     */

    private String imageUrl;

    /**
     * 第三方系统userid
     */

    private String bizUserId;

    /**
     * 第三方系统unionid
     */

    private String bizUnionid;

	public void setAppId(Integer appId) {
		this.appId = appId;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBizUserId() {
		return bizUserId;
	}

	public void setBizUserId(String bizUserId) {
		this.bizUserId = bizUserId;
	}

	public String getBizUnionid() {
		return bizUnionid;
	}

	public void setBizUnionid(String bizUnionid) {
		this.bizUnionid = bizUnionid;
	}

	public Integer getAppId() {
		return appId;
	}

}