/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel("购物车合计")
public class ShopCartAmountDto {

    @ApiModelProperty("总额")
    private Double totalMoney;

    @ApiModelProperty("总计")
    private Double finalMoney;

    @ApiModelProperty("减额")
    private Double subtractMoney;

    @ApiModelProperty("商品数量")
    private Integer count;

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getFinalMoney() {
		return finalMoney;
	}

	public void setFinalMoney(Double finalMoney) {
		this.finalMoney = finalMoney;
	}

	public Double getSubtractMoney() {
		return subtractMoney;
	}

	public void setSubtractMoney(Double subtractMoney) {
		this.subtractMoney = subtractMoney;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
    
    
}
