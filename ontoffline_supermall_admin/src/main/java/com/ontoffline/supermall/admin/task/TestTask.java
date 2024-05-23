/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.task;


import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ontoffline.supermall.service.AttachFileService;

/**
 * @author wangylontoffline
 */

@Slf4j
@Component
public class TestTask {
	@Autowired
	private AttachFileService attachFileService;
	
	MultipartFile file = (MultipartFile) new File("panxie.jpg");
	

    public void test1() throws IOException{
    	String fileName = attachFileService.uploadFile(file.getBytes(),file.getOriginalFilename());
        System.out.println(ResponseEntity.ok(fileName));
        System.out.println(DateUtil.now() + ": test1 running.................");

    }

    public void test2(String param){
        System.out.println(DateUtil.now() + ": test2 running.................");
    }
    
    
}
