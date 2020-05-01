package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.ExaminationDTO;
import com.lyy.pojo.dto.StudentExaminationDTO;
import com.lyy.pojo.entity.Examination;
import com.lyy.pojo.entity.StudentExamination;
import com.lyy.pojo.entity.extend.StudentExaminationExtend;
import com.lyy.pojo.vo.ExaminationQueryVO;
import com.lyy.service.ExaminationService;
import com.lyy.service.StudentExaminationService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考卷管理控制层
 * @author LGX_TvT
 * @date 2020-04-29 14:06
 */
@Api(tags = "考卷管理Api (V1版本)")
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
     * 新增考卷信息操作
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增考卷信息操作", notes = "签到信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<ExaminationQueryVO> vo) {
        try {
            ExaminationDTO examinationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), ExaminationDTO.class);
            examinationDTO.setId(SnowFlakeUtil.generateId() + "");
            examinationDTO.setState("0");
            boolean result = examinationService.save(examinationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "考卷信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "考卷信息保存成功"), new ResponseBody<String>("考卷信息保存成功"));
    }

    /**
     * 删除考卷
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据考卷ID删除考卷信息记录", notes = "考卷ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<ExaminationQueryVO> vo) throws AppException {
        try {
            ExaminationDTO examinationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), ExaminationDTO.class);
            boolean result = examinationService.remove(examinationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_DELETE_FAIL_ERROR, "考卷信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "考卷信息删除成功"), new ResponseBody<String>("考卷信息删除成功"));
    }

    /**
     * 修改考卷信息
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改考卷信息", notes = "基础考卷信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<ExaminationQueryVO> vo) throws AppException {
        try {
            ExaminationDTO examinationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), ExaminationDTO.class);
            boolean result = examinationService.update(examinationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "考卷信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "考卷信息修改成功"), new ResponseBody<String>("考卷信息修改成功"));
    }

    /**
     * 根据课程查找考卷
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程查找所有考卷", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}")
    public CommonResponse<List<Examination>> doQueryAllByCourse(@PathVariable("courseId") String courseId) {
        List<Examination> courseJobs = null;
        try {
            courseJobs = examinationService.queryAllByCourse(courseId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "考卷信息查找失败");
        }
        return new CommonResponse<List<Examination>>(new ResponseHead(StateCode.SUCCEED_CODE, "考卷信息查找成功"), new ResponseBody<>(courseJobs));
    }

    /**
     * 根据试卷ID查找上交的试卷
     * @param examId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据试卷ID查找上交的试卷", notes = "试卷ID")
    @ApiILog
    @GetMapping("/query/studentExamination/{examId}")
    public CommonResponse<List<StudentExaminationExtend>> doQueryStudentExaminationByExamId(@PathVariable("examId") String examId) {
        List<StudentExaminationExtend> studentExaminationExtends = null;
        try {
            studentExaminationExtends = examinationService.queryStudentExaminationByExamId(examId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "试卷信息查找失败");
        }
        return new CommonResponse<List<StudentExaminationExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "试卷信息查找成功"), new ResponseBody<>(studentExaminationExtends));
    }

    /**
     * 查找未上交
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据试卷ID以及课程ID查找试卷未上交的信息", notes = "试卷ID，课程ID")
    @ApiILog
    @GetMapping("/query/studentExamination/{courseId}/{examId}")
    public CommonResponse<Map> doQueryMissStudentExaminationByCourseAndExaminationId(@PathVariable("courseId") String courseId, @PathVariable("examId") String examId) {
        Map<String, Object> map = null;
        try {
            map = examinationService.queryMissStudentExaminationByCourseAndExam(courseId, examId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "试卷信息查找失败");
        }
        return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "试卷信息查找成功"), new ResponseBody<>(map));
    }

    /**
     * 作业批改
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改学生试卷（批改）", notes = "学生作业试卷")
    @ApiILog
    @PutMapping("/update/studentExamination")
    public CommonResponse<String> doUpdateStudentExamination(@RequestBody CommonRequest<StudentExamination> vo) {
        try {
            StudentExaminationDTO studentExaminationDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentExaminationDTO.class);
            boolean result = studentExaminationService.update(studentExaminationDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "试卷信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "试卷信息修改成功"), new ResponseBody<String>("试卷信息修改成功"));
    }


}
