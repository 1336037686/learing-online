package com.lyy.service.impl;

import com.lyy.dao.StudentExaminationDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentExaminationDTO;
import com.lyy.pojo.entity.StudentExamination;
import com.lyy.service.StudentExaminationService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-05-01 11:47
 */
@Service
public class StudentExaminationServiceImpl implements StudentExaminationService {

    @Autowired
    private StudentExaminationDao studentExaminationDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean update(StudentExaminationDTO studentExaminationDTO) {
        try {
            StudentExamination studentExamination = converterUtil.copyPropertiesAndReturnNewOne(studentExaminationDTO, StudentExamination.class);
            int result = studentExaminationDao.update(studentExamination);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "考试信息修改失败");
        }
    }
}
