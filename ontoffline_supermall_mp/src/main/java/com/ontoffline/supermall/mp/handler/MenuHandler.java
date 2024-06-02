/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.handler;


import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;

/**
 * @author wangylontoffline
 */

@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        String msg = String.format("type:%s, event:%s, key:%s",
            wxMessage.getMsgType(), wxMessage.getEvent(),
            wxMessage.getEventKey());
        if (MenuButtonType.VIEW.equals(wxMessage.getEvent())) {
            return null;
        }

        return WxMpXmlOutMessage.TEXT().content(msg)
            .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
            .build();
    }

}
