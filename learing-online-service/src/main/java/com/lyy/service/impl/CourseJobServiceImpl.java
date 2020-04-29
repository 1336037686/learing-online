package com.lyy.service.impl;

import com.lyy.dao.CourseJobDao;
import com.lyy.dao.StudentJobDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.CourseJobDTO;
import com.lyy.pojo.entity.CourseJob;
import com.lyy.pojo.entity.extend.StudentJobExtend;
import com.lyy.service.CourseJobService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-29 14:56
 */
@Service
public class CourseJobServiceImpl implements CourseJobService {

    @Autowired
    private CourseJobDao courseJobDao;

    @Autowired
    private StudentJobDao studentJobDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean save(CourseJobDTO courseJobDTO) throws BussinessException {
        try {
            CourseJob courseJob = converterUtil.copyPropertiesAndReturnNewOne(courseJobDTO, CourseJob.class);
            int result = courseJobDao.save(courseJob);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_SAVE_FAIL_ERROR, "作业信息保存失败");
        }
    }

    @Override
    public boolean remove(CourseJobDTO courseJobDTO) throws BussinessException {
        try {
            int result = courseJobDao.remove(courseJobDTO.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_DELETE_FAIL_ERROR, "作业信息删除失败");
        }
    }

    @Override
    public boolean update(CourseJobDTO courseJobDTO) throws BussinessException {
        try {
            CourseJob courseJob = converterUtil.copyPropertiesAndReturnNewOne(courseJobDTO, CourseJob.class);
            int result = courseJobDao.update(courseJob);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_UPDATE_FAIL_ERROR, "作业信息修改失败");
        }
    }

    @Override
    public List<CourseJob> queryAllByCourse(String courseId) throws BussinessException {
        try {
            return courseJobDao.queryAllByCourse(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
    }

    @Override
    public List<StudentJobExtend> queryStudentJobByJobId(String jobId) throws BussinessException {
        try {
            return studentJobDao.queryStudentJobByJobId(jobId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
    }

    @Override
    public Map<String, Object> queryMissStudentJobByCourseAndJob(String courseId, String jobId) throws BussinessException {
        Map<String, Object> map = new HashMap<>();
        try {
            List<StudentJobExtend> list = studentJobDao.queryMissStudentJobByCourseAndJob(courseId, jobId);
            map.put("miss", list);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_JOB_QUERY_FAIL_ERROR, "作业信息查找失败");
        }
    }
}
