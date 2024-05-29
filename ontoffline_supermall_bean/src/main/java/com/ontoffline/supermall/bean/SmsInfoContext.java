/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean;


import java.util.ArrayList;
import java.util.List;

import com.ontoffline.supermall.bean.bo.SmsInfoBo;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @author wangylontoffline
 */

public class SmsInfoContext {

	/** The request holder. */
	private static ThreadLocal<List<SmsInfoBo>> smsInfoHolder = new ThreadLocal<List<SmsInfoBo>>();


	public static List<SmsInfoBo> get(){
		List<SmsInfoBo> list = smsInfoHolder.get();
		if (CollectionUtil.isEmpty(list)) {
			return new ArrayList<>();
		}
		return smsInfoHolder.get();
	}
	
	public static void set(List<SmsInfoBo> smsInfoBos){
		 smsInfoHolder.set(smsInfoBos);
	}
	
	public static void put(SmsInfoBo smsInfoBo){
		List<SmsInfoBo> smsInfoBos = smsInfoHolder.get();
		if (CollectionUtil.isEmpty(smsInfoBos)) {
			smsInfoBos = new ArrayList<>();
		}
		smsInfoBos.add(smsInfoBo);
		smsInfoHolder.set(smsInfoBos);
	}
	
	public static void clean() {
		if (smsInfoHolder.get() != null) {
			smsInfoHolder.remove();
		}
	}
}