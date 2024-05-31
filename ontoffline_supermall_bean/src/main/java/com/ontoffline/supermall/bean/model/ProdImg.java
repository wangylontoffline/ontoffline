/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_prod_img")
public class ProdImg implements Serializable {
    /**
     * 图片ID
     */
    @TableId

    private Long imgId;

    /**
     * 产品ID
     */

    private Long prodId;

    /**
     * skuID(商品通用图片 skuid为0)
     */

    private Long skuId;

    /**
     * 文件路径
     */

    private String imgPath;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 顺序
     */
    private Integer seq;

}