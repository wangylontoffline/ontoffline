/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ontoffline.supermall.common.bean.Qiniu;
import com.ontoffline.supermall.common.enums.QiniuZone;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.Objects;

/**
 * @author wangylontoffline
 */

@Configuration
public class FileUploadConfig {


	@Autowired
	private Qiniu qiniu;

    /**
     * 根据配置文件选择机房
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        Zone zone = null;
        if (Objects.equals(qiniu.getZone(), QiniuZone.HUA_BEI)) {
            zone = Zone.huabei();
        } else if (Objects.equals(qiniu.getZone(), QiniuZone.HUA_DONG)) {
            zone = Zone.huadong();
        } else if (Objects.equals(qiniu.getZone(), QiniuZone.HUA_NAN)) {
            zone = Zone.huanan();
        } else if (Objects.equals(qiniu.getZone(), QiniuZone.BEI_MEI)) {
            zone = Zone.beimei();
        } else if (Objects.equals(qiniu.getZone(), QiniuZone.XIN_JIA_PO)) {
            zone = Zone.xinjiapo();
        }
        return new com.qiniu.storage.Configuration(zone);
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(qiniu.getAccessKey(), qiniu.getSecretKey());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }
}
