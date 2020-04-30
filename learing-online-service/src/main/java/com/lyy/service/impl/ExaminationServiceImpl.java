package com.lyy.service.impl;

import com.lyy.dao.ExaminationDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ExaminationDTO;
import com.lyy.pojo.entity.Examination;
import com.lyy.service.ExaminationService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-30 19:19
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationDao examinationDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean save(ExaminationDTO examinationDTO) throws BussinessException {
        try {
            Examination examination = converterUtil.copyPropertiesAndReturnNewOne(examinationDTO, Examination.class);
            int result = examinationDao.save(examination);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "考试信息保存失败");
        }
    }

    @Override
    public boolean remove(ExaminationDTO examinationDTO) throws BussinessException {
        try {
            int result = examinationDao.remove(examinationDTO.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_DELETE_FAIL_ERROR, "考试信息删除失败");
        }
    }

    @Override
    public boolean update(ExaminationDTO examinationDTO) throws BussinessException {
        try {
            Examination examination = converterUtil.copyPropertiesAndReturnNewOne(examinationDTO, Examination.class);
            int result = examinationDao.update(examination);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "考试信息修改失败");
        }
    }

    @Override
    public List<Examination> queryAllByCourse(String courseId) throws BussinessException {
        try {
            return examinationDao.queryAllByCourse(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_QUERY_FAIL_ERROR, "考试信息查找失败");
        }
    }
    
}