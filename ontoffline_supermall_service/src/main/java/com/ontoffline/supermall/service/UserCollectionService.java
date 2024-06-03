/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.app.dto.UserCollectionDto;
import com.ontoffline.supermall.bean.model.UserCollection;

/**
 * @author wangylontoffline
 */

public interface UserCollectionService extends IService<UserCollection> {
    IPage<UserCollectionDto> getUserCollectionDtoPageByUserId(Page page, String userId);
}
