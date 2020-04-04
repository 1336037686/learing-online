package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.AdministratorDTO;
import com.lyy.pojo.vo.LoginQueryVO;
import com.lyy.pojo.vo.LoginResponseVO;
import com.lyy.service.AdministratorLoginService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制层
 * @author LGX_TvT
 * @date 2020-03-23 21:54
 */
@Api(tags = "管理员登陆Api (V1版本)")
@RequestMapping("/v1/admin")
@RestController
public class AdministratorLoginController {

    @Autowired
    private AdministratorLoginService administratorLoginService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 管理员用户登陆
     * @param vo
     * @return
     * @throws Exception
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "登陆", notes = "需要账号，密码.")
    @ApiILog
    @PostMapping("/login")
    public CommonResponse<LoginResponseVO> doLogin(@RequestBody CommonRequest<LoginQueryVO> vo) throws AppException {
        LoginResponseVO loginResponseVO = null;
        try {
            AdministratorDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AdministratorDTO.class);
            AdministratorDTO login = administratorLoginService.Login(dto);
            if(login == null) {
                return new CommonResponse<LoginResponseVO>(new ResponseHead(StateCode.FAIL_CODE, "登陆失败"), new ResponseBody<LoginResponseVO>(null));
            }
            //生成token
            String token = TokenUtil.getToken(login.getId(), login.getUserName());
            loginResponseVO = new LoginResponseVO(login.getUserName(), token);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SYSTEM_AUTHORIZE_ERROR, "登陆失败");
        }
        return new CommonResponse<LoginResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "登陆成功"), new ResponseBody<LoginResponseVO>(loginResponseVO));
    }

}
