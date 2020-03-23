package com.lyy.apidoc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger2 API文档基础配置
 * @author LGX_TvT
 * @date 2019-12-17 22:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "bossoft.swagger2")
public class Swagger2Param {
    /**
     * 扫描包路径
     */
    private String basePackage;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * （不可见）条款地址
     */
    private String termsOfServiceUrl;

    /**
     * 版本号
     */
    private String version;
}
