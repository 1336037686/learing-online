package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.StudentCourseDTO;
import com.lyy.pojo.vo.StudentCourseQueryVO;
import com.lyy.pojo.vo.StudentCourseResponseVO;
import com.lyy.service.StudentCourseService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

/**
 * 学生选课控制层
 */
@Api(tags = "学生选课管理Api (V1版本)")
@RequestMapping("/v1/studentCourse")
@RestController
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 添加学生选课信息
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "添加学生选课信息", notes = "学生选课信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<StudentCourseQueryVO> vo) {
        try {
            StudentCourseDTO studentCourseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentCourseDTO.class);
            studentCourseDTO.setId(SnowFlakeUtil.generateId() + "");
            studentCourseDTO.setState("0");
            boolean result = studentCourseService.save(studentCourseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_SAVE_FAIL_ERROR, "学生选课信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生选课信息保存成功"), new ResponseBody<String>("学生选课信息保存成功"));
    }

    /**
     * 根据选课ID删除选课学生
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据选课ID删除选课学生", notes = "选课ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<StudentCourseQueryVO> vo) throws AppException {
        try {
            StudentCourseDTO studentCourseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentCourseDTO.class);
            boolean result = studentCourseService.remove(studentCourseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_DELETE_FAIL_ERROR, "学生选课信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生选课信息删除成功"), new ResponseBody<String>("学生选课信息删除成功"));
    }

    /**
     * 修改选课信息
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改选课信息", notes = "选课信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<StudentCourseQueryVO> vo) throws AppException {
        try {
            StudentCourseDTO studentCourseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentCourseDTO.class);
            boolean result = studentCourseService.update(studentCourseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_UPDATE_FAIL_ERROR, "选课信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "选课信息修改成功"), new ResponseBody<String>("选课信息修改成功"));
    }

    /**
     * 根据课程ID查看选课学生
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查看选课学生", notes = "课程ID")
    @ApiILog
    @PostMapping("/query/course")
    public CommonResponse<StudentCourseResponseVO> doQueryAllByCourse(@RequestBody CommonRequest<StudentCourseQueryVO> vo) {
        StudentCourseResponseVO studentCourseResponseVO = null;
        try {
            StudentCourseDTO studentCourseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentCourseDTO.class);
            studentCourseDTO = studentCourseService.queryAllByCourse(studentCourseDTO);
            studentCourseResponseVO = converterUtil.copyPropertiesAndReturnNewOne(studentCourseDTO, StudentCourseResponseVO.class);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_QUERY_FAIL_ERROR, "选课信息查找失败");
        }
        return new CommonResponse<StudentCourseResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "选课信息查找成功"), new ResponseBody<>(studentCourseResponseVO));
    }


    // TODO: 直通码创建


    // TODO: 直通码查询


}
