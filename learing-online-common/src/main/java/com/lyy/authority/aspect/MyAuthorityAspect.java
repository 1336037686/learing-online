package com.lyy.authority.aspect;


import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.CommonRequest;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.utils.DateUtil;
import com.lyy.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Token验证切面
 * @author LGX_TvT
 * @date 2019-11-28 16:09
 */
@Aspect
@Order(-1)
@Component
public class MyAuthorityAspect {

    private final Logger logger = LoggerFactory.getLogger(MyAuthorityAspect.class);


    @Pointcut("@annotation(com.lyy.authority.annotation.TokenVerify)")
    public void apiAspect(){
    }

    @Around("apiAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //业务发生时间
        Date serviceHappenDate = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //获取Token验证注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        TokenVerify tokenVerify = method.getAnnotation(TokenVerify.class);

        //返回对象
        Object o = null;
        Object[] args = pjp.getArgs();

        boolean result = false;
        logger.info("===================== Token验证 =====================");
        logger.info("== 业务发生时间:{}", DateUtil.parseDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
        logger.info("== 是否需要Token验证:{}", tokenVerify.required());

        // 是否需要验证
        if(tokenVerify.required()) {
            // 验证 token
            for (Object arg : args) {
                if(arg instanceof CommonRequest) {
                    try {
                        CommonRequest vo = (CommonRequest)arg;
                        String token = vo.getHead().getToken();
                        logger.info("== Token:{}", token);
                        if(token != null) {
                            Map<String, Object> objectMap = TokenUtil.verifyToken(token);
                            Object userId = objectMap.get("userId");
                            Object userName = objectMap.get("userName");
                            if(userId != null && userName != null ){
                                logger.info("== Token内容:{}, {}", userId, userName);
                                result = true;
                            }
                        }
                    }catch (Exception e) {
                        logger.info("== 验证操作:{}", "Token解析出错," + e.getMessage());
                        logger.info("== 验证结果:{}", "验证失败, 该接口需要登陆");
                        logger.info("====================================================");
                        throw new AppException(ErrorCode.SYSTEM_AUTHORIZE_ERROR, "该接口需要登陆");
                    }
                }
            }
        } else {
            logger.info("== Token:{}", "无需Token");
            result = true;
        }
        if(!result) {
            logger.info("== 验证结果:{}", "验证失败, 该接口需要登陆");
            throw new AppException(ErrorCode.SYSTEM_AUTHORIZE_ERROR, "该接口需要登陆");
        } else {
            logger.info("== 验证结果:{}", "验证通过, 该接口可以访问");
        }
        logger.info("====================================================");

        // 执行方法
        try {
            if (args.length > 0) {
                o = pjp.proceed(args);
            } else {
                o = pjp.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        return o;
    }
}
