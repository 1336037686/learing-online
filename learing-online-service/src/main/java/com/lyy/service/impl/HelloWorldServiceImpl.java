package com.lyy.service.impl;

import com.lyy.dao.HelloWorldDao;
import com.lyy.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-03-23 16:22
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Autowired
    HelloWorldDao helloWorldDao;

    @Override
    public String hello() {
        return helloWorldDao.hello().toString();
    }
}
