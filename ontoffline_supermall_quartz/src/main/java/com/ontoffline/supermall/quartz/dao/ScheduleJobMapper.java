/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.quartz.model.ScheduleJob;

import org.apache.ibatis.annotations.Param;

/**
 * @author wangylontoffline
 */

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

	/**
	 *  批量修改任务状态
	 * @param jobIds 调度任务id
	 * @param status 任务状态
	 * @return 修改成功条数
	 */
	int updateBatch(@Param("jobIds") Long[] jobIds, @Param("status") int status);
}