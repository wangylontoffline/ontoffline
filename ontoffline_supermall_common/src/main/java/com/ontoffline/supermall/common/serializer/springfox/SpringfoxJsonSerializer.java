/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.serializer.springfox;


import io.swagger.models.Operation;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;

import java.util.List;

/**
 * @author wangylontoffline
 */

public class SpringfoxJsonSerializer extends JsonSerializer {

    public SpringfoxJsonSerializer(List<JacksonModuleRegistrar> modules) {
        super(modules);
    }

    @Override
    public Json toJson(Object toSerialize) {
        if (!(toSerialize instanceof Swagger)) {
            return super.toJson(toSerialize);
        }
        Swagger swagger = (Swagger)toSerialize;

        swagger.getPaths().forEach((key, path) ->{
            Operation get = path.getGet();
            if (get != null) {

                List<Parameter> parameters = get.getParameters();
                if (parameters != null) {
                    parameters.removeIf(parameter -> parameter.getName().startsWith("records[0]."));
                }
            }
        });

        return super.toJson(swagger);
//        return super.toJson(toSerialize);
    }
}
