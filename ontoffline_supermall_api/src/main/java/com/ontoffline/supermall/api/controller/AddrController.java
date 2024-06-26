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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.app.dto.UserAddrDto;
import com.ontoffline.supermall.bean.app.param.AddrParam;
import com.ontoffline.supermall.bean.model.UserAddr;
import com.ontoffline.supermall.service.UserAddrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/address")
@Api(tags = "地址接口")
@AllArgsConstructor
public class AddrController {

    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private UserAddrService userAddrService;

    /**
     * 选择订单配送地址
     */
    @GetMapping("/list")
    @ApiOperation(value = "用户地址列表", notes = "获取用户的所有地址信息")
    public ResponseEntity<List<UserAddrDto>> dvyList() {
        String userId = SecurityUtils.getUser().getUserId();
        List<UserAddr> userAddrs = userAddrService.list(new LambdaQueryWrapper<UserAddr>().eq(UserAddr::getUserId, userId).orderByDesc(UserAddr::getCommonAddr).orderByDesc(UserAddr::getUpdateTime));
        return ResponseEntity.ok(mapperFacade.mapAsList(userAddrs, UserAddrDto.class));
    }

    @PostMapping("/addAddr")
    @ApiOperation(value = "新增用户地址", notes = "新增用户地址")
    public ResponseEntity<String> addAddr(@Valid @RequestBody AddrParam addrParam) {
        String userId = SecurityUtils.getUser().getUserId();

        if (addrParam.getAddrId() != null && addrParam.getAddrId() != 0) {
            return ResponseEntity.badRequest().body("该地址已存在");
        }
        int addrCount = userAddrService.count(new LambdaQueryWrapper<UserAddr>().eq(UserAddr::getUserId, userId));
        UserAddr userAddr = mapperFacade.map(addrParam, UserAddr.class);

        if (addrCount == 0) {
            userAddr.setCommonAddr(1);
        } else {
            userAddr.setCommonAddr(0);
        }
        userAddr.setUserId(userId);
        userAddr.setStatus(1);
        userAddr.setCreateTime(new Date());
        userAddr.setUpdateTime(new Date());
        userAddrService.save(userAddr);
        if (userAddr.getCommonAddr() == 1) {
            // 清除默认地址缓存
            userAddrService.removeUserAddrByUserId(0L, userId);
        }
        return ResponseEntity.ok("添加地址成功");
    }

    /**
     * 修改订单配送地址
     */
    @PutMapping("/updateAddr")
    @ApiOperation(value = "修改订单用户地址", notes = "修改用户地址")
    public ResponseEntity<String> updateAddr(@Valid @RequestBody AddrParam addrParam) {
        String userId = SecurityUtils.getUser().getUserId();

        UserAddr dbUserAddr = userAddrService.getUserAddrByUserId(addrParam.getAddrId(), userId);
        if (dbUserAddr == null) {
            return ResponseEntity.badRequest().body("该地址已被删除");
        }

        UserAddr userAddr = mapperFacade.map(addrParam, UserAddr.class);
        userAddr.setUserId(userId);
        userAddr.setUpdateTime(new Date());
        userAddrService.updateById(userAddr);
        // 清除当前地址缓存
        userAddrService.removeUserAddrByUserId(addrParam.getAddrId(), userId);
        // 清除默认地址缓存
        userAddrService.removeUserAddrByUserId(0L, userId);
        return ResponseEntity.ok("修改地址成功");
    }

    /**
     * 删除订单配送地址
     */
    @DeleteMapping("/deleteAddr/{addrId}")
    @ApiOperation(value = "删除订单用户地址", notes = "根据地址id，删除用户地址")
    @ApiImplicitParam(name = "addrId", value = "地址ID", required = true, dataType = "Long")
    public ResponseEntity<String> deleteDvy(@PathVariable("addrId") Long addrId) {
        String userId = SecurityUtils.getUser().getUserId();
        UserAddr userAddr = userAddrService.getUserAddrByUserId(addrId, userId);
        if (userAddr == null) {
            return ResponseEntity.badRequest().body("该地址已被删除");
        }
        if (userAddr.getCommonAddr() == 1) {
            return ResponseEntity.badRequest().body("默认地址无法删除");
        }
        userAddrService.removeById(addrId);
        userAddrService.removeUserAddrByUserId(addrId, userId);
        return ResponseEntity.ok("删除地址成功");
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/defaultAddr/{addrId}")
    @ApiOperation(value = "设置默认地址", notes = "根据地址id，设置默认地址")
    public ResponseEntity<String> defaultAddr(@PathVariable("addrId") Long addrId) {
        String userId = SecurityUtils.getUser().getUserId();

        userAddrService.updateDefaultUserAddr(addrId, userId);

        userAddrService.removeUserAddrByUserId(0L, userId);
        userAddrService.removeUserAddrByUserId(addrId, userId);
        return ResponseEntity.ok("修改地址成功");
    }

    /**
     * 获取地址信息订单配送地址
     */
    @GetMapping("/addrInfo/{addrId}")
    @ApiOperation(value = "获取地址信息", notes = "根据地址id，获取地址信息")
    @ApiImplicitParam(name = "addrId", value = "地址ID", required = true, dataType = "Long")
    public ResponseEntity<UserAddrDto> addrInfo(@PathVariable("addrId") Long addrId) {
        String userId = SecurityUtils.getUser().getUserId();
        UserAddr userAddr = userAddrService.getUserAddrByUserId(addrId, userId);
        if (userAddr == null) {
            throw new OntofflineSupermallBindException("该地址已被删除");
        }
        return ResponseEntity.ok(mapperFacade.map(userAddr, UserAddrDto.class));
    }

}
