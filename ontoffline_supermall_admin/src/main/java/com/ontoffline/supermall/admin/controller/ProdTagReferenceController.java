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
import com.ontoffline.supermall.bean.model.ProdTagReference;
import com.ontoffline.supermall.common.annotation.SysLog;
import com.ontoffline.supermall.service.ProdTagReferenceService;
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
@RequestMapping("/generator/prodTagReference" )
public class ProdTagReferenceController {

    private ProdTagReferenceService prodTagReferenceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param prodTagReference 分组标签引用
     * @return 分页数据
     */
    @GetMapping("/page" )
    public ResponseEntity<IPage<ProdTagReference>> getProdTagReferencePage(PageParam page, ProdTagReference prodTagReference) {
        return ResponseEntity.ok(prodTagReferenceService.page(page, new LambdaQueryWrapper<ProdTagReference>()));
    }


    /**
     * 通过id查询分组标签引用
     * @param referenceId id
     * @return 单个数据
     */
    @GetMapping("/info/{referenceId}" )
    public ResponseEntity<ProdTagReference> getById(@PathVariable("referenceId" ) Long referenceId) {
        return ResponseEntity.ok(prodTagReferenceService.getById(referenceId));
    }

    /**
     * 新增分组标签引用
     * @param prodTagReference 分组标签引用
     * @return 是否新增成功
     */
    @SysLog("新增分组标签引用" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator:prodTagReference:save')" )
    public ResponseEntity<Boolean> save(@RequestBody @Valid ProdTagReference prodTagReference) {
        return ResponseEntity.ok(prodTagReferenceService.save(prodTagReference));
    }

    /**
     * 修改分组标签引用
     * @param prodTagReference 分组标签引用
     * @return 是否修改成功
     */
    @SysLog("修改分组标签引用" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator:prodTagReference:update')" )
    public ResponseEntity<Boolean> updateById(@RequestBody @Valid ProdTagReference prodTagReference) {
        return ResponseEntity.ok(prodTagReferenceService.updateById(prodTagReference));
    }

    /**
     * 通过id删除分组标签引用
     * @param referenceId id
     * @return 是否删除成功
     */
    @SysLog("删除分组标签引用" )
    @DeleteMapping("/{referenceId}" )
    @PreAuthorize("@pms.hasPermission('generator:prodTagReference:delete')" )
    public ResponseEntity<Boolean> removeById(@PathVariable Long referenceId) {
        return ResponseEntity.ok(prodTagReferenceService.removeById(referenceId));
    }

}
