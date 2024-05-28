/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.bean.app.dto.ProdCommDataDto;
import com.ontoffline.supermall.bean.app.dto.ProdCommDto;
import com.ontoffline.supermall.bean.app.param.ProdCommParam;
import com.ontoffline.supermall.bean.model.OrderItem;
import com.ontoffline.supermall.bean.model.ProdComm;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.OrderItemService;
import com.ontoffline.supermall.service.ProdCommService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/prodComm")
@Api(tags = "评论接口")
@AllArgsConstructor
public class ProdCommController {
	
    @Autowired
    private ProdCommService prodCommService;
    
    
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("/prodCommData")
    @ApiOperation(value = "返回商品评论数据(好评率 好评数量 中评数 差评数)", notes = "根据商品id获取")
    public ResponseEntity<ProdCommDataDto> getProdCommData(Long prodId) {
        return ResponseEntity.ok(prodCommService.getProdCommDataByProdId(prodId));
    }

    @GetMapping("/prodCommPageByUser")
    @ApiOperation(value = "根据用户返回评论分页数据", notes = "传入页码")
    public ResponseEntity<IPage<ProdCommDto>> getProdCommPage(PageParam page) {
        return ResponseEntity.ok(prodCommService.getProdCommDtoPageByUserId(page, SecurityUtils.getUser().getUserId()));
    }

    @GetMapping("/prodCommPageByProd")
    @ApiOperation(value = "根据商品返回评论分页数据", notes = "传入商品id和页码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "evaluate", value = "-1或null 全部，0好评 1中评 2差评 3有图", required = true, dataType = "Long"),
    })
    public ResponseEntity<IPage<ProdCommDto>> getProdCommPageByProdId(PageParam page, Long prodId, Integer evaluate) {
        return ResponseEntity.ok(prodCommService.getProdCommDtoPageByProdId(page, prodId, evaluate));
    }

    @PostMapping
    @ApiOperation(value = "添加评论")
    public ResponseEntity<Void> saveProdCommPage(@Valid @RequestBody ProdCommParam prodCommParam) {
    	List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderNumber(prodCommParam.getOrderItemId()+"");
    	for(OrderItem orderItem: orderItems) {
        ProdComm prodComm = new ProdComm();
        prodComm.setProdId(orderItem.getProdId());
        prodComm.setOrderItemId(prodCommParam.getOrderItemId());
        prodComm.setUserId(SecurityUtils.getUser().getUserId());
        prodComm.setScore(prodCommParam.getScore());
        prodComm.setContent(prodCommParam.getContent());
        prodComm.setPics(prodCommParam.getPics());
        prodComm.setIsAnonymous(prodCommParam.getIsAnonymous());
        prodComm.setRecTime(new Date());
        prodComm.setStatus(0);
        prodComm.setEvaluate(prodCommParam.getEvaluate());
        prodCommService.save(prodComm);
        }      
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiOperation(value = "删除评论", notes = "根据id删除")
    public ResponseEntity<Void> deleteProdComm(Long prodCommId) {
        prodCommService.removeById(prodCommId);
        return ResponseEntity.ok().build();
    }
}
