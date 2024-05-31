/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_transcity_free")
public class TranscityFree implements Serializable {
    private static final long serialVersionUID = 2579465286635831076L;
    /**
     * 指定条件包邮城市项id
     */
    @TableId

    private Long transcityFreeId;

    /**
     * 指定条件包邮项id
     */

    private Long transfeeFreeId;

    /**
     * 城市id
     */

    private Long freeCityId;

	public Long getTranscityFreeId() {
		return transcityFreeId;
	}

	public void setTranscityFreeId(Long transcityFreeId) {
		this.transcityFreeId = transcityFreeId;
	}

	public Long getTransfeeFreeId() {
		return transfeeFreeId;
	}

	public void setTransfeeFreeId(Long transfeeFreeId) {
		this.transfeeFreeId = transfeeFreeId;
	}

	public Long getFreeCityId() {
		return freeCityId;
	}

	public void setFreeCityId(Long freeCityId) {
		this.freeCityId = freeCityId;
	}
    
    
}
