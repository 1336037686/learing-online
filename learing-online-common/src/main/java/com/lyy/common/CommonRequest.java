package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求报文
 * @author LGX_TvT
 * @date 2019-11-29 0:00
 */
@Data
public class CommonRequest<T> implements Serializable {

    /**
     * 请求头
     */
    private RequestHead head;

    /**
     * 请求体
     */
    private RequestBody<T> body;

    public CommonRequest(RequestHead head, RequestBody<T> body) {
        this.head = head;
        this.body = body;
    }

    public CommonRequest() {
    }
}
