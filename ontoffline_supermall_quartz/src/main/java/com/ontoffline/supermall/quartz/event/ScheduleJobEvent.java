/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.event;


import com.ontoffline.supermall.quartz.model.ScheduleJob;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangylontoffline
 */

@Getter
public class ScheduleJobEvent {

	private ScheduleJob scheduleJob;

	public ScheduleJobEvent(ScheduleJob scheduleJob) {
		super();
		this.scheduleJob = scheduleJob;
	}

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}
	
}
