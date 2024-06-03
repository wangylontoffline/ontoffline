/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.dto.HotSearchDto;
import com.ontoffline.supermall.bean.model.HotSearch;
import com.ontoffline.supermall.dao.HotSearchMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface HotSearchService extends IService<HotSearch> {

    List<HotSearchDto> getHotSearchDtoByshopId(Long shopId);

    void removeHotSearchDtoCacheByshopId(Long shopId);
}
