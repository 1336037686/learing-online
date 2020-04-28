package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentCourseDTO;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:30
 */
public interface StudentCourseService {

    boolean save(StudentCourseDTO studentCourseDTO) throws BussinessException;

    boolean remove(StudentCourseDTO studentCourseDTO) throws BussinessException;

    boolean update(StudentCourseDTO studentCourseDTO) throws BussinessException;

    StudentCourseDTO queryAllByCourse(StudentCourseDTO studentCourseDTO) throws BussinessException;
}
