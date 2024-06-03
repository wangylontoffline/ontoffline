/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ontoffline.supermall.bean.app.dto.ProdCommDataDto;
import com.ontoffline.supermall.bean.app.dto.ProdCommDto;
import com.ontoffline.supermall.bean.model.ProdComm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangylontoffline
 */

public interface ProdCommMapper extends BaseMapper<ProdComm> {
    ProdCommDataDto getProdCommDataByProdId(@Param("prodId") Long prodId);

    IPage<ProdCommDto> getProdCommDtoPageByProdId(@Param("page") Page page, @Param("prodId") Long prodId, @Param("evaluate") Integer evaluate);

    IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page, @Param("userId") String userId);

    IPage<ProdComm> getProdCommPage(Page page, @Param("prodComm") ProdComm prodComm);
}