package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentExaminationDTO;

/**
 * @author LGX_TvT
 * @date 2020-05-01 2:38
 */
public interface StudentExaminationService {
    boolean update(StudentExaminationDTO studentExaminationDTO) throws BussinessException;

    boolean saveExam(StudentExaminationDTO studentExaminationDTO) throws BussinessException;

    boolean updateExam(StudentExaminationDTO studentExaminationDTO) throws BussinessException;
}
