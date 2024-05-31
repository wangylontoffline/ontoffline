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

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_transfee")
public class Transfee implements Serializable {
    private static final long serialVersionUID = 8039640964056626028L;
    /**
     * 运费项id
     */
    @TableId
    @ApiModelProperty(value = "运费项id",required=true)
    private Long transfeeId;

    /**
     * 运费模板id
     */

    @ApiModelProperty(value = "运费模板id",required=true)
    private Long transportId;

    /**
     * 续件数量
     */

    @ApiModelProperty(value = "续件数量",required=true)
    private Double continuousPiece;

    /**
     * 首件数量
     */

    @ApiModelProperty(value = "首件数量",required=true)
    private Double firstPiece;

    /**
     * 续件费用
     */

    @ApiModelProperty(value = "续件费用",required=true)
    private Double continuousFee;

    /**
     * 首件费用
     */

    @ApiModelProperty(value = "首件费用",required=true)
    private Double firstFee;

    @TableField(exist=false)
    @ApiModelProperty(value = "指定条件运费城市项",required=true)
    private List<Area> cityList;

	public Long getTransfeeId() {
		return transfeeId;
	}

	public void setTransfeeId(Long transfeeId) {
		this.transfeeId = transfeeId;
	}

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public Double getContinuousPiece() {
		return continuousPiece;
	}

	public void setContinuousPiece(Double continuousPiece) {
		this.continuousPiece = continuousPiece;
	}

	public Double getFirstPiece() {
		return firstPiece;
	}

	public void setFirstPiece(Double firstPiece) {
		this.firstPiece = firstPiece;
	}

	public Double getContinuousFee() {
		return continuousFee;
	}

	public void setContinuousFee(Double continuousFee) {
		this.continuousFee = continuousFee;
	}

	public Double getFirstFee() {
		return firstFee;
	}

	public void setFirstFee(Double firstFee) {
		this.firstFee = firstFee;
	}

	public List<Area> getCityList() {
		return cityList;
	}

	public void setCityList(List<Area> cityList) {
		this.cityList = cityList;
	}
    
    

}
