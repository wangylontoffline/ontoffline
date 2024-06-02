/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.config;


import com.ontoffline.supermall.quartz.event.ScheduleJobEvent;
import com.ontoffline.supermall.quartz.model.ScheduleJob;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangylontoffline
 */

@Slf4j
@DisallowConcurrentExecution
public class QuartzJob implements Job {

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * 任务调度参数key
	 */
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	@Override
	@SneakyThrows
	public void execute(JobExecutionContext jobExecutionContext) {
		ScheduleJob sysJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(JOB_PARAM_KEY);
		publisher.publishEvent(new ScheduleJobEvent(sysJob));
	}

}
