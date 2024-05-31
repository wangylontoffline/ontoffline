/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.bean.vo.UserVO;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod_comm")
@EqualsAndHashCode
public class ProdComm implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long prodCommId;
    /**
     * 商品ID
     */
    private Long prodId;
    /**
     * 订单项ID
     */
    private Long orderItemId;
    /**
     * 评论用户ID
     */
    private String userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 掌柜回复
     */
    private String replyContent;
    /**
     * 记录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recTime;
    /**
     * 回复时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
    /**
     * 是否回复 0:未回复  1:已回复
     */
    private Integer replySts;
    /**
     * IP来源
     */
    private String postip;
    /**
     * 得分，0-5分
     */
    private Integer score;
    /**
     * 有用的计数
     */
    private Integer usefulCounts;
    /**
     * 晒图的字符串 以逗号分隔
     */
    @JsonSerialize(using = ImgJsonSerializer.class)
    private String pics;
    /**
     * 是否匿名(1:是  0:否)
     */
    private Integer isAnonymous;
    /**
     * 是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1
     */
    private Integer status;

    /**
     * 评价(0好评 1中评 2差评)
     */
    private Integer evaluate;

    /**
     * 关联用户
     */
    @TableField(exist = false)
    private UserVO user;

    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String prodName;

	public Long getProdCommId() {
		return prodCommId;
	}

	public void setProdCommId(Long prodCommId) {
		this.prodCommId = prodCommId;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReplySts() {
		return replySts;
	}

	public void setReplySts(Integer replySts) {
		this.replySts = replySts;
	}

	public String getPostip() {
		return postip;
	}

	public void setPostip(String postip) {
		this.postip = postip;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUsefulCounts() {
		return usefulCounts;
	}

	public void setUsefulCounts(Integer usefulCounts) {
		this.usefulCounts = usefulCounts;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
    
    
}
