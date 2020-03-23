package com.lyy.apidoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * @author LGX_TvT
 * @date 2019-12-17 20:33
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Swagger2 配置
     */
    @Autowired
    private Swagger2Param swagger2Param;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swagger2Param.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title(swagger2Param.getTitle())
                //描述
                .description(swagger2Param.getDescription())
                //（不可见）条款地址
                .termsOfServiceUrl(swagger2Param.getTermsOfServiceUrl())
                //版本号
                .version(swagger2Param.getVersion())
                .build();
    }
}
