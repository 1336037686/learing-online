package com.lyy.service.impl;

import com.lyy.dao.StudentJobDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentJobDTO;
import com.lyy.pojo.entity.CourseJob;
import com.lyy.pojo.entity.StudentJob;
import com.lyy.service.StudentJobService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-05-01 2:38
 */
@Service
public class StudentJobServiceImpl implements StudentJobService {

    @Autowired
    private StudentJobDao studentJobDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean update(StudentJobDTO studentJobDTO) {
        try {
            StudentJob studentJob = converterUtil.copyPropertiesAndReturnNewOne(studentJobDTO, StudentJob.class);
            int result = studentJobDao.update(studentJob);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_UPDATE_FAIL_ERROR, "作业信息修改失败");
        }
    }
}
