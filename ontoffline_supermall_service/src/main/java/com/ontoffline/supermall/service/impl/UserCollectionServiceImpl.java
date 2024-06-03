/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.app.dto.UserCollectionDto;
import com.ontoffline.supermall.bean.model.UserCollection;
import com.ontoffline.supermall.dao.UserCollectionMapper;
import com.ontoffline.supermall.service.UserCollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Override
    public IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId) {
        return userCollectionMapper.getUserCollectionDtoPageByUserId(page,userId);
    }
}
