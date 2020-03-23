package com.lyy.exception.base;

/**
 * 中间服务层异常
 * @author LGX_TvT
 * @date 2019-11-29 10:41
 */
public class ServiceException extends AppException {
    public ServiceException() {
    }

    public ServiceException(String code, String message) {
        super(code, message);
    }

    public ServiceException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
