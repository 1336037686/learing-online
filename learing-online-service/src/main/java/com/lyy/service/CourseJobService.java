package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.CourseJobDTO;
import com.lyy.pojo.entity.CourseJob;
import com.lyy.pojo.entity.StudentJob;
import com.lyy.pojo.entity.extend.StudentJobExtend;

import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-29 14:55
 */
public interface CourseJobService {


    boolean save(CourseJobDTO courseJobDTO) throws BussinessException;

    boolean remove(CourseJobDTO courseJobDTO) throws BussinessException;

    boolean update(CourseJobDTO courseJobDTO) throws BussinessException;

    List<CourseJob> queryAllByCourse(String courseId) throws BussinessException;

    List<StudentJobExtend> queryStudentJobByJobId(String jobId) throws BussinessException;

    Map<String, Object> queryMissStudentJobByCourseAndJob(String courseId, String jobId) throws BussinessException;

    Map<String, Object> queryStudentJobMapByStudentId(String courseId, String studentId) throws BussinessException;

    StudentJob queryStudentJobByJobAndStudent(String jobId, String studentId) throws BussinessException;
}
