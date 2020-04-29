package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentCourseDTO;
import com.lyy.pojo.entity.extend.StudentCourseExtend;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:30
 */
public interface StudentCourseService {

    boolean save(StudentCourseDTO studentCourseDTO) throws BussinessException;

    boolean remove(StudentCourseDTO studentCourseDTO) throws BussinessException;

    boolean update(StudentCourseDTO studentCourseDTO) throws BussinessException;

    List<StudentCourseExtend> queryAllByCourse(StudentCourseDTO studentCourseDTO) throws BussinessException;
}
