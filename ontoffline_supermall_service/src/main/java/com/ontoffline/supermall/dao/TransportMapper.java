/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.TransfeeFree;
import com.ontoffline.supermall.bean.model.Transport;

/**
 * @author wangylontoffline
 */

public interface TransportMapper extends BaseMapper<Transport> {

	Transport getTransportAndTransfeeAndTranscity(@Param("id") Long id);

	void deleteTransports(@Param("ids") Long[] ids);

	List<TransfeeFree> getTransfeeFreeAndTranscityFreeByTransportId(@Param("transportId") Long transportId);

}