/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.util;


import cn.hutool.core.util.StrUtil;

import com.ontoffline.supermall.quartz.model.ScheduleJob;
import com.ontoffline.supermall.common.util.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author wangylontoffline
 */

@Slf4j
public class SpringBeanTaskUtil {
	
	public static void invokeMethod(ScheduleJob scheduleJob) {
		Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
		try {
			if (StrUtil.isNotEmpty(scheduleJob.getParams())) {
				Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName(), String.class);
				ReflectionUtils.makeAccessible(method);
				method.invoke(target, scheduleJob.getParams());
			} else {
				Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName());
				ReflectionUtils.makeAccessible(method);
				method.invoke(target);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行定时任务失败", e);
		}
	}
}
