package com.lyy.log.aspect;


import com.lyy.utils.DateUtil;
import com.lyy.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Date;

/**
 * 控制器，API切面处理类
 * 在执行前日志记录输出请求的内容，包含请求地址，请求方式，请求类方法，请求方法的参数，在执行后输出返回的内容。
 * 请求报文：Ip#业务发生时间#报文信息
 * 应答报文：业务发生时间#耗时s#报文信息
 * @author LGX_TvT
 * @date 2019-11-28 16:09
 */
@Aspect
@Component
public class MyApiLogAspect {

    private final Logger logger = LoggerFactory.getLogger(MyApiLogAspect.class);


    @Pointcut("@annotation(com.lyy.log.annotation.ApiILog)")
    public void apiAspect(){
    }

    @Around("apiAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //业务发生时间
        Date serviceHappenDate = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取调用类信息
        Object target = pjp.getTarget().getClass().getName();
        //获取调用方法信息
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //获取参数类型
        StringBuffer params = new StringBuffer();
        Parameter[] parameters = method.getParameters();
        if(parameters != null && parameters.length > 0) {
            for (Parameter parameter : parameters) {
                params.append("[" + parameter.getType().getName() + "]" + parameter.getName() + " ");
            }
        }

        //返回对象
        Object o = null;
        Object[] args = pjp.getArgs();
        logger.info("===================== 请求内容 =====================");
        logger.info("== 业务发生时间:{}", DateUtil.parseDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
        logger.info("== 请求URL:{}", request.getRequestURL().toString());
        logger.info("== 请求IP:{}", IpUtil.getIPAddress(request));
        logger.info("== 请求类:{}", target);
        logger.info("== 调用方法:{}", method.getName());
        logger.info("== 方法参数:{}", params.toString());
        logger.info("== 传入数据:{}", Arrays.toString(args));
        logger.info("=====================================================");

        //计算执行时间
        long startTime = System.currentTimeMillis();
        long endtime = 0;
        try {
            if (args.length > 0) {
                o = pjp.proceed(args);
            } else {
                o = pjp.proceed();
            }
            endtime = System.currentTimeMillis();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        logger.info("===================== 响应内容 =====================");
        logger.info("== 业务发生时间:{}", DateUtil.parseDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
        logger.info("== 响应耗时:{}", endtime-startTime < 0 ? "" : ((endtime - startTime) / 1000) + "s");
        logger.info("== 返回内容:{}", o == null ? "" : o.toString());
        logger.info("===================================================");
        return o;
    }
}
