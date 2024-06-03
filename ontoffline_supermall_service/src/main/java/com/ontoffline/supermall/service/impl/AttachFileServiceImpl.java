/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import java.util.Date;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.AttachFile;
import com.ontoffline.supermall.dao.AttachFileMapper;
import com.ontoffline.supermall.service.AttachFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ontoffline.supermall.common.bean.Qiniu;
import com.ontoffline.supermall.common.util.Json;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangylontoffline
 */

@Service
public class AttachFileServiceImpl extends ServiceImpl<AttachFileMapper, AttachFile> implements AttachFileService {

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;
	@Autowired
	private Qiniu qiniu;

    @Autowired
    private Auth auth;

    public final static String NORM_MONTH_PATTERN = "yyyy/MM/";

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String uploadFile(byte[] bytes,String originalName) throws QiniuException {
		String extName = FileUtil.extName(originalName);
		String fileName =DateUtil.format(new Date(), NORM_MONTH_PATTERN)+ IdUtil.simpleUUID() + "." + extName;


		AttachFile attachFile = new AttachFile();
		attachFile.setFilePath(fileName);
		attachFile.setFileSize(bytes.length);
		attachFile.setFileType(extName);
		attachFile.setUploadTime(new Date());
		attachFileMapper.insert(attachFile);

		String upToken = auth.uploadToken(qiniu.getBucket(),fileName);
	    Response response = uploadManager.put(bytes, fileName, upToken);
	    Json.parseObject(response.bodyString(),  DefaultPutRet.class);
		return fileName;
	}

	@Override
	public void deleteFile(String fileName){
		attachFileMapper.delete(new LambdaQueryWrapper<AttachFile>().eq(AttachFile::getFilePath,fileName));
		try {
			bucketManager.delete(qiniu.getBucket(), fileName);
		} catch (QiniuException e) {
			throw new RuntimeException(e);
		}
	}


}
