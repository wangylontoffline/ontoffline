/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.listener;


import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.StrUtil;

import com.ontoffline.supermall.quartz.event.ScheduleJobEvent;
import com.ontoffline.supermall.quartz.model.ScheduleJob;
import com.ontoffline.supermall.quartz.model.ScheduleJobLog;
import com.ontoffline.supermall.quartz.service.ScheduleJobLogService;
import com.ontoffline.supermall.quartz.util.SpringBeanTaskUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangylontoffline
 */

@Slf4j
@Component
@EnableAsync
@AllArgsConstructor
public class SysJobListener {
	
	private final Logger logger = LoggerFactory.getLogger(SysJobListener.class);

	private ScheduleJobLogService scheduleJobLogService;

	@Async
	@EventListener(ScheduleJobEvent.class)
	public void scheduleJobEventListener(ScheduleJobEvent event) {
		ScheduleJob scheduleJob = event.getScheduleJob();
		//数据库保存执行记录
		ScheduleJobLog jobLog = new ScheduleJobLog();
		jobLog.setJobId(scheduleJob.getJobId());
		jobLog.setBeanName(scheduleJob.getBeanName());
		jobLog.setMethodName(scheduleJob.getMethodName());
		jobLog.setParams(scheduleJob.getParams());
		jobLog.setCreateTime(new Date());

		//任务开始时间
		long startTime = SystemClock.now();

		try {
			//执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());

			SpringBeanTaskUtil.invokeMethod(scheduleJob);

			//任务执行总时长
			long times = SystemClock.now() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setStatus(1);
			StringBuilder sb = new StringBuilder();

			logger.info(sb.append("任务执行完毕，任务ID：").append(jobLog.getJobId()).append("  总共耗时：").append(times).append("毫秒").toString());
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

			//任务执行总时长
			long times = SystemClock.now() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setStatus(0);
			jobLog.setError(StrUtil.sub(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(jobLog);
		}
	}
}
