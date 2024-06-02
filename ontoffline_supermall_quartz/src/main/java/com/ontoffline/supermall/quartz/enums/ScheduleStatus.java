/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.quartz.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangylontoffline
 */

@Getter
@AllArgsConstructor
public enum ScheduleStatus {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);

    private ScheduleStatus(int type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	/**
     * 类型
     */
    private final Integer type;
}
