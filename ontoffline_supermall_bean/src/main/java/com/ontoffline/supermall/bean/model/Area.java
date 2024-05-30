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
@TableName("oto_area")
public class Area implements Serializable {
    private static final long serialVersionUID = -6013320537436191451L;
    @TableId
    @ApiModelProperty(value = "地区id",required=true)
    private Long areaId;

    @ApiModelProperty(value = "地区名称",required=true)
    private String areaName;

    @ApiModelProperty(value = "地区上级id",required=true)
    public Long parentId;

    @ApiModelProperty(value = "地区层级",required=true)
    private Integer level;

    @TableField(exist=false)
    private List<Area> areas;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
    
    
}
