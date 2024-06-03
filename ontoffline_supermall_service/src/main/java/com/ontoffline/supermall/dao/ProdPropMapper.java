/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.ProdProp;
import com.ontoffline.supermall.common.util.PageAdapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface ProdPropMapper extends BaseMapper<ProdProp> {

    List<ProdProp> listPropAndValue(@Param("adapter") PageAdapter adapter, @Param("prodProp") ProdProp prodProp);

    long countPropAndValue(@Param("prodProp") ProdProp prodProp);

    int deleteByPropId(@Param("propId") Long propId, @Param("rule") Integer rule, @Param("shopId") Long shopId);

    List<ProdProp> listByCategoryId(Long categoryId);

    ProdProp getProdPropByPropNameAndShopId(@Param("propName") String propName, @Param("shopId") Long shopId, @Param("rule") Integer rule);
}
