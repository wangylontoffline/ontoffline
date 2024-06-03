/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.TranscityFree;
import com.ontoffline.supermall.dao.TranscityFreeMapper;
import com.ontoffline.supermall.service.TranscityFreeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class TranscityFreeServiceImpl extends ServiceImpl<TranscityFreeMapper, TranscityFree> implements TranscityFreeService {

    @Autowired
    private TranscityFreeMapper transcityFreeMapper;

}
