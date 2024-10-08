/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.enums.SmsType;
import com.ontoffline.supermall.bean.model.SmsLog;
import com.ontoffline.supermall.dao.SmsLogMapper;
import com.ontoffline.supermall.service.SmsLogService;
import com.ontoffline.supermall.common.bean.ALiDaYu;
import com.ontoffline.supermall.common.enums.OntofflineHttpStatus;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.Json;
import com.ontoffline.supermall.common.util.RedisUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wangylontoffline
 */

@Service
@Slf4j
@AllArgsConstructor
public class SmsLogServiceImpl extends ServiceImpl<SmsLogMapper, SmsLog> implements SmsLogService {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SmsLogServiceImpl.class);
	
    private SmsLogMapper smsLogMapper;

    private ALiDaYu aLiDaYu;

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 当天最大验证码短信发送量
     */
    private static final int TODAY_MAX_SEND_VALID_SMS_NUMBER = 10;

    /**
     * 一段时间内短信验证码的最大验证次数
     */
    private static final int TIMES_CHECK_VALID_CODE_NUM = 10;

    /**
     * 短信验证码的前缀
     */
    private static final String CHECK_VALID_CODE_NUM_PREFIX = "checkValidCodeNum_";

    /**
     * 短信发送成功的标志
     */
    private static final String SEND_SMS_SUCCESS_FLAG = "OK";

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void sendSms(SmsType smsType, String userId, String mobile, Map<String, String> params) {

        SmsLog smsLog = new SmsLog();
        if (smsType.equals(SmsType.VALID)) {
            int todaySendSmsNumber = smsLogMapper.selectCount(new LambdaQueryWrapper<SmsLog>()
                    .gt(SmsLog::getRecDate, DateUtil.beginOfDay(new Date()))
                    .lt(SmsLog::getRecDate, DateUtil.endOfDay(new Date()))
                    .eq(SmsLog::getUserId, userId)
                    .eq(SmsLog::getType, smsType.value()));
            if (todaySendSmsNumber >= TODAY_MAX_SEND_VALID_SMS_NUMBER) {
                throw new OntofflineSupermallBindException("今日发送短信验证码次数已达到上限");
            }

            // 将上一条验证码失效
            smsLogMapper.invalidSmsByMobileAndType(mobile, smsType.value());

            String code = RandomUtil.randomNumbers(6);
            params.put("code", code);
        }
        smsLog.setType(smsType.value());
        smsLog.setMobileCode(params.get("code"));
        smsLog.setRecDate(new Date());
        smsLog.setStatus(1);
        smsLog.setUserId(userId);
        smsLog.setUserPhone(mobile);
        smsLog.setContent(formatContent(smsType, params));
        smsLogMapper.insert(smsLog);
        try {
            this.sendSms(mobile, smsType.getTemplateCode(), params);
        } catch (ClientException e) {
            throw new OntofflineSupermallBindException("发送短信失败，请稍后再试");
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean checkValidCode(String mobile, String code, SmsType smsType) {
        long checkValidCodeNum = RedisUtil.incr(CHECK_VALID_CODE_NUM_PREFIX + mobile, 1);
        if (checkValidCodeNum == 0) {
            // 半小时后失效
            RedisUtil.expire(CHECK_VALID_CODE_NUM_PREFIX, 1800);
        }
        if (checkValidCodeNum >= TIMES_CHECK_VALID_CODE_NUM) {
            throw new OntofflineSupermallBindException("验证码校验过频繁，请稍后再试");
        }
        SmsLog sms = new SmsLog();
        sms.setUserPhone(mobile);
        sms.setMobileCode(code);
        sms.setStatus(1);
        sms.setType(smsType.value());

        SmsLog dbSms = smsLogMapper.selectOne(new LambdaQueryWrapper<SmsLog>()
                .eq(SmsLog::getUserPhone, mobile)
                .eq(SmsLog::getMobileCode, code)
                .eq(SmsLog::getStatus, 1)
                .eq(SmsLog::getType, smsType.value()));
        // 没有找到当前的验证码
        if (dbSms == null) {
            RedisUtil.incr(CHECK_VALID_CODE_NUM_PREFIX + mobile, 1);
            return false;
        }
        RedisUtil.del(CHECK_VALID_CODE_NUM_PREFIX + mobile);
        // 标记为失效状态
        dbSms.setStatus(0);
        smsLogMapper.updateById(dbSms);
        // 验证码已过期
        DateTime offsetMinute = DateUtil.offsetMinute(dbSms.getRecDate(), 5);
        if (offsetMinute.getTime() < System.currentTimeMillis()) {
            RedisUtil.incr(CHECK_VALID_CODE_NUM_PREFIX + mobile, 1);
            return false;
        }

        return true;
    }

    private void sendSms(String mobile, String templateCode, Map<String, String> params) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aLiDaYu.getAccessKeyId(), aLiDaYu.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(aLiDaYu.getSignName());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        request.setTemplateParam(Json.toJsonString(params));


        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        logger.debug(Json.toJsonString(sendSmsResponse));
        if (sendSmsResponse.getCode() == null || !SEND_SMS_SUCCESS_FLAG.equals(sendSmsResponse.getCode())) {
            throw new OntofflineSupermallBindException("发送短信失败，请稍后再试:" + sendSmsResponse.getMessage());
        }
    }

    private String formatContent(SmsType smsType, Map<String, String> params) {
        if (CollectionUtil.isEmpty(params)) {
            return smsType.getContent();
        }
        String content = smsType.getContent();
        for (Entry<String, String> element : params.entrySet()) {
            content = content.replace("${" + element.getKey() + "}", element.getValue());
        }
        return content;
    }
}
