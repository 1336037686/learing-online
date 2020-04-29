package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.StudentCourseDTO;
import com.lyy.pojo.entity.extend.StudentCourseExtend;
import com.lyy.pojo.vo.StudentCourseQueryVO;
import com.lyy.service.StudentCourseService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            studentCourseDTO.setTime(new Date());
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
     * 根据课程ID和类型查看选课学生
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID和类型查看选课学生", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}/{type}")
    public CommonResponse<List<StudentCourseExtend>> doQueryAllByCourse(@PathVariable("courseId") String courseId, @PathVariable("type") String type) {
        List<StudentCourseExtend> studentCourseResponseVO = new ArrayList<>();
        try {
            StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
            studentCourseDTO.setCourse(courseId);
            List<StudentCourseExtend> list = studentCourseService.queryAllByCourse(studentCourseDTO);
            List<StudentCourseExtend> passList = new ArrayList<>();
            List<StudentCourseExtend> failList = new ArrayList<>();
            for (StudentCourseExtend x : list) {
                if("1".equals(x.getCheckState())) {
                    passList.add(x);
                } else {
                    failList.add(x);
                }
            }
            switch (type) {
                case "0":
                    // 查询未通过
                    studentCourseResponseVO = failList;
                    break;
                case "1":
                    // 查询已通过
                    studentCourseResponseVO = passList;
                    break;
                default:
                    // 查询所有
                    studentCourseResponseVO = list;
            }
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_QUERY_FAIL_ERROR, "选课信息查找失败");
        }
        return new CommonResponse<List<StudentCourseExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "选课信息查找成功"), new ResponseBody<>(studentCourseResponseVO));
    }


    // TODO: 直通码创建


    // TODO: 直通码查询


}
