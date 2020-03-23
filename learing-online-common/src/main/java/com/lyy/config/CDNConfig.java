package com.lyy.config;



import com.aliyun.oss.OSSClient;
import com.lyy.utils.cdn.CdnParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LGX_TvT
 * @date 2020-03-23 17:49
 */
@Configuration
public class CDNConfig {

    @Autowired
    CdnParam cdnParam;
    /**
     * OSSClient ossClient = new OSSClient(endPoint,accessKeyId, accessKeySecret)
     * @return
     */
    @Bean
    public OSSClient ossClient(){
        return new OSSClient(cdnParam.getEndPoint(), cdnParam.getAccessKeyId(), cdnParam.getAccessKeySelect());
    }
}
