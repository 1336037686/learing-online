package com.lyy.service.impl;

import com.lyy.dao.AttendanceDao;
import com.lyy.dao.StudentAttendanceDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.entity.Attendance;
import com.lyy.pojo.entity.StudentAttendance;
import com.lyy.service.StudentAttendanceService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-05-06 22:41
 */
@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    @Autowired
    private StudentAttendanceDao studentAttendanceDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean save(StudentAttendance data) throws BussinessException {
        // 查看是否已签到
        StudentAttendance studentAttendance = studentAttendanceDao.queryByAttendanceAndStudent(data.getAttendance(), data.getStudent());
        if(studentAttendance != null) {
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_SAVE_FAIL_ERROR, "已经签到，无需重复签到");
        }
        // 查看签到时间是否已过
        Attendance attendance = attendanceDao.queryById(data.getAttendance());
        if(attendance.getEndTime().getTime() < data.getTime().getTime()) {
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_SAVE_FAIL_ERROR, "签到失败，超过签到时间");
        }
        try {
            int result = studentAttendanceDao.save(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_SAVE_FAIL_ERROR, "签到信息保存失败");
        }
        return true;
    }
}
