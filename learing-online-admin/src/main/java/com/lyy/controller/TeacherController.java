package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.entity.Teacher;
import com.lyy.pojo.vo.TeacherQueryVO;
import com.lyy.pojo.vo.TeacherResponseVO;
import com.lyy.service.TeacherService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师账号管理 控制层
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "教师账号管理Api (V1版本)")
@RequestMapping("/v1/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存教师账号
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "保存教师账号", notes = "需要类别名称（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<TeacherQueryVO> vo) throws AppException {
        try {
            TeacherDTO teacherDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);

            // 添加id
            teacherDTO.setId(SnowFlakeUtil.generateId() + "");
            // 添加状态
            teacherDTO.setState("0");
            // 密码加密
            teacherDTO.setPassword(Md5Util.md5(teacherDTO.getPassword()));

            boolean result = teacherService.save(teacherDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_TEACHER_SAVE_FAIL_ERROR, "教师账号保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号添加成功"), new ResponseBody<String>("教师账号添加成功"));
    }

    /**
     * 分页查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页查找教师账号信息", notes = "需要页号，长度范围.")
    @ApiILog
    @PostMapping("/query")
    public CommonResponse<TeacherResponseVO> doQueryAll(@org.springframework.web.bind.annotation.RequestBody CommonRequest<TeacherQueryVO> vo) throws AppException {
        TeacherDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);
        TeacherResponseVO teacherResponseVO = null;
        try {
            TeacherDTO teacherDTO = teacherService.queryAll(dto);
            teacherResponseVO = converterUtil.copyPropertiesAndReturnNewOne(teacherDTO, TeacherResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "教师账号查找失败");
        }
        return new CommonResponse<TeacherResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号查找成功"), new ResponseBody<TeacherResponseVO>(teacherResponseVO));
    }

    /**
     * 条件查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "条件查找教师账号信息", notes = "需要页号，长度范围，姓名.")
    @ApiILog
    @PostMapping("/condition")
    public CommonResponse<TeacherResponseVO> doQueryByName(@org.springframework.web.bind.annotation.RequestBody CommonRequest<TeacherQueryVO> vo) throws AppException {
        TeacherDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);
        TeacherResponseVO teacherResponseVO = null;
        try {
            TeacherDTO teacherDTO = teacherService.queryByName(dto);
            teacherResponseVO = converterUtil.copyPropertiesAndReturnNewOne(teacherDTO, TeacherResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "教师账号查找失败");
        }
        return new CommonResponse<TeacherResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号查找成功"), new ResponseBody<TeacherResponseVO>(teacherResponseVO));
    }

    /**
     * 查找管理员公告信息
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "查找所有教师账号信息", notes = "无")
    @ApiILog
    @GetMapping("/query")
    public CommonResponse<List<Teacher>> doQueryAll() throws AppException {
        List<Teacher> teacherResponseVOList = null;
        try {
            teacherResponseVOList = teacherService.queryAll();
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "教师账号查找失败");
        }
        return new CommonResponse<List<Teacher>>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号查找成功"), new ResponseBody<List<Teacher>>(teacherResponseVOList));
    }

    /**
     * 更新课程教师账号
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "修改教师账号", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<TeacherQueryVO> vo) throws AppException {
        try {
            TeacherDTO teacherDTO = converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), TeacherDTO.class);

            if(teacherDTO.getPassword().length() < 32) {
                teacherDTO.setPassword(Md5Util.md5(teacherDTO.getPassword()));
            }

            teacherService.update(teacherDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_TEACHER_UPDATE_FAIL_ERROR, "教师账号更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号更新成功"), new ResponseBody<String>("教师账号更新成功"));
    }

    /**
     * 保存课程教师账号
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "删除教师账号", notes = "id（必填）")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<TeacherQueryVO> vo) throws AppException {
        try {
            teacherService.remove(vo.getBody().getData().getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_TEACHER_DELETE_FAIL_ERROR, "教师账号删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "教师账号删除成功"), new ResponseBody<String>("教师账号删除成功"));
    }


}
