/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.User;
import com.ontoffline.supermall.bean.vo.UserVO;
import com.ontoffline.supermall.dao.UserMapper;
import com.ontoffline.supermall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author wangylontoffline
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Cacheable(cacheNames="user",key="#userId")
    public User getUserByUserId(String userId) {
        return userMapper.selectById(userId);
    }
}
