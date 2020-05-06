package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.AttendanceDTO;
import com.lyy.pojo.entity.Attendance;
import com.lyy.pojo.entity.StudentAttendance;
import com.lyy.service.AttendanceService;
import com.lyy.service.StudentAttendanceService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 签到管理控制层
 * @author LGX_TvT
 * @date 2020-04-29 3:31
 */
@Api(tags = "签到管理Api (V1版本)")
@RequestMapping("/v1/attendance")
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 新增签到操作
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增签到操作", notes = "无")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<StudentAttendance> vo) {
        try {
            StudentAttendance data = vo.getBody().getData();
            data.setId(SnowFlakeUtil.generateId() + "");
            data.setState("0");
            data.setTime(new Date());
            boolean result = studentAttendanceService.save(data);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_ATTENDANCE_SAVE_FAIL_ERROR, "签到信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "签到信息保存成功"), new ResponseBody<String>("签到信息保存成功"));
    }

    /**
     * 根据课程ID查询签到操作
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查询签到操作", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}")
    public CommonResponse<List<Attendance>> doQueryAllByCourse(@PathVariable("courseId") String courseId) {
        List<Attendance> attendanceResponseVO = null;
        try {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setCourse(courseId);
            attendanceResponseVO = attendanceService.queryAllByCourse(attendanceDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_ATTENDANCE_QUERY_FAIL_ERROR, "选课信息查找失败");
        }
        return new CommonResponse<List<Attendance>>(new ResponseHead(StateCode.SUCCEED_CODE, "签到信息查找成功"), new ResponseBody<>(attendanceResponseVO));
    }


}
