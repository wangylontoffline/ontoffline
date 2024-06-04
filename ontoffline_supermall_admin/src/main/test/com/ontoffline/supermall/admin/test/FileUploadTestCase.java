/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.ontoffline.supermall.service.AttachFileService;

import cn.hutool.core.date.DateUtil;

/**
 * @author wangylontoffline
 *
 */
public class FileUploadTestCase {
	
	@Autowired
	private static AttachFileService attachFileService;
	
	private static File f = new File("panxie.jpg");


    public static MultipartFile convert(File file) throws IOException {
	        FileInputStream inputStream = new FileInputStream(file);
	        return new MockMultipartFile(file.getName(), inputStream);
	 }

	/**
	 * 
	 */
	public FileUploadTestCase() {
		// TODO Auto-generated constructor stub
	}
	

	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MultipartFile mf = convert(f);
		String fileName = attachFileService.uploadFile(mf.getBytes(),mf.getOriginalFilename());
        System.out.println(ResponseEntity.ok(fileName));
        System.out.println(DateUtil.now() + ": test1 running.................");
	}

}
