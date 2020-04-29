package com.lyy.service.impl;

import com.lyy.dao.AttendanceDao;
import com.lyy.dao.StudentAttendanceDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.AttendanceDTO;
import com.lyy.pojo.entity.Attendance;
import com.lyy.pojo.entity.Student;
import com.lyy.service.AttendanceService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-29 3:46
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private StudentAttendanceDao studentAttendanceDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean save(AttendanceDTO attendanceDTO) throws BussinessException {
        try {
            Attendance attendance = converterUtil.copyPropertiesAndReturnNewOne(attendanceDTO, Attendance.class);
            int result = attendanceDao.save(attendance);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_SAVE_FAIL_ERROR, "签到信息保存失败");
        }
    }

    @Override
    public boolean remove(AttendanceDTO attendanceDTO) throws BussinessException {
        try {
            int result = attendanceDao.remove(attendanceDTO.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_DELETE_FAIL_ERROR, "签到信息删除失败");
        }
    }

    @Override
    public List<Attendance> queryAllByCourse(AttendanceDTO attendanceDTO) throws BussinessException {
        try {
            return attendanceDao.queryAllByCourse(attendanceDTO.getCourse());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_QUERY_FAIL_ERROR, "签到信息查找失败");
        }
    }

    /**
     * 根据签到ID查看签到基本信息
     * @param attendanceId
     * @return
     */
    @Override
    public Map<String, Object> queryInfo(String attendanceId) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 查找未签到学员
            List<Student> list = studentAttendanceDao.queryInfo(attendanceId);
            map.put("miss", list);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_ATTENDANCE_QUERY_FAIL_ERROR, "签到信息查找失败");
        }
    }
}
