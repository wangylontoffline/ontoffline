/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_category_brand")
public class CategoryBrand implements Serializable {
    @TableId

    private Long id;

    /**
     * 分类id
     */

    private Long categoryId;

    /**
     * 品牌id
     */

    private Long brandId;
}