/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.aspect;


import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.common.util.IPHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ontoffline.supermall.sys.service.SysLogService;
import com.ontoffline.supermall.sys.model.SysLog;
import com.ontoffline.supermall.common.util.Json;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.StrUtil;

/**
 * @author wangylontoffline
 */

@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	private static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	@Around("@annotation(sysLog)")
	public Object around(ProceedingJoinPoint joinPoint,com.ontoffline.supermall.common.annotation.SysLog sysLog) throws Throwable {
		long beginTime = SystemClock.now();
		//执行方法
		Object result = joinPoint.proceed();
		//执行时长(毫秒)
		long time = SystemClock.now() - beginTime;


		SysLog sysLogEntity = new SysLog();
		if(sysLog != null){
			//注解上的描述
			sysLogEntity.setOperation(sysLog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		sysLogEntity.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		String params = Json.toJsonString(args[0]);
		sysLogEntity.setParams(params);

		//设置IP地址
		sysLogEntity.setIp(IPHelper.getIpAddr());

		//用户名
		String username = SecurityUtils.getSysUser().getUsername();
		sysLogEntity.setUsername(username);

		sysLogEntity.setTime(time);
		sysLogEntity.setCreateDate(new Date());
		//保存系统日志
		sysLogService.save(sysLogEntity);


		return result;
	}

}
