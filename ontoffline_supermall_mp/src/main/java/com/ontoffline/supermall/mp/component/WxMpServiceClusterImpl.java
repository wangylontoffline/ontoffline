/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.component;


import cn.hutool.http.HttpUtil;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * @author wangylontoffline
 */

public class WxMpServiceClusterImpl extends WxMpServiceHttpClientImpl {


    private static final String REDISSON_LOCK_PREFIX = "redisson_lock:";

    private RedissonClient redissonClient;

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        if (!this.getWxMpConfigStorage().isAccessTokenExpired() && !forceRefresh) {
            return this.getWxMpConfigStorage().getAccessToken();
        }

        RLock rLock = redissonClient.getLock(REDISSON_LOCK_PREFIX + ":WxMpServiceCluster:getAccessToken");

        try {
            boolean doingUpdateAccessToken;
            try {
                doingUpdateAccessToken = rLock.tryLock(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                return this.getWxMpConfigStorage().getAccessToken();
            }

            if (!doingUpdateAccessToken) {
                throw new OntofflineSupermallBindException("服务器繁忙，请稍后再试");
            }

            if (!this.getWxMpConfigStorage().isAccessTokenExpired()) {
                return this.getWxMpConfigStorage().getAccessToken();
            }
            String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(this.getWxMpConfigStorage()), this.getWxMpConfigStorage().getAppId(), this.getWxMpConfigStorage().getSecret());
            String resultContent = HttpUtil.get(url);

            WxError error = WxError.fromJson(resultContent, WxType.MP);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            }
            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            this.getWxMpConfigStorage().updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
            return this.getWxMpConfigStorage().getAccessToken();

        } finally {
            rLock.unlock();
        }
    }
}
