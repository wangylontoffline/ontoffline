/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.util;


import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangylontoffline
 */

@Component
public class IdUtil {

    @Autowired
    private Snowflake snowflake;

    private static final String DICT = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

    private static final int SEED = DICT.length();

    private static final int ID_MIN_LENGTH = 6;
    /**
     * 数字->字符映射
     */
    private static final char[] CHARS = DICT.toCharArray();
    /**
     * 字符->数字映射
     */
    private static final Map<Character, Integer> NUMBERS = new HashMap<>();

    static {
        int len = CHARS.length;
        for (int i = 0; i<len; i++) {
            NUMBERS.put(CHARS[i], i);
        }
    }

    /**
     * 根据从数据库中返回的记录ID生成对应的短网址编码
     * @param id (1-56.8billion)
     * @return
     */
    public static String encode(long id) {
        StringBuilder shortURL = new StringBuilder();
        while (id > 0) {
            int r = (int) (id % SEED);
            shortURL.insert(0, CHARS[r]);
            id = id / SEED;
        }
        int len = shortURL.length();
        while (len < ID_MIN_LENGTH) {
            shortURL.insert(0, CHARS[0]);
            len++;
        }
        return shortURL.toString();
    }

    /**
     * 根据获得的短网址编码解析出数据库中对应的记录ID
     * @param key 短网址 eg. RwTji8, GijT7Y等等
     * @return
     */
    public static long decode(String key) {
        char[] shorts = key.toCharArray();
        int len = shorts.length;
        long id = 0L;
        for (int i = 0; i < len; i++) {
            id = id + (long) (NUMBERS.get(shorts[i]) * Math.pow(SEED, len-i-1));
        }
        return id;
    }


    public String nextShortId() {
        return encode(snowflake.nextId());
    }
}
