/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.quartz.model.ScheduleJob;

/**
 * @author wangylontoffline
 */

public interface ScheduleJobService extends IService<ScheduleJob>{

	/**
	 * 保存并开始定时任务
	 * @param scheduleJob
	 */
	void saveAndStart(ScheduleJob scheduleJob);
	
	/**
	 * 更新定时任务
	 * @param scheduleJob
	 */
	void updateScheduleJob(ScheduleJob scheduleJob);
	
	/**
	 * 批量删除定时任务
	 * @param jobIds 需要删除的job id列表
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 * @param jobIds 需要更新的job id列表
	 * @param status 更新后的状态
	 * @return 更新数量
	 */
	int updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 * @param jobIds job id列表
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 * @param jobIds job id列表
	 */
	void pause(Long[] jobIds);
	
	/**
	 * 恢复运行
	 * @param jobIds job id列表
	 */
	void resume(Long[] jobIds);
	
}
