/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_transfee_free")
public class TransfeeFree implements Serializable {
    private static final long serialVersionUID = -2811714952219888223L;
    /**
     * 指定条件包邮项id
     */
    @TableId
    @ApiModelProperty(value = "指定条件包邮项id",required=true)
    private Long transfeeFreeId;

    /**
     * 运费模板id
     */

    @ApiModelProperty(value = "运费模板id",required=true)
    private Long transportId;

    /**
     * 包邮方式 （0 满x件/重量/体积包邮 1满金额包邮 2满x件/重量/体积且满金额包邮）
     */

    @ApiModelProperty(value = "包邮方式 （0 满x件/重量/体积包邮 1满金额包邮 2满x件/重量/体积且满金额包邮）",required=true)
    private Integer freeType;

    /**
     * 需满金额
     */
    @ApiModelProperty(value = "需满金额",required=true)
    private Double amount;

    /**
     * 包邮x件/重量/体积
     */
    @ApiModelProperty(value = "包邮x件/重量/体积",required=true)
    private Double piece;

    /**
     * 指定条件包邮城市项
     */
    @TableField(exist=false)
    @ApiModelProperty(value = "指定条件包邮城市项",required=true)
    private List<Area> freeCityList;

	public Long getTransfeeFreeId() {
		return transfeeFreeId;
	}

	public void setTransfeeFreeId(Long transfeeFreeId) {
		this.transfeeFreeId = transfeeFreeId;
	}

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public Integer getFreeType() {
		return freeType;
	}

	public void setFreeType(Integer freeType) {
		this.freeType = freeType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPiece() {
		return piece;
	}

	public void setPiece(Double piece) {
		this.piece = piece;
	}

	public List<Area> getFreeCityList() {
		return freeCityList;
	}

	public void setFreeCityList(List<Area> freeCityList) {
		this.freeCityList = freeCityList;
	}
    
    
}
