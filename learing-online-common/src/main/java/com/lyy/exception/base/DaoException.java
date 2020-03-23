package com.lyy.exception.base;

/**
 * 数据访问相关的基本异常
 * @author LGX_TvT
 * @date 2019-11-29 10:39
 */
public class DaoException extends AppException {

    public DaoException() {
    }

    public DaoException(String code, String message) {
        super(code, message);
    }

    public DaoException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
