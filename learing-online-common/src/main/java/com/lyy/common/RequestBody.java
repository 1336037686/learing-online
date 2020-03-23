package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求体
 * @author LGX_TvT
 * @date 2019-11-28 20:41
 */
@Data
public class RequestBody<T> implements Serializable {

    /**
     * 业务数据
     */
    private T data;

    public RequestBody() {
    }

    public RequestBody(T data) {
        this.data = data;
    }
}
