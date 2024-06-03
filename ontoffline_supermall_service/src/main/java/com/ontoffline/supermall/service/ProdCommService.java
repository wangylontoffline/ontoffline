/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.app.dto.ProdCommDataDto;
import com.ontoffline.supermall.bean.app.dto.ProdCommDto;
import com.ontoffline.supermall.bean.model.ProdComm;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface ProdCommService extends IService<ProdComm> {
    ProdCommDataDto getProdCommDataByProdId(Long prodId);

    IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page,String userId);

    IPage<ProdCommDto> getProdCommDtoPageByProdId(Page page, Long prodId, Integer evaluate);

    IPage<ProdComm> getProdCommPage(Page page,ProdComm prodComm);

}
