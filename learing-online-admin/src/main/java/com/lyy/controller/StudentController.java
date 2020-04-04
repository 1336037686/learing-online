package com.lyy.controller;

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
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学生账号管理 控制层
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "学生账号管理Api (V1版本)")
@RequestMapping("/v1/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存学生账号
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存学生账号", notes = "需要类别名称（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        try {
            StudentDTO studentDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentDTO.class);

            // 添加id
            studentDTO.setId(SnowFlakeUtil.generateId() + "");
            //添加状态
            studentDTO.setState("0");
            // 密码加密
            studentDTO.setPassword(Md5Util.md5(studentDTO.getPassword()));

            boolean result = studentService.save(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_SAVE_FAIL_ERROR, "学生账号保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号添加成功"), new ResponseBody<String>("学生账号添加成功"));
    }

    /**
     * 分页查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页查找学生账号信息", notes = "需要页号，长度范围.")
    @ApiILog
    @PostMapping("/query")
    public CommonResponse<StudentResponseVO> doQueryAll(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        StudentDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentDTO.class);
        StudentResponseVO studentResponseVO = null;
        try {
            StudentDTO studentDTO = studentService.queryAll(dto);
            studentResponseVO = converterUtil.copyPropertiesAndReturnNewOne(studentDTO, StudentResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "学生账号查找失败");
        }
        return new CommonResponse<StudentResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号查找成功"), new ResponseBody<StudentResponseVO>(studentResponseVO));
    }

    /**
     * 更新课程学生账号
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "修改学生账号", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        try {
            StudentDTO studentDTO = converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), StudentDTO.class);

            if(studentDTO.getPassword().length() < 32) {
                studentDTO.setPassword(Md5Util.md5(studentDTO.getPassword()));
            }

            studentService.update(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_UPDATE_FAIL_ERROR, "学生账号更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号更新成功"), new ResponseBody<String>("学生账号更新成功"));
    }

    /**
     * 保存课程学生账号
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "删除学生账号", notes = "id（必填）")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<StudentQueryVO> vo) throws AppException {
        try {
            studentService.remove(vo.getBody().getData().getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_DELETE_FAIL_ERROR, "学生账号删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生账号删除成功"), new ResponseBody<String>("学生账号删除成功"));
    }


}
