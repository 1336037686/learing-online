package com.lyy.utils.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Email配置类
 * 用于定义一些邮件相关的基础信息
 * @author LGX_TvT
 * @date 2019-12-17 23:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "bossoft.email")
public class EmailParam {

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
     * AccountName
     * "postmaste@pkying.top"
     */
    private String accountName;

    /**
     * 邮箱推送标签
     */
    private String tagName;

    /**
     * 邮箱推送发信人昵称
     */
    private String fromAlias;

    /**
     * 邮箱推送回信功能
     */
    private Boolean toAddress;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件正文
     * 具体验证码使用{{code}}占位符替代
     */
    private String emailHtmlBody;

    /**
     * regionId
     * cn-hangzhou
     */
    private String regionId;


    /**
     * 邮件验证码长度
     */
    private Integer emailCodeLength = 4;
}
