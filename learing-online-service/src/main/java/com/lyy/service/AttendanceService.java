package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.AttendanceDTO;
import com.lyy.pojo.entity.Attendance;

import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-29 3:44
 */
public interface AttendanceService {
    /**
     * 保存
     * @param attendanceDTO
     * @return
     * @throws BussinessException
     */
    boolean save(AttendanceDTO attendanceDTO) throws BussinessException;

    /**
     * 删除
     * @param attendanceDTO
     * @return
     * @throws BussinessException
     */
    boolean remove(AttendanceDTO attendanceDTO) throws BussinessException;

    /**
     * 根据课程查询
     * @param attendanceDTO
     * @return
     * @throws BussinessException
     */
    List<Attendance> queryAllByCourse(AttendanceDTO attendanceDTO) throws BussinessException;

    /**
     * 根据签到ID查看签到基本信息
     * @param attendanceId
     * @return
     */
    Map<String, Object> queryInfo(String attendanceId);
}
