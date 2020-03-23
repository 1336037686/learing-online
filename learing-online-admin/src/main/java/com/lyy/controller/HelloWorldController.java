package com.lyy.controller;

import com.lyy.log.annotation.ApiILog;
import com.lyy.service.HelloWorldService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LGX_TvT
 * @date 2020-03-23 16:20
 */
@Api
@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @ApiILog
    @RequestMapping("/hello")
    public String hello() throws Exception {
        return helloWorldService.hello();
    }

}
