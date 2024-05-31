/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod_prop_value")
public class ProdPropValue implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6604406039354172708L;

	/**
     * 属性值ID
     */
    @TableId

    private Long valueId;

    /**
     * 属性值名称
     */

    private String propValue;

    /**
     * 属性ID
     */

    private Long propId;

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}
    

}