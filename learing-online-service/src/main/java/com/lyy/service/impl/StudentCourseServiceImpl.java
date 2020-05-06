package com.lyy.service.impl;

import com.lyy.dao.StudentCourseDao;
import com.lyy.dao.StudentDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentCourseDTO;
import com.lyy.pojo.entity.Student;
import com.lyy.pojo.entity.StudentCourse;
import com.lyy.pojo.entity.extend.StudentCourseExtend;
import com.lyy.service.StudentCourseService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:31
 */
@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public boolean save(StudentCourseDTO studentCourseDTO) throws BussinessException {
        studentCourseDTO.setCheckState("0");
        // 判断学生学号是否存在
        Student student = studentDao.queryByUserName(studentCourseDTO.getStudent());
        if(student == null) {
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_SAVE_FAIL_ERROR, "该学生学号不存在");
        }
        // 判断学生是否已经选过课程
        StudentCourse studentCourse = studentCourseDao.queryByCourseAndStudent(studentCourseDTO.getCourse(), student.getId());
        if(studentCourse != null) {
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_SAVE_FAIL_ERROR, "已经参加该课程, 无需重复添加课程");
        }
        try {
            studentCourse = converterUtil.copyPropertiesAndReturnNewOne(studentCourseDTO, StudentCourse.class);
            studentCourse.setStudent(student.getId());
            int result = studentCourseDao.save(studentCourse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_SAVE_FAIL_ERROR, "选课信息保存失败");
        }
        return true;
    }

    @Override
    public boolean remove(StudentCourseDTO studentCourseDTO) throws BussinessException {
        try {
            int result = studentCourseDao.remove(studentCourseDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_DELETE_FAIL_ERROR, "选课信息删除失败");
        }
        return true;
    }

    @Override
    public boolean update(StudentCourseDTO studentCourseDTO) throws BussinessException {
        try {
            StudentCourse studentCourse = converterUtil.copyPropertiesAndReturnNewOne(studentCourseDTO, StudentCourse.class);
            int result = studentCourseDao.update(studentCourse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_UPDATE_FAIL_ERROR, "选课信息修改失败");
        }
        return true;
    }

    @Override
    public List<StudentCourseExtend> queryAllByCourse(StudentCourseDTO studentCourseDTO) throws BussinessException {
        try {
            List<StudentCourseExtend> list = studentCourseDao.queryAllByCourse(studentCourseDTO.getCourse());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_COURSE_QUERY_FAIL_ERROR, "选课信息查找失败");
        }
    }
}
