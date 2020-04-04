package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.CourseDao;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.entity.Course;
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
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<Course> courseList = courseDao.queryAllAndNotCheck();
        PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    /**
     * 更新
     * @param courseDTO
     * @return
     */
    @Override
    public boolean update(CourseDTO courseDTO) {
        courseDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(courseDTO, Course.class));
        return true;
    }

}
