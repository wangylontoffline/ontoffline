/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod_tag_reference")
public class ProdTagReference implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 分组引用id
     */
    @TableId
    private Long referenceId;
    /**
     * 店铺id
     */
    private Long shopId;
    /**
     * 标签id
     */
    private Long tagId;
    /**
     * 商品id
     */
    private Long prodId;
    /**
     * 状态(1:正常,0:删除)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    
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
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	
	
}
