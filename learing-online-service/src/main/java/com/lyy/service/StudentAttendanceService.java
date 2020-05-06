package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.entity.StudentAttendance;

/**
 * @author LGX_TvT
 * @date 2020-05-06 22:41
 */
public interface StudentAttendanceService {
    boolean save(StudentAttendance data) throws BussinessException;
}
