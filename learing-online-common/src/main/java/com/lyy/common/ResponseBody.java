package com.lyy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应消息体
 * @author LGX_TvT
 * @date 2019-11-28 20:41
 */
@Data
public class ResponseBody<T> implements Serializable {
    /**
     * VO数据
     */
    private T data;

    public ResponseBody() {
    }

    public ResponseBody(T data) {
        this.data = data;
    }
}
