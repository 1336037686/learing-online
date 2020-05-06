package com.lyy.service.impl;

import com.lyy.dao.CourseJobDao;
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
    private CourseJobDao courseJobDao;

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

    @Override
    public boolean saveJob(StudentJobDTO studentJobDTO) {
        // 查找是否已经过期
        CourseJob courseJob = courseJobDao.queryById(studentJobDTO.getCourseJob());
        if(courseJob.getEndTime().getTime() < studentJobDTO.getTime().getTime()) {
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "已经超过限制时间, 作业信息保存失败");
        }
        // 查找当前是否已经提交作业
        StudentJob job = studentJobDao.queryStudentJobByJobAndStudent(studentJobDTO.getCourseJob(), studentJobDTO.getStudent());
        if(job != null) {
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "已提交过作业, 作业信息保存失败");
        }
        try {
            StudentJob studentJob = converterUtil.copyPropertiesAndReturnNewOne(studentJobDTO, StudentJob.class);
            int result = studentJobDao.save(studentJob);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "作业信息保存失败");
        }
    }

    @Override
    public boolean updateJob(StudentJobDTO studentJobDTO) {
        // 查找是否已经过期
        CourseJob courseJob = courseJobDao.queryById(studentJobDTO.getCourseJob());
        if(courseJob.getEndTime().getTime() < studentJobDTO.getTime().getTime()) {
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "已经超过限制时间, 作业信息修改失败");
        }
        // 查找当前作业是否已经批改
        StudentJob job = studentJobDao.queryStudentJobByJobAndStudent(studentJobDTO.getCourseJob(), studentJobDTO.getStudent());
        if(job != null && "1".equals(job.getCheckState())) {
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "作业已批改, 作业信息修改失败");
        }
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
