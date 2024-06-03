/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ontoffline.supermall.bean.app.dto.NoticeDto;
import com.ontoffline.supermall.bean.model.Notice;

/**
 * @author wangylontoffline
 */

public interface NoticeMapper extends BaseMapper<Notice> {

    Page<NoticeDto> pageNotice(Page<NoticeDto> page);
}
