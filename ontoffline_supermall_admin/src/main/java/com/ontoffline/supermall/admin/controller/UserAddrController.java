/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.bean.model.UserAddr;
import com.ontoffline.supermall.common.annotation.SysLog;
import com.ontoffline.supermall.service.UserAddrService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangylontoffline
 */

@RestController
@AllArgsConstructor
@RequestMapping("/user/addr")
public class UserAddrController {

    private UserAddrService userAddrService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param userAddr 用户地址管理
     * @return 分页数据
     */
    @GetMapping("/page")
    public ResponseEntity<IPage<UserAddr>> getUserAddrPage(PageParam page, UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.page(page, new LambdaQueryWrapper<UserAddr>()));
    }


    /**
     * 通过id查询用户地址管理
     *
     * @param addrId id
     * @return 单个数据
     */
    @GetMapping("/info/{addrId}")
    public ResponseEntity<UserAddr> getById(@PathVariable("addrId") Long addrId) {
        return ResponseEntity.ok(userAddrService.getById(addrId));
    }

    /**
     * 新增用户地址管理
     *
     * @param userAddr 用户地址管理
     * @return 是否新增成功
     */
    @SysLog("新增用户地址管理")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('user:addr:save')")
    public ResponseEntity<Boolean> save(@RequestBody @Valid UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.save(userAddr));
    }

    /**
     * 修改用户地址管理
     *
     * @param userAddr 用户地址管理
     * @return 是否修改成功
     */
    @SysLog("修改用户地址管理")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('user:addr:update')")
    public ResponseEntity<Boolean> updateById(@RequestBody @Valid UserAddr userAddr) {
        return ResponseEntity.ok(userAddrService.updateById(userAddr));
    }

    /**
     * 通过id删除用户地址管理
     *
     * @param addrId id
     * @return 是否删除成功
     */
    @SysLog("删除用户地址管理")
    @DeleteMapping("/{addrId}")
    @PreAuthorize("@pms.hasPermission('user:addr:delete')")
    public ResponseEntity<Boolean> removeById(@PathVariable Long addrId) {
        return ResponseEntity.ok(userAddrService.removeById(addrId));
    }

}
