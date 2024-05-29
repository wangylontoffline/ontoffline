/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangylontoffline
 */

@Data
public class UserAddrDto implements Serializable {
        @ApiModelProperty(value = "地址id", required = true)
    private Long addrId;

    @ApiModelProperty(value = "收货人", required = true)
    private String receiver;

    @ApiModelProperty(value = "省", required = true)
    private String province;

    @ApiModelProperty(value = "城市", required = true)
    private String city;

    @ApiModelProperty(value = "区", required = true)
    private String area;

    @ApiModelProperty(value = "地址", required = true)
    private String addr;

    @ApiModelProperty(value = "手机", required = true)
    private String mobile;

    @ApiModelProperty(value = "是否默认地址（1:是 0：否） ", required = true)
    private Integer commonAddr;

    /**
     * 省ID
     */
    @ApiModelProperty(value = "省ID", required = true)
    private Long provinceId;

    /**
     * 城市ID
     */
    @ApiModelProperty(value = "城市ID", required = true)
    private Long cityId;

    /**
     * 区域ID
     */
    @ApiModelProperty(value = "区域ID", required = true)
    private Long areaId;
}
