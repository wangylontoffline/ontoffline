/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.dto.HotSearchDto;
import com.ontoffline.supermall.bean.model.HotSearch;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface HotSearchMapper extends BaseMapper<HotSearch> {
    List<HotSearchDto> getHotSearchDtoByShopId(Long shopId);
}