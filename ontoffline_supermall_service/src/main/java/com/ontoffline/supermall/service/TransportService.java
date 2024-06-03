/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.model.Transport;

import org.springframework.cache.annotation.CacheEvict;

/**
 * @author wangylontoffline
 */

public interface TransportService extends IService<Transport> {

	void insertTransportAndTransfee(Transport transport);

	void updateTransportAndTransfee(Transport transport);

	void deleteTransportAndTransfeeAndTranscity(Long[] ids);

	Transport getTransportAndAllItems(Long transportId);

	@CacheEvict(cacheNames = "TransportAndAllItems", key = "#transportId")
	default void removeTransportAndAllItemsCache(Long transportId){}

}
