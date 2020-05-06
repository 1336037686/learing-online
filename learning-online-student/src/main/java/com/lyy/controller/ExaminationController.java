package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.StudentExaminationDTO;
import com.lyy.pojo.entity.Examination;
import com.lyy.pojo.entity.StudentExamination;
import com.lyy.service.ExaminationService;
import com.lyy.service.StudentExaminationService;
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
 * 考试管理控制层
 * @author LGX_TvT
 * @date 2020-04-29 14:06
 */
@Api(tags = "考试管理Api (V1版本)")
@RequestMapping("/v1/exam")
@RestController
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private StudentExaminationService studentExaminationService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 新增考试信息
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增考试信息操作", notes = "考试信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<StudentExamination> vo) {
        try {
            StudentExaminationDTO studentExaminationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentExaminationDTO.class);
            studentExaminationDTO.setId(SnowFlakeUtil.generateId() + "");
            studentExaminationDTO.setTime(new Date());
            studentExaminationDTO.setState("0");
            studentExaminationDTO.setCheckState("0");
            studentExaminationDTO.setEvaluate("");
            studentExaminationDTO.setScore(0.0);
            boolean result = studentExaminationService.saveExam(studentExaminationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "考试信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "考试信息保存成功"), new ResponseBody<String>("考试信息保存成功"));
    }

    /**
     * 考试修改
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改考试", notes = "考试信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdateStudentJob(@RequestBody CommonRequest<StudentExamination> vo) {
        try {
            StudentExaminationDTO studentExaminationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentExaminationDTO.class);
            studentExaminationDTO.setTime(new Date());
            studentExaminationDTO.setCheckState("0");
            studentExaminationDTO.setScore(0.0);
            studentExaminationDTO.setEvaluate("");
            boolean result = studentExaminationService.updateExam(studentExaminationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "考试信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "考试信息修改成功"), new ResponseBody<String>("考试信息修改成功"));
    }

    /**
     * 根据课程查找考试
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程查找所有考试", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}")
    public CommonResponse<List<Examination>> doQueryAllByCourse(@PathVariable("courseId") String courseId) {
        List<Examination> examinations = null;
        try {
            examinations = examinationService.queryAllByCourse(courseId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "考试信息查找失败");
        }
        return new CommonResponse<List<Examination>>(new ResponseHead(StateCode.SUCCEED_CODE, "考试信息查找成功"), new ResponseBody<>(examinations));
    }

    /**
     * 根据课程以及学生ID查找学生考试信息考试
     * @param courseId
     * @param studentId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程以及学生ID查找学生考试信息考试", notes = "课程ID，学生ID")
    @ApiILog
    @GetMapping("/query/studentExam/{courseId}/{studentId}")
    public CommonResponse<Map> doQueryStudentJobByCourseAndStudent(@PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {
        try {
            Map<String, Object> map = examinationService.queryStudentExamMapByCourseAndStudent(courseId, studentId);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "考试信息查找成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "考试信息查找失败");
        }
    }

    /**
     * 根据考试以及学生ID查找学生考试信息
     * @param examId
     * @param studentId
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据考试以及学生ID查找学生考试信息", notes = "考试ID，学生ID")
    @ApiILog
    @GetMapping("/query/studentExam/detail/{examId}/{studentId}")
    public CommonResponse<StudentExamination> doQueryStudentExamByJobAndStudent(@PathVariable("examId") String examId, @PathVariable("studentId") String studentId) throws AppException {
        try {
            StudentExamination studentExamination = examinationService.queryStudentExamByExamAndStudent(examId, studentId);
            return new CommonResponse<StudentExamination>(new ResponseHead(StateCode.SUCCEED_CODE, "考试信息查找成功"), new ResponseBody<>(studentExamination));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "考试信息查找失败");
        }
    }

}
