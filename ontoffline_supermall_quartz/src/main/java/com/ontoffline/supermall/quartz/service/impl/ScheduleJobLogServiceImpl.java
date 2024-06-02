/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.quartz.dao.ScheduleJobLogMapper;
import com.ontoffline.supermall.quartz.model.ScheduleJobLog;
import com.ontoffline.supermall.quartz.service.ScheduleJobLogService;

/**
 * @author wangylontoffline
 */

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;

}
