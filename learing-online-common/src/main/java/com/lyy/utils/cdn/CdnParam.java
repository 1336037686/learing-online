package com.lyy.utils.cdn;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Cdn配置参数类
 * 用于定义一些CDN基础信息
 * @author LGX_TvT
 * @date 2019-12-17 22:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "bossoft.cdn")
public class CdnParam {

    /**
     * 阿里云API的内或外网域名
     */
    private String endPoint;

    /**
     * 阿里云API的密钥Access Key ID
     */
    private String accessKeyId;

    /**
     * 阿里云API的密钥Access Key Secret
     */
    private String accessKeySelect;

    /**
     * 阿里云API的bucket名称
     */
    private String bucketName;

    /**
     * /阿里云API的图片文件夹名称
     */
    private String imageFolder;

    /**
     * /阿里云API的视频文件夹名称
     */
    private String videoFolder;

    /**
     * /阿里云API的资源文件夹名称
     */
    private String resourceFolder;

}
