package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.vo.TeacherQueryVO;
import com.lyy.pojo.vo.TeacherResponseVO;
import com.lyy.service.TeacherService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author LGX_TvT
 * @date 2020-04-10 16:09
 */
@Api(tags = "教师管理Api (V1版本)")
@RequestMapping("/v1/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 按照ID查找教师信息
     * @param id
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照ID查找教师信息", notes = "需要教师ID.")
    @ApiILog
    @GetMapping("/query/{id}")
    public CommonResponse<TeacherResponseVO> doQueryById(@PathVariable("id") String id) throws AppException{
        TeacherResponseVO teacherResponseVO = null;
        try {
            TeacherDTO dto = teacherService.queryById(id);
            teacherResponseVO = converterUtil.copyPropertiesAndReturnNewOne(dto, TeacherResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_TEACHER_QUERY_FAIL_ERROR, "教师信息查找失败");
        }
        return new CommonResponse<TeacherResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "教师信息查找成功"), new ResponseBody<TeacherResponseVO>(teacherResponseVO));
    }


    /**
     * 修改密码
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改教师密码", notes = "需要教师ID, 教师新密码.")
    @ApiILog
    @PutMapping("/update/password")
    public CommonResponse<String> doUpdatePassword(@RequestBody CommonRequest<TeacherQueryVO> vo) {
        try {
            TeacherDTO teacherDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);
            teacherDTO.setPassword(Md5Util.md5(vo.getBody().getData().getPassword()));
            boolean result = teacherService.updatePassword(teacherDTO);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_TEACHER_UPDATE_FAIL_ERROR, "密码修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "密码修改成功"), new ResponseBody<>("密码修改成功"));
    }
}
