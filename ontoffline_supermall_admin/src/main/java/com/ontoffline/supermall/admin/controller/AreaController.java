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
import com.ontoffline.supermall.bean.model.Area;
import com.ontoffline.supermall.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/admin/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin:area:page')")
    public ResponseEntity<IPage<Area>> page(Area area,PageParam<Area> page) {
        IPage<Area> sysUserPage = areaService.page(page, new LambdaQueryWrapper<Area>());
        return ResponseEntity.ok(sysUserPage);
    }

    /**
     * 获取省市
     */
    @GetMapping("/list")
    @PreAuthorize("@pms.hasPermission('admin:area:list')")
    public ResponseEntity<List<Area>> list(Area area) {
        List<Area> areas = areaService.list(new LambdaQueryWrapper<Area>()
                .like(area.getAreaName() != null, Area::getAreaName, area.getAreaName()));
        return ResponseEntity.ok(areas);
    }

    /**
     * 通过父级id获取区域列表
     */
    @GetMapping("/listByPid")
    public ResponseEntity<List<Area>> listByPid(Long pid) {
        List<Area> list = areaService.listByPid(pid);
        return ResponseEntity.ok(list);
    }

    /**
     * 获取信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("@pms.hasPermission('admin:area:info')")
    public ResponseEntity<Area> info(@PathVariable("id") Long id) {
        Area area = areaService.getById(id);
        return ResponseEntity.ok(area);
    }

    /**
     * 保存
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin:area:save')")
    public ResponseEntity<Void> save(@Valid @RequestBody Area area) {
        if (area.getParentId() != null) {
            Area parentArea = areaService.getById(area.getParentId());
            area.setLevel(parentArea.getLevel() + 1);
            areaService.removeAreaCacheByParentId(area.getParentId());
        }
        areaService.save(area);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin:area:update')")
    public ResponseEntity<Void> update(@Valid @RequestBody Area area) {
        areaService.updateById(area);
        areaService.removeAreaCacheByParentId(area.getParentId());
        return ResponseEntity.ok().build();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin:area:delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Area area = areaService.getById(id);
        areaService.removeById(id);
        areaService.removeAreaCacheByParentId(area.getParentId());
        return ResponseEntity.ok().build();
    }

}
