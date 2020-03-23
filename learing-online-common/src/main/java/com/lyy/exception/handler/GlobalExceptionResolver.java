package com.lyy.exception.handler;


import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseHead;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.exception.base.DaoException;
import com.lyy.exception.base.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * 捕获业务异常以及JSR303抛出的参数校验异常，将错误信息统一处理为CommonResponse返回客户端
 * @author LGX_TvT
 * @date 2019-11-29 11:13
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver {


    /**
     * 处理业务异常
     * 服务端发生异常则统一异常处理,捕获BussinessException异常,将异常错误码填写在应答码，将错误信息填写在消息字段
     * @param e
     * @return
     */
    @ExceptionHandler(BussinessException.class)
    @ResponseBody
    public CommonResponse<Object> handlerBusinessException(BussinessException e){
        log.error("业务异常：错误码[{}], 错误信息[{}]", e.getCode(), e.getMessage());
        log.error("错误详情：", e);
        return new CommonResponse<Object>(new ResponseHead(e.getCode(), e.getMessage()), new com.lyy.common.ResponseBody<Object>());
    }

    /**
     * 处理JSR303校验异常
     * JSR303校验器后，校验不通过都会产生一个BindException，输出错误信息
     *
     * 注：错误码以及返回信息暂未确认
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResponse<Object> handlerBindException(BindException e) {
        log.error("校验异常：[{}]", e.getLocalizedMessage());
        log.error("错误详情：", e);
        return new CommonResponse<Object>(new ResponseHead(ErrorCode.SYSTEM_BASIC_DATA_ERROR, e.getMessage()), new com.lyy.common.ResponseBody<Object>());
    }

    /**
     * 处理Service业务层异常
     * 业务层发生异常则统一异常处理,捕获ServiceException异常,将异常错误码填写在应答码，将错误信息填写在消息字段
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public CommonResponse<Object> handlerServiceException(ServiceException e){
        log.error("服务异常：错误码[{}], 错误信息[{}]", e.getCode(), e.getMessage());
        log.error("错误详情：", e);
        return new CommonResponse<Object>(new ResponseHead(e.getCode(), e.getMessage()), new com.lyy.common.ResponseBody<Object>());
    }


    /**
     * 处理Dao层异常
     * Dao层发生异常则统一异常处理，捕获DaoException异常,将异常错误码填写在应答码，将错误信息填写在消息字段
     * @param e
     * @return
     */
    @ExceptionHandler(DaoException.class)
    @ResponseBody
    public CommonResponse<Object> handlerDaoException(DaoException e){
        log.error("Dao异常：错误码[{}], 错误信息[{}]", e.getCode(), e.getMessage());
        log.error("错误详情：", e);
        return new CommonResponse<Object>(new ResponseHead(e.getCode(), e.getMessage()), new com.lyy.common.ResponseBody<Object>());
    }

    /**
     * 任意错误处理异常
     * Exception统一异常处理，捕获Exception异常,将异常错误码填写在应答码，将错误信息填写在消息字段
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse<Object> handlerException(Exception e){
        log.error("系统异常：错误码[{}], 错误信息[{}]", ErrorCode.SYSTEM_EXCEPTION_ERROR, e.getMessage());
        log.error("错误详情：", e);
        return new CommonResponse<Object>(new ResponseHead(ErrorCode.SYSTEM_EXCEPTION_ERROR, e.getMessage()), new com.lyy.common.ResponseBody<Object>());
    }
}
