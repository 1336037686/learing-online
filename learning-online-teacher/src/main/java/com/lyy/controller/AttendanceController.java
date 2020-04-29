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
import com.lyy.pojo.vo.AttendanceQueryVO;
import com.lyy.service.AttendanceService;
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
    private ConverterUtil converterUtil;

    /**
     * 新增签到操作
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增签到操作", notes = "签到时长")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<AttendanceQueryVO> vo) {
        try {
            Date startTime = new Date();
            Date endTime = new Date();
            endTime.setTime(endTime.getTime() + vo.getBody().getData().getDate() * 60 * 1000);
            AttendanceDTO attendanceDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AttendanceDTO.class);
            attendanceDTO.setId(SnowFlakeUtil.generateId() + "");
            attendanceDTO.setStartTime(startTime);
            attendanceDTO.setEndTime(endTime);
            attendanceDTO.setState("0");
            boolean result = attendanceService.save(attendanceDTO);
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
     * 删除签到操作
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据签到ID删除签到操作记录", notes = "签到ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<AttendanceQueryVO> vo) throws AppException {
        try {
            AttendanceDTO attendanceDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AttendanceDTO.class);
            boolean result = attendanceService.remove(attendanceDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_ATTENDANCE_DELETE_FAIL_ERROR, "签到信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "签到信息删除成功"), new ResponseBody<String>("签到信息删除成功"));
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

    /**
     * 根据签到ID查看签到基本信息
     * @param attendanceId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据签到ID查看签到基本信息", notes = "签到ID")
    @ApiILog
    @GetMapping("/query/info/{attendanceId}")
    public CommonResponse<Map> doQueryInfo(@PathVariable("attendanceId") String attendanceId) {
        Map<String, Object> attendanceResponseVO = null;
        try {
            attendanceResponseVO = attendanceService.queryInfo(attendanceId);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_ATTENDANCE_QUERY_FAIL_ERROR, "签到信息查找失败");
        }
        return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "签到信息查找成功"), new ResponseBody<>(attendanceResponseVO));
    }
}
