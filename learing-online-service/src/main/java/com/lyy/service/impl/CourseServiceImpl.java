package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.CourseDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.entity.Course;
import com.lyy.pojo.entity.extend.CourseExtend;
import com.lyy.service.CourseService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-31 22:04
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private ConverterUtil converterUtil;


    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public CourseDTO queryAllAndNotCheck(CourseDTO dto) {
        try {
            PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
            List<Course> courseList = courseDao.queryAllAndNotCheck();
            PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
            dto.setPageInfo(pageInfo);
            return dto;
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
    }

    /**
     * 更新
     * @param courseDTO
     * @return
     */
    @Override
    public boolean update(CourseDTO courseDTO) {
        try {
            courseDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(courseDTO, Course.class));
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_UPDATE_FAIL_ERROR, "课程信息更新失败");
        }
        return true;
    }

    /**
     * 课程名称模糊查找
     * @param dto
     * @return
     */
    @Override
    public CourseDTO queryByName(CourseDTO dto) throws BussinessException{
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        try {
            List<Course> courseList = courseDao.queryByName(dto.getName());
            PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
            dto.setPageInfo(pageInfo);
            return dto;
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
    }

    /**
     * 保存
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean save(CourseDTO courseDTO) throws BussinessException {
        try {
            Course course = converterUtil.copyPropertiesAndReturnNewOne(courseDTO, Course.class);
            int result = courseDao.save(course);
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程信息保存失败");
        }
        return true;
    }

    /**
     * 删除
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean remove(CourseDTO courseDTO) throws BussinessException {
        // 判断是否有章节

        // 删除章节
        try {
            int result = courseDao.remove(courseDTO.getId());
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_UPDATE_FAIL_ERROR, "课程信息更新失败");
        }
        return true;
    }

    /**
     * 根据教师信息查找课程信息
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public CourseDTO queryByTeacher(CourseDTO courseDTO) throws BussinessException {
        PageHelper.startPage(courseDTO.getCurrentPage(), courseDTO.getSize());
        try {
            List<CourseExtend> course = courseDao.queryByTeacher(courseDTO.getTeacher());
            PageInfo<CourseExtend> pageInfo = new PageInfo<>(course);
            courseDTO.setPageInfo(pageInfo);
            return courseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }

    /**
     * 按照教师查找所有已经审核通过的课程信息
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public List<CourseExtend> queryAllPassByTeacher(CourseDTO courseDTO) throws BussinessException {
        return courseDao.queryAllPassByTeacher(courseDTO.getTeacher());
    }

    /**
     * 按照课程ID查找课程信息
     * @param id
     * @return
     * @throws BussinessException
     */
    @Override
    public CourseDTO queryById(String id) throws BussinessException {
        try {
            Course course = courseDao.queryById(id);
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(course, CourseDTO.class);
            return courseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }
}
