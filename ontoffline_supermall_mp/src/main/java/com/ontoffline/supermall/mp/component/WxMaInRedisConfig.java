/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.component;


import java.util.concurrent.locks.Lock;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ontoffline.supermall.common.annotation.RedisLock;
import com.ontoffline.supermall.mp.config.bean.WxMiniApp;
import com.ontoffline.supermall.common.util.RedisUtil;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import me.chanjar.weixin.common.bean.WxAccessToken;

/**
 * @author wangylontoffline
 */

@Component
public class WxMaInRedisConfig extends WxMaDefaultConfigImpl {

    private static final String ACCESS_TOKEN_KEY = "wxMa:access_token:";

    private static final String JSAPI_TICKET_KEY = "wxMa:jsapi_ticket:";

    private static final String CARD_API_TICKET_KEY = "wxMa:card_api_ticket:";

    private static final String WX_MA_ACCESS_TOKEN_LOCK = "wxMa:access_token_lock:";

    private static final String WX_MA_JSAPI_TICKET_LOCK = "wxMa:jsapi_ticket_lock:";

    private static final String WX_MA_CARD_API_TICKET_LOCK = "wxMa:card_api_ticket_lock:";

    private String accessTokenKey;

    private String jsapiTicketKey;

    private String cardApiTicketKey;

    @Autowired
    private RedissonClient redissonClient;

    public WxMaInRedisConfig (WxMiniApp wxMiniApp) {
        this.setAppid(wxMiniApp.getAppid());
        this.setSecret(wxMiniApp.getSecret());
    }

    /**
     * 每个公众号生成独有的存储key.
     */
    @Override
    public void setAppid(String appId) {
        super.setAppid(appId);
        this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
        this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(appId);
        this.cardApiTicketKey = CARD_API_TICKET_KEY.concat(appId);
    }

    @Override
    public String getAccessToken() {
        return RedisUtil.get(accessTokenKey);
    }

    @Override
    public Lock getAccessTokenLock(){
        return redissonClient.getLock(WX_MA_ACCESS_TOKEN_LOCK);
    }

    @Override
    public boolean isAccessTokenExpired() {
        return !RedisUtil.hasKey(accessTokenKey);
    }

    @Override
    public void expireAccessToken() {
        RedisUtil.del(accessTokenKey);
    }

    @Override
    @RedisLock(lockName = "updateMaAccessToken")
    public void updateAccessToken(WxAccessToken accessToken) {
        updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }


    @Override
    @RedisLock(lockName = "updateMaAccessToken")
    public void updateAccessToken(String accessToken, int expiresInSeconds) {
        RedisUtil.set(accessTokenKey, accessToken, expiresInSeconds - 200);
    }

    @Override
    public String getJsapiTicket() {
        return RedisUtil.get(jsapiTicketKey);
    }

    @Override
    public Lock getJsapiTicketLock() {
        return redissonClient.getLock(WX_MA_JSAPI_TICKET_LOCK);
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return !RedisUtil.hasKey(jsapiTicketKey);
    }

    @Override
    public void expireJsapiTicket() {
        RedisUtil.del(jsapiTicketKey);
    }

    @Override
    @RedisLock(lockName = "updateMaJsapiTicket")
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        RedisUtil.set(jsapiTicketKey, jsapiTicket, expiresInSeconds - 200);
    }

    @Override
    public String getCardApiTicket() {
        return RedisUtil.get(cardApiTicketKey);
    }

    @Override
    public Lock getCardApiTicketLock() {
        return redissonClient.getLock(WX_MA_CARD_API_TICKET_LOCK);
    }

    @Override
    public boolean isCardApiTicketExpired() {
        return !RedisUtil.hasKey(cardApiTicketKey);
    }

    @Override
    public void expireCardApiTicket() {
        RedisUtil.del(cardApiTicketKey);
    }

    @Override
    @RedisLock(lockName = "updateMaCardJsapiTicket")
    public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
        RedisUtil.set(cardApiTicketKey, cardApiTicket, expiresInSeconds - 200);
    }

}
