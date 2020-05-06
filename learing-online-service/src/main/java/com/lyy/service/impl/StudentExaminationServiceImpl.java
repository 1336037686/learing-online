package com.lyy.service.impl;

import com.lyy.dao.ExaminationDao;
import com.lyy.dao.StudentExaminationDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentExaminationDTO;
import com.lyy.pojo.entity.Examination;
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
    private ExaminationDao examinationDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 批改
     * @param studentExaminationDTO
     * @return
     */
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

    /**
     * 保存学生试卷
     * @param studentExaminationDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean saveExam(StudentExaminationDTO studentExaminationDTO) throws BussinessException {
        // 查找是否已经过期
        Examination examination = examinationDao.queryById(studentExaminationDTO.getExamination());
        if(examination.getEndTime().getTime() < studentExaminationDTO.getTime().getTime()) {
            throw new BussinessException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "已经超过限制时间, 试卷信息保存失败");
        }
        // 查找当前是否已经提交作业
        StudentExamination studentExamination = studentExaminationDao.queryStudentExamByExamAndStudent(studentExaminationDTO.getExamination(), studentExaminationDTO.getStudent());
        if(studentExamination != null) {
            throw new BussinessException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "已提交过试卷, 试卷信息保存失败");
        }
        try {
            StudentExamination s = converterUtil.copyPropertiesAndReturnNewOne(studentExaminationDTO, StudentExamination.class);
            int result = studentExaminationDao.save(s);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "作业信息保存失败");
        }
    }

    /**
     * 修改学生试卷
     * @param studentExaminationDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean updateExam(StudentExaminationDTO studentExaminationDTO) throws BussinessException {
        // 查找是否已经过期
        Examination examination = examinationDao.queryById(studentExaminationDTO.getExamination());
        if(examination.getEndTime().getTime() < studentExaminationDTO.getTime().getTime()) {
            throw new BussinessException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "已经超过限制时间, 试卷信息保存失败");
        }
        // 查找当前试卷是否已经批改
        StudentExamination job = studentExaminationDao.queryStudentExamByExamAndStudent(studentExaminationDTO.getExamination(), studentExaminationDTO.getStudent());
        if(job != null && "1".equals(job.getCheckState())) {
            throw new BussinessException(ErrorCode.SERVICE_EXAM_SAVE_FAIL_ERROR, "试卷已批改, 试卷信息修改失败");
        }
        try {
            StudentExamination studentExamination = converterUtil.copyPropertiesAndReturnNewOne(studentExaminationDTO, StudentExamination.class);
            int result = studentExaminationDao.update(studentExamination);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_EXAM_UPDATE_FAIL_ERROR, "试卷信息修改失败");
        }
    }

}
