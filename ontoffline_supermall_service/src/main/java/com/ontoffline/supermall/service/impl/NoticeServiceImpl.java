/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.app.dto.NoticeDto;
import com.ontoffline.supermall.bean.model.Notice;
import com.ontoffline.supermall.dao.NoticeMapper;
import com.ontoffline.supermall.service.NoticeService;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    private NoticeMapper noticeMapper;

    @Override
    @Cacheable(cacheNames = "notices", key = "'notices'")
    public List<Notice> listNotice() {
        return noticeMapper.selectList(new LambdaQueryWrapper<Notice>()
                .eq(Notice::getStatus, 1)
                .eq(Notice::getIsTop, 1)
                .orderByDesc(Notice::getPublishTime));
    }

    @Override
    @CacheEvict(cacheNames = "notices", key = "'notices'")
    public void removeNoticeList() {
    }

    @Override
    public Page<NoticeDto> pageNotice(Page<NoticeDto> page) {
        return noticeMapper.pageNotice(page);
    }

    @Override
    @Cacheable(cacheNames = "notice", key = "#noticeId")
    public Notice getNoticeById(Long noticeId) {
        return noticeMapper.selectById(noticeId);
    }

    @Override
    @CacheEvict(cacheNames = "notice", key = "#noticeId")
    public void removeNoticeById(Long noticeId) {
    }


}
