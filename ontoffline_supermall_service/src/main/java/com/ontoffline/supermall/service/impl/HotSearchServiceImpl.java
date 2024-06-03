/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.dto.HotSearchDto;
import com.ontoffline.supermall.bean.model.HotSearch;
import com.ontoffline.supermall.dao.HotSearchMapper;
import com.ontoffline.supermall.service.HotSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Service
public class HotSearchServiceImpl extends ServiceImpl<HotSearchMapper, HotSearch> implements HotSearchService {

    @Autowired
    private HotSearchMapper hotSearchMapper;

    @Override
    @Cacheable(cacheNames = "HotSearchDto", key = "#shopId")
    public List<HotSearchDto> getHotSearchDtoByshopId(Long shopId) {
        return hotSearchMapper.getHotSearchDtoByShopId(shopId);
    }

    @Override
    @CacheEvict(cacheNames = "HotSearchDto", key = "#shopId")
    public void removeHotSearchDtoCacheByshopId(Long shopId) {

    }
}
