package com.lyy.exception.base;

import lombok.Data;

/**
 * @author LGX_TvT
 * @date 2019-11-29 10:31
 */
@Data
public class AppException extends RuntimeException {

    /**
     * 错误状态
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public AppException() {
    }

    public AppException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AppException(String code, String message, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = message;
    }
}
