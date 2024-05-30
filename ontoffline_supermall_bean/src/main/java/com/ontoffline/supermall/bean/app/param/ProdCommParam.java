/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel(value= "添加评论信息")
public class ProdCommParam {
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品id")
    private Long prodId;
    /**
     * 订单项ID
     */
    @ApiModelProperty(value = "订单项ID")
    private Long orderItemId;

    /**
     * 评价，0-5分
     */
    @ApiModelProperty(value = "评价，0-5分",required=true)
    @NotNull(message = "评价不能为空")
    private Integer score;

    @ApiModelProperty(value = "评论内容",required=true)
    private String content;

    @ApiModelProperty(value = "评论图片, 用逗号分隔")
    private String pics;

    /**
     * 是否匿名(1:是  0:否)
     */
    @ApiModelProperty(value = "是否匿名(1:是  0:否) 默认为否")
    private Integer isAnonymous;


    @ApiModelProperty(value = "* 评价(0好评 1中评 2差评)")
    private Integer evaluate;


	public Long getProdId() {
		return prodId;
	}


	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}


	public Long getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPics() {
		return pics;
	}


	public void setPics(String pics) {
		this.pics = pics;
	}


	public Integer getIsAnonymous() {
		return isAnonymous;
	}


	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}


	public Integer getEvaluate() {
		return evaluate;
	}


	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

    
}
