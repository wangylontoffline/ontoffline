/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.enums.SmsType;
import com.ontoffline.supermall.bean.model.SmsLog;

/**
 * @author wangylontoffline
 */

public interface SmsLogService extends IService<SmsLog> {

	public void sendSms(SmsType smsType,String userId,String mobile,Map<String,String> params);
	
	public boolean checkValidCode(String mobile, String code,SmsType smsType);
}
