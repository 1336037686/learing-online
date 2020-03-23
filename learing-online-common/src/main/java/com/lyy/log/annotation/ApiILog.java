package com.lyy.log.annotation;

import java.lang.annotation.*;

/**
 * API日志注解
 * 自定义注解,使用AOP对相关API进行日志记录
 * @author LGX_TvT
 * @date 2019-11-28 16:03
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiILog {
    String value() default "";
}