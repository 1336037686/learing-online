package com.lyy.exception.base;

/**
 * 基本业务操作异常
 * @author LGX_TvT
 * @date 2019-11-29 10:36
 */
public class BussinessException extends AppException {


    public BussinessException() {
    }

    public BussinessException(String code, String message) {
        super(code, message);
    }

    public BussinessException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

}
