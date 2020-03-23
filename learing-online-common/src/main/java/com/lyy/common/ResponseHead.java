package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应消息头
 * @author LGX_TvT
 * @date 2019-11-28 20:41
 */
@Data
public class ResponseHead implements Serializable {

    /**
     * 应答码
     */
    private String responseCode;

    /**
     * 消息
     */
    private String message;

    public ResponseHead(){}

    public ResponseHead(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
