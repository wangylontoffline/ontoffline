/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.app.dto.NoticeDto;
import com.ontoffline.supermall.bean.model.Notice;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface NoticeService extends IService<Notice> {

    List<Notice> listNotice();

    void removeNoticeList();

    Page<NoticeDto> pageNotice(Page<NoticeDto> page);

    Notice getNoticeById(Long noticeId);

    void removeNoticeById(Long noticeId);
}
