package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.vo.LoginQueryVO;
import com.lyy.pojo.vo.LoginResponseVO;
import com.lyy.service.TeacherLoginService;
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
 * 教师登陆控制层
 * @author LGX_TvT
 * @date 2020-04-10 16:15
 */
@Api(tags = "教师登陆Api (V1版本)")
@RequestMapping("/v1/teacher")
@RestController
public class TeacherLoginController {

    @Autowired
    private TeacherLoginService teacherLoginService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 教师登陆
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "教师登陆", notes = "需要账号，密码.")
    @ApiILog
    @PostMapping("/login")
    public CommonResponse<LoginResponseVO> doLogin(@RequestBody CommonRequest<LoginQueryVO> vo) throws AppException {
        LoginResponseVO loginResponseVO = null;
        try {
            TeacherDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);
            TeacherDTO login = teacherLoginService.login(dto);
            if(login == null) {
                return new CommonResponse<LoginResponseVO>(new ResponseHead(StateCode.FAIL_CODE, "登陆失败"), new ResponseBody<LoginResponseVO>(null));
            }
            //生成token
            String token = TokenUtil.getToken(login.getId(), login.getUserName());
            loginResponseVO = new LoginResponseVO(login.getId(), login.getName(), token);
        } catch (BussinessException b){
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_AUTHORIZE_ERROR, "登陆失败");
        }
        return new CommonResponse<LoginResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "登陆成功"), new ResponseBody<LoginResponseVO>(loginResponseVO));
    }


}
