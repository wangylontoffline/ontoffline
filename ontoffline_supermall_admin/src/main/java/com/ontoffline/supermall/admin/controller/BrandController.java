/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.bean.model.Brand;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin:brand:page')")
    public ResponseEntity<IPage<Brand>> page(Brand brand,PageParam<Brand> page) {
        page.setAsc("first_char");
        IPage<Brand> brands = brandService.page(page,
                new LambdaQueryWrapper<Brand>()
                        .like(StrUtil.isNotBlank(brand.getBrandName()), Brand::getBrandName, brand.getBrandName()));
        return ResponseEntity.ok(brands);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("@pms.hasPermission('admin:brand:info')")
    public ResponseEntity<Brand> info(@PathVariable("id") Long id) {
        Brand brand = brandService.getById(id);
        return ResponseEntity.ok(brand);
    }

    /**
     * 保存
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin:brand:save')")
    public ResponseEntity<Void> save(@Valid Brand brand) {
        Brand dbBrand = brandService.getByBrandName(brand.getBrandName());
        if (dbBrand != null) {
            throw new OntofflineSupermallBindException("该品牌名称已存在");
        }
        brandService.save(brand);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin:brand:update')")
    public ResponseEntity<Void> update(@Valid Brand brand) {
        Brand dbBrand = brandService.getByBrandName(brand.getBrandName());
        if (dbBrand != null && !Objects.equals(dbBrand.getBrandId(), brand.getBrandId())) {
            throw new OntofflineSupermallBindException("该品牌名称已存在");
        }
        brandService.updateById(brand);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin:brand:delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.deleteByBrand(id);
        return ResponseEntity.ok().build();
    }

}
