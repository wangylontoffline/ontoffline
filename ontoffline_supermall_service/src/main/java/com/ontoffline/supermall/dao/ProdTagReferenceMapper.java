/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.ProdTag;
import com.ontoffline.supermall.bean.model.ProdTagReference;
import com.ontoffline.supermall.bean.model.Sku;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface ProdTagReferenceMapper extends BaseMapper<ProdTagReference> {
    void insertBatch(@Param("shopId") Long shopId, @Param("prodId") Long prodId, @Param("tagList") List<Long> tagList);

    List<Long> listTagIdByProdId(@Param("prodId") Long prodId);

}
