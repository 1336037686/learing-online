package com.lyy.utils.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LGX_TvT
 * @date 2019-12-17 23:14
 */
@Data
@Component
@ConfigurationProperties(prefix = "bossoft.sms")
public class SmsParam {

    /**
     * 超时异常处理
     */
    private String defaultConnectTimeout;

    /**
     * 超时异常处理
     */
    private String defaultReadTimeout;

    /**
     * 短信验证超时时间
     * 可自行调整
     */
    private String timeout;

    /**
     * 阿里云短信推送服务名称
     */
    private String product;

    /**
     * 阿里云短信推送服务API接口
     */
    private String domain;

    /**
     * 阿里云accessKeyID
     * 密匙id
     */
    private String accessKeyId;

    /**
     * 阿里云accessKeySecret
     * 密匙密码
     */
    private String accessKeySecret;

    /**
     * 短信标签
     */
    private String signName;

    /**
     * 短信验证模板号
     */
    private String templateCode;

    /**
     * regionId
     * cn-hangzhou
     */
    private String regionId;

    /**
     * endpointName
     * cn-hangzhou
     */
    private String endpointName;

    /**
     * outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
     */
    private String outId = "yourOutId";

    /**
     * 短信验证码长度
     */
    private Integer smsCodeLength = 4;
}
