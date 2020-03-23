package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应报文
 * @author LGX_TvT
 * @date 2019-11-29 0:13
 */
@Data
public class CommonResponse<T> implements Serializable {

    /**
     * 响应头
     */
    private ResponseHead head;

    /**
     * 响应体
     */
    private ResponseBody<T> body;

    public CommonResponse() {
    }

    public CommonResponse(ResponseHead head, ResponseBody<T> body) {
        this.head = head;
        this.body = body;
    }
}
