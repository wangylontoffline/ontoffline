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
import com.ontoffline.supermall.bean.model.Transfee;
import com.ontoffline.supermall.bean.model.TransfeeFree;

/**
 * @author wangylontoffline
 */

public interface TransfeeMapper extends BaseMapper<Transfee> {

	void insertTransfees(List<Transfee> transfees);

	void insertTransfeeFrees(List<TransfeeFree> transfeeFrees);

	void deleteByTransportId(@Param("transportId") Long transportId);

	void deleteTransfeeFreesByTransportId(@Param("transportId") Long transportId);


}