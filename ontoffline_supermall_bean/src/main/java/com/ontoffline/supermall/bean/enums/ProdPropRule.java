/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.enums;

/**
 * @author wangylontoffline
 */

public enum ProdPropRule {

	// 规格属性 (用于商品商品发布时，关联sku)
	SPEC(1),
	
	// 规格参数(用于商品搜索时，与分类关联搜索)
	ATTRIBUTE(2);
	
	private Integer num;
	
	public Integer value() {
		return num;
	}
	
	ProdPropRule(Integer num){
		this.num = num;
	}
	
	public static ProdPropRule instance(Integer value) {
		ProdPropRule[] enums = values();
		for (ProdPropRule statusEnum : enums) {
			if (statusEnum.value().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}
}
