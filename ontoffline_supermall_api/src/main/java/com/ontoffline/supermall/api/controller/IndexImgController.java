/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import com.ontoffline.supermall.bean.app.dto.IndexImgDto;
import com.ontoffline.supermall.bean.model.IndexImg;
import com.ontoffline.supermall.service.IndexImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangylontoffline
 */

@RestController
@Api(tags = "首页轮播图接口")
public class IndexImgController {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private IndexImgService indexImgService;

    /**
     * 首页轮播图接口
     */
    @GetMapping("/indexImgs")
    @ApiOperation(value = "首页轮播图", notes = "获取首页轮播图列表信息")
    public ResponseEntity<List<IndexImgDto>> indexImgs() {
        List<IndexImg> indexImgList = indexImgService.listIndexImgs();
        List<IndexImgDto> indexImgDtos = mapperFacade.mapAsList(indexImgList, IndexImgDto.class);
        return ResponseEntity.ok(indexImgDtos);
    }
}
