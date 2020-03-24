package com.lyy.controller;

import com.lyy.common.CommonResponse;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.vo.LoginQueryVO;
import com.lyy.pojo.vo.LoginResponseVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制层
 * @author LGX_TvT
 * @date 2020-03-23 21:54
 */
@RequestMapping("/v1/admin")
@RestController
public class AdministratorController {

    /**
     * 管理员用户登陆
     * @param vo
     * @return
     * @throws Exception
     */
    @ApiILog
    @PostMapping("/login")
    public CommonResponse<LoginResponseVO> doLogin(@RequestBody LoginQueryVO vo) throws Exception{
        return null;
    }

}
