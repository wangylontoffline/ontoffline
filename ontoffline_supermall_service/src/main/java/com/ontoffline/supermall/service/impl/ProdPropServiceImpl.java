/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.enums.ProdPropRule;
import com.ontoffline.supermall.bean.model.ProdProp;
import com.ontoffline.supermall.dao.CategoryPropMapper;
import com.ontoffline.supermall.dao.ProdPropMapper;
import com.ontoffline.supermall.dao.ProdPropValueMapper;
import com.ontoffline.supermall.service.ProdPropService;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.PageAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author wangylontoffline
 */

@Service
public class ProdPropServiceImpl extends ServiceImpl<ProdPropMapper, ProdProp> implements ProdPropService {

    @Autowired
    private ProdPropMapper prodPropMapper;

    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

    @Autowired
    private CategoryPropMapper categoryPropMapper;

    @Override
    public IPage<ProdProp> pagePropAndValue(ProdProp prodProp, Page<ProdProp> page) {

        page.setRecords(prodPropMapper.listPropAndValue(new PageAdapter(page), prodProp));
        page.setTotal(prodPropMapper.countPropAndValue(prodProp));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProdPropAndValues(@Valid ProdProp prodProp) {
        ProdProp dbProdProp = prodPropMapper.getProdPropByPropNameAndShopId(prodProp.getPropName(), prodProp.getShopId(), prodProp.getRule());
        if (dbProdProp != null) {
            throw new OntofflineSupermallBindException("已有相同名称规格");
        }
        prodPropMapper.insert(prodProp);
        if (CollUtil.isEmpty(prodProp.getProdPropValues())) {
            return;
        }
        prodPropValueMapper.insertPropValues(prodProp.getPropId(), prodProp.getProdPropValues());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProdPropAndValues(ProdProp prodProp) {
        ProdProp dbProdProp = prodPropMapper.getProdPropByPropNameAndShopId(prodProp.getPropName(), prodProp.getShopId(), prodProp.getRule());
        if (dbProdProp != null && !Objects.equals(prodProp.getPropName(), dbProdProp.getPropName())) {
            throw new OntofflineSupermallBindException("已有相同名称规格");
        }
        prodPropMapper.updateById(prodProp);
        // 先删除原有的属性值，再添加新的属性值
        prodPropValueMapper.deleteByPropId(prodProp.getPropId());
        if (CollUtil.isEmpty(prodProp.getProdPropValues())) {
            return;
        }
        prodPropValueMapper.insertPropValues(prodProp.getPropId(), prodProp.getProdPropValues());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProdPropAndValues(Long propId, Integer propRule, Long shopId) {
        int deleteRows = prodPropMapper.deleteByPropId(propId, propRule, shopId);
        if (deleteRows == 0) {
            return;
        }
        // 删除原有的属性值
        prodPropValueMapper.deleteByPropId(propId);

        // 如果是参数，删除参数与分类关联信息
        if (ProdPropRule.ATTRIBUTE.value().equals(propRule)) {
            categoryPropMapper.deleteByPropId(propId);
        }
    }

    @Override
    public List<ProdProp> listByCategoryId(Long categoryId) {
        return prodPropMapper.listByCategoryId(categoryId);
    }
}
