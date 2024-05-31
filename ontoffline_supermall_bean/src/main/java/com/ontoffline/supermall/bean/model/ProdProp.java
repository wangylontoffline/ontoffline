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
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod_prop")
public class ProdProp implements Serializable {
    private static final long serialVersionUID = -8761177918672000191L;

    /**
     * 属性id
     */
    @TableId
    private Long propId;

    /**
     * 属性名称
     */
    @NotBlank(message = "属性名称不能为空")
    private String propName;

    /**
     * 1:销售属性(规格); 2:参数属性;
     */
    private Integer rule;

    private Long shopId;

    /**
     * 属性值
     */
    @TableField(exist=false)
    @NotEmpty(message="规格属性值不能为空")
    private List<ProdPropValue> prodPropValues;

	public List<ProdPropValue> getProdPropValues() {
		return prodPropValues;
	}

	public void setProdPropValues(List<ProdPropValue> prodPropValues) {
		this.prodPropValues = prodPropValues;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
    
    

}
