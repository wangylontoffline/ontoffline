/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.quartz.config.ScheduleManager;
import com.ontoffline.supermall.quartz.dao.ScheduleJobMapper;
import com.ontoffline.supermall.quartz.enums.ScheduleStatus;
import com.ontoffline.supermall.quartz.model.ScheduleJob;
import com.ontoffline.supermall.quartz.service.ScheduleJobService;

import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	@Autowired
	private ScheduleManager scheduleManager;
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		list().forEach(scheduleJob -> {
			CronTrigger trigger = scheduleManager.getCronTrigger(scheduleJob);
			// 如果定时任务不存在，则创建定时任务
			if (trigger == null) {
				scheduleManager.createScheduleJob(scheduleJob);
			} else if (ScheduleStatus.NORMAL.getType().equals(scheduleJob.getStatus())) {
				scheduleManager.resumeJob(scheduleJob);
			} else if (ScheduleStatus.PAUSE.getType().equals(scheduleJob.getStatus())) {
				scheduleManager.pauseJob(scheduleJob);
			}
		});
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAndStart(ScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getType());
		scheduleJobMapper.insert(scheduleJob);
        
        scheduleManager.createScheduleJob(scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateScheduleJob(ScheduleJob scheduleJob) {
		scheduleManager.updateScheduleJob(scheduleJob);
		scheduleJobMapper.updateById(scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] jobIds) {

		List<Long> ids = Arrays.asList(jobIds);
		this.listByIds(ids).forEach(scheduleJob -> {
			scheduleManager.deleteScheduleJob(scheduleJob);
		});
		scheduleJobMapper.deleteBatchIds(ids);
	}

	@Override
	public int updateBatch(Long[] jobIds, int status) {
		return scheduleJobMapper.updateBatch(jobIds,status);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		scheduleManager.run(scheduleJobMapper.selectById(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
		this.listByIds(Arrays.asList(jobIds)).forEach(scheduleJob -> {
			scheduleManager.pauseJob(scheduleJob);
		});
        
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getType());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
		this.listByIds(Arrays.asList(jobIds)).forEach(scheduleJob -> {
			scheduleManager.resumeJob(scheduleJob);
		});

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getType());
    }
}
