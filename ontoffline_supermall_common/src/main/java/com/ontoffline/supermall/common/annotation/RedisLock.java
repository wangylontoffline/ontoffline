/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wangylontoffline
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

	/**
	 * redis锁 名字
	 */
	String lockName() default "";

	/**
	 * redis锁 key 支持spel表达式
	 */
	String key() default "";

	/**
	 * 过期秒数,默认为5毫秒
	 *
	 * @return 轮询锁的时间
	 */
	int expire() default 5000;

	/**
	 * 超时时间单位
	 *
	 * @return 秒
	 */
	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
