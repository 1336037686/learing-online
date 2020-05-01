package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ExaminationDTO;
import com.lyy.pojo.entity.Examination;
import com.lyy.pojo.entity.extend.StudentExaminationExtend;

import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-30 19:17
 */
public interface ExaminationService {
    boolean save(ExaminationDTO examinationDTO) throws BussinessException;

    boolean remove(ExaminationDTO examinationDTO) throws BussinessException;

    boolean update(ExaminationDTO examinationDTO) throws BussinessException;

    List<Examination> queryAllByCourse(String courseId) throws BussinessException;

    List<StudentExaminationExtend> queryStudentExaminationByExamId(String examId) throws BussinessException;

    Map<String, Object> queryMissStudentExaminationByCourseAndExam(String courseId, String examId) throws BussinessException;
}
