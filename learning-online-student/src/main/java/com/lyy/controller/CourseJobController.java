package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.StudentJobDTO;
import com.lyy.pojo.entity.CourseJob;
import com.lyy.pojo.entity.StudentJob;
import com.lyy.service.CourseJobService;
import com.lyy.service.StudentJobService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作业管理控制层
 * @author LGX_TvT
 * @date 2020-04-29 14:06
 */
@Api(tags = "作业管理Api (V1版本)")
@RequestMapping("/v1/courseJob")
@RestController
public class CourseJobController {

    @Autowired
    private CourseJobService courseJobService;

    @Autowired
    private StudentJobService studentJobService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 新增作业信息
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增作业信息操作", notes = "作业信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<StudentJob> vo) {
        try {
            StudentJobDTO studentJobDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentJobDTO.class);
            studentJobDTO.setId(SnowFlakeUtil.generateId() + "");
            studentJobDTO.setTime(new Date());
            studentJobDTO.setState("0");
            studentJobDTO.setCheckState("0");
            studentJobDTO.setEvaluate("");
            studentJobDTO.setScore(0.0);
            boolean result = studentJobService.saveJob(studentJobDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "作业信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息保存成功"), new ResponseBody<String>("作业信息保存成功"));
    }

    /**
     * 作业修改
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改作业", notes = "作业信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdateStudentJob(@RequestBody CommonRequest<StudentJob> vo) {
        try {
            StudentJobDTO studentJobDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentJobDTO.class);
            studentJobDTO.setTime(new Date());
            studentJobDTO.setCheckState("0");
            studentJobDTO.setScore(0.0);
            studentJobDTO.setEvaluate("");
            boolean result = studentJobService.updateJob(studentJobDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_UPDATE_FAIL_ERROR, "作业信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息修改成功"), new ResponseBody<String>("作业信息修改成功"));
    }

    /**
     * 根据课程查找作业
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程查找所有作业", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}")
    public CommonResponse<List<CourseJob>> doQueryAllByCourse(@PathVariable("courseId") String courseId) {
        List<CourseJob> courseJobs = null;
        try {
            courseJobs = courseJobService.queryAllByCourse(courseId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
        return new CommonResponse<List<CourseJob>>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息查找成功"), new ResponseBody<>(courseJobs));
    }

    /**
     * 根据课程以及学生ID查找学生作业信息作业
     * @param courseId
     * @param studentId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程以及学生ID查找学生作业信息作业", notes = "课程ID，学生ID")
    @ApiILog
    @GetMapping("/query/studentJob/{courseId}/{studentId}")
    public CommonResponse<Map> doQueryStudentJobByCourseAndStudent(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {

        try {
            Map<String, Object> map = courseJobService.queryStudentJobMapByStudentId(courseId, studentId);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息查找成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
    }

    /**
     * 根据作业以及学生ID查找学生作业信息
     * @param jobId
     * @param studentId
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据作业以及学生ID查找学生作业信息", notes = "作业ID，学生ID")
    @ApiILog
    @GetMapping("/query/studentJob/detail/{jobId}/{studentId}")
    public CommonResponse<StudentJob> doQueryStudentJobByJobAndStudent(@PathVariable("jobId") String jobId, @PathVariable("studentId") String studentId) throws AppException {
        try {
            StudentJob studentJob = courseJobService.queryStudentJobByJobAndStudent(jobId, studentId);
            return new CommonResponse<StudentJob>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息查找成功"), new ResponseBody<>(studentJob));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
    }

}
