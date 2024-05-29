/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.EmojiJsonSerializer;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@ApiModel("评论对象")
@Data
public class ProdCommDto {

    @ApiModelProperty(value = "id")
    private Long prodCommId;

    /**
     * 得分，0-5分
     */
    @ApiModelProperty(value = "得分，0-5分")
    private Integer score;

    /**
     * 是否匿名(1:是  0:否)
     */
    @ApiModelProperty(value = "是否匿名(1:是  0:否)")
    private Integer isAnonymous;

    /**
     * 掌柜回复
     */
    @ApiModelProperty(value = "掌柜回复")
    private String replyContent;

    /**
     * 记录时间
     */
    @ApiModelProperty(value = "记录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recTime;

    /**
     * 回复时间
     */
    @ApiModelProperty(value = "回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date replyTime;

    @JsonSerialize(using = EmojiJsonSerializer.class)
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 头像图片路径
     */
    @ApiModelProperty(value = "头像图片路径")
    private String pic;

    /**
     * 是否回复 0:未回复  1:已回复
     */
    @ApiModelProperty(value = "商家是否回复 0:未回复  1:已回复")
    private Integer replySts;

    /**
     * 评论图片
     */
    @JsonSerialize(using = ImgJsonSerializer.class)
    @ApiModelProperty(value = "评论图片")
    private String pics;

    /**
     * 评价等级
     */
    @ApiModelProperty(value = "0好评 1中评 2差评")
    private Byte evaluate;

    @ApiModelProperty(value = "评论内容")
    private String content;

	public Long getProdCommId() {
		return prodCommId;
	}

	public void setProdCommId(Long prodCommId) {
		this.prodCommId = prodCommId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getReplySts() {
		return replySts;
	}

	public void setReplySts(Integer replySts) {
		this.replySts = replySts;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public Byte getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Byte evaluate) {
		this.evaluate = evaluate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    

}
