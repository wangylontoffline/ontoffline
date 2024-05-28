/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContext;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangylontoffline
 */

@Profile("prod")
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//	 @Bean
//	 public Docket baseRestApi() {
//	     return new Docket(DocumentationType.SWAGGER_2)
//	     .apiInfo(apiInfo())
//		 .groupName("基础版")
//	     .select()
//	     .apis(RequestHandlerSelectors.basePackage("com.yami.shop.api"))
//	     .paths(PathSelectors.any())
//	     .build();
//	 }
//
	 @Bean
	 public ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	     .title("上下网接口文档")
	     .description("上下网商城接口文档Swagger版")
//	     .termsOfServiceUrl("http://www.ontoffline.com")
//	     .contact(new Contact("南京奥托兰科技有限公司","https://www.ontoffline.com/", ""))
	     .termsOfServiceUrl("https://www.ontoffline.com")
	     .contact(new Contact("南京奥托兰科技有限公司","https://www.ontoffline.com", ""))
	     .version("1.0")
	     .build();
	 }
	 
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.ontoffline.supermall.api"))
	            .paths(PathSelectors.any())
	            .build();
//	            .securitySchemes(Arrays.asList(apiKey()))
//	            .securityContexts(Arrays.asList(securityContext()));
	    }
//	    private ApiKey apiKey() {
//	        return new ApiKey("apiKey", "api_key", "header");
//	    }
//	    private SecurityContext securityContext() {
//	        return SecurityContext.builder()
//	            .securityReferences(defaultAuth())
//	            .forPaths(PathSelectors.any())
//	            .build();
//	    }
//	    List<SecurityReference> defaultAuth() {
//	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//	        authorizationScopes[0] = authorizationScope;
//	        return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
//	    }
}