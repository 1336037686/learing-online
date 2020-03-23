package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求头，数据结构
 * @author LGX_TvT
 * @date 2019-11-28 20:41
 */
@Data
public class RequestHead implements Serializable {

    /**
     * token
     */
    private String token;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 设备ID
     */
    private String equipId;

    public RequestHead() {
    }

    public RequestHead(String token, String businessType, String equipId) {
        this.token = token;
        this.businessType = businessType;
        this.equipId = equipId;
    }
}
