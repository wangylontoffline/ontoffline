/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_attach_file")
public class AttachFile implements Serializable {
    @TableId

    private Long fileId;

    /**
     * 文件路径
     */

    private String filePath;

    /**
     * 文件类型
     */

    private String fileType;

    /**
     * 文件大小
     */

    private Integer fileSize;

    /**
     * 上传时间
     */

    private Date uploadTime;

    /**
     * 文件关联的表主键id
     */

    private Long fileJoinId;
    
    /**
     * 文件关联表类型：1 商品表  @see FileJoinType
     */

    private Integer fileJoinType;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Long getFileJoinId() {
		return fileJoinId;
	}

	public void setFileJoinId(Long fileJoinId) {
		this.fileJoinId = fileJoinId;
	}

	public Integer getFileJoinType() {
		return fileJoinType;
	}

	public void setFileJoinType(Integer fileJoinType) {
		this.fileJoinType = fileJoinType;
	}
    
    
}