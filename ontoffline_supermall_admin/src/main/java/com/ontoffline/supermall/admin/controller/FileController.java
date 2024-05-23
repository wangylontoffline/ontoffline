/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ontoffline.supermall.bean.dto.TinymceEditorDto;
import com.ontoffline.supermall.common.bean.Qiniu;
import com.ontoffline.supermall.service.AttachFileService;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/admin/file")
public class FileController {
	
	@Autowired
	private AttachFileService attachFileService;
	@Autowired
	private Qiniu qiniu;
	
	@PostMapping("/upload/element")
	public ResponseEntity<String> uploadElementFile(@RequestParam("file") MultipartFile file) throws IOException{
		if(file.isEmpty()){
            return ResponseEntity.noContent().build();
        }
		String fileName = attachFileService.uploadFile(file.getBytes(),file.getOriginalFilename());
        return ResponseEntity.ok(fileName);
	}
	
	@PostMapping("/upload/tinymceEditor")
	public ResponseEntity<String> uploadTinymceEditorImages(@RequestParam("editorFile") MultipartFile editorFile) throws IOException{
		String fileName =  attachFileService.uploadFile(editorFile.getBytes(),editorFile.getOriginalFilename());
        return ResponseEntity.ok(qiniu.getResourcesUrl() + fileName);
	}
	
}
