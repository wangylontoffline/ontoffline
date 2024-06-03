/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.User;
import com.ontoffline.supermall.bean.vo.UserVO;

/**
 * @author wangylontoffline
 */

public interface UserService extends IService<User> {

    User getUserByUserId(String userId);
}
