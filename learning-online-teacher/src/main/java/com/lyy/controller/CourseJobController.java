package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CourseJobDTO;
import com.lyy.pojo.entity.CourseJob;
import com.lyy.pojo.entity.StudentJob;
import com.lyy.pojo.entity.extend.StudentJobExtend;
import com.lyy.pojo.vo.CourseJobQueryVO;
import com.lyy.service.CourseJobService;
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
    private ConverterUtil converterUtil;

    /**
     * 新增作业信息操作
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增作业信息操作", notes = "签到信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<CourseJobQueryVO> vo) {
        try {
            CourseJobDTO courseJobDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseJobDTO.class);
            courseJobDTO.setId(SnowFlakeUtil.generateId() + "");
            courseJobDTO.setState("0");
            boolean result = courseJobService.save(courseJobDTO);
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
     * 删除作业
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据作业ID删除作业信息记录", notes = "作业ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<CourseJobQueryVO> vo) throws AppException {
        try {
            CourseJobDTO courseJobDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseJobDTO.class);
            boolean result = courseJobService.remove(courseJobDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_DELETE_FAIL_ERROR, "作业信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息删除成功"), new ResponseBody<String>("作业信息删除成功"));
    }

    /**
     * 修改作业信息
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改作业信息", notes = "基础作业信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<CourseJobQueryVO> vo) throws AppException {
        try {
            CourseJobDTO courseJobDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseJobDTO.class);
            boolean result = courseJobService.update(courseJobDTO);
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
    @PostMapping("/query/course/{courseId}")
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
     * 根据课程ID查找上交的作业
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据作业ID查找上交的作业", notes = "课程ID")
    @ApiILog
    @PostMapping("/query/studentJob/{courseId}")
    public CommonResponse<List<StudentJobExtend>> doQueryStudentJobByJobId(@PathVariable("courseId") String courseId) {
        List<StudentJobExtend> studentJobExtends = null;
        try {
            studentJobExtends = courseJobService.queryStudentJobByJobId(courseId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
        return new CommonResponse<List<StudentJobExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息查找成功"), new ResponseBody<>(studentJobExtends));
    }

    /**
     * 查找未上交
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据作业ID查找作业未上交的信息", notes = "作业ID")
    @ApiILog
    @PostMapping("/query/studentJob/{courseId}/{jobId}")
    public CommonResponse<Map> doQueryMissStudentJobByJobId(@PathVariable("courseId") String courseId, @PathVariable("jobId") String jobId) {
        Map<String, Object> map = null;
        try {
            map = courseJobService.queryMissStudentJobByCourseAndJob(courseId, jobId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
        return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "作业信息查找成功"), new ResponseBody<>(map));
    }

    /**
     * 作业批改
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查找作业未上交的信息", notes = "课程ID")
    @ApiILog
    @PostMapping("/update/studentJob")
    public CommonResponse<String> doUpdateStudentJob(CommonRequest<StudentJob> vo) {
        return null;
    }

}
