package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ExaminationDTO;
import com.lyy.pojo.entity.Examination;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-30 19:17
 */
public interface ExaminationService {
    boolean save(ExaminationDTO examinationDTO) throws BussinessException;

    boolean remove(ExaminationDTO examinationDTO) throws BussinessException;

    boolean update(ExaminationDTO examinationDTO) throws BussinessException;

    List<Examination> queryAllByCourse(String courseId) throws BussinessException;
}
