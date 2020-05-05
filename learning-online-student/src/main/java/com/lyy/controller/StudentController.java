package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.StudentDTO;
import com.lyy.pojo.vo.StudentQueryVO;
import com.lyy.pojo.vo.StudentResponseVO;
import com.lyy.service.StudentService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LGX_TvT
 * @date 2020-05-05 17:27
 */
@Api(tags = "学生账号管理Api (V1版本)")
@RequestMapping("/v1/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ConverterUtil converterUtil;

    @TokenVerify(required = false)
    @ApiOperation(value = "根据ID查找学生账号信息", notes = "id必填")
    @ApiILog
    @GetMapping("/query/{id}")
    public CommonResponse<StudentResponseVO> doQueryById(@PathVariable("id") String id) throws AppException {
        try {
            StudentDTO dto = studentService.queryById(id);
            StudentResponseVO studentResponseVO = converterUtil.copyPropertiesAndReturnNewOne(dto, StudentResponseVO.class);
            return new CommonResponse<StudentResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "学生信息查找成功"), new ResponseBody<>(studentResponseVO));
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_STUDENT_QUERY_FAIL_ERROR, "学生信息查找失败");
        }
    }

    /**
     * 更新课程学生账号
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "修改学生账号密码", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update/password")
    public CommonResponse<String> doUpdatePassword(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(vo.getBody().getData().getId());
            studentDTO.setPassword(Md5Util.md5(vo.getBody().getData().getPassword()));
            studentService.update(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_UPDATE_FAIL_ERROR, "学生账号更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号更新成功"), new ResponseBody<String>("学生账号更新成功"));
    }

    /**
     * 更新课程学生账号
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "修改学生账号信息", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(vo.getBody().getData().getId());
            studentDTO.setPhone(vo.getBody().getData().getPhone());
            studentDTO.setEmail(vo.getBody().getData().getEmail());
            studentService.update(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_UPDATE_FAIL_ERROR, "学生账号更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号更新成功"), new ResponseBody<String>("学生账号更新成功"));
    }
}
