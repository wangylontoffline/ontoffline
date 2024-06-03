/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.Delivery;
import com.ontoffline.supermall.dao.DeliveryMapper;
import com.ontoffline.supermall.service.DeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

}
