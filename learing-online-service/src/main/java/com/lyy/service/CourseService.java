package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.CourseDTO;

/**
 * 专业
 * @author LGX_TvT
 * @date 2020-03-31 22:02
 */
public interface CourseService {

    /**
     * 查找
     * @param dto
     * @return
     */
    CourseDTO queryAllAndNotCheck(CourseDTO dto) throws BussinessException;

    /**
     * 更新
     * @param courseDTO
     * @return
     */
    boolean update(CourseDTO courseDTO) throws BussinessException;

    /**
     * 按照课程名称模糊查找
     * @param dto
     * @return
     */
    CourseDTO queryByName(CourseDTO dto) throws BussinessException;

    /**
     * 保存
     * @param courseDTO
     * @return
     */
    boolean save(CourseDTO courseDTO) throws BussinessException;

    /**
     * 删除
     * @param courseDTO
     * @return
     */
    boolean remove(CourseDTO courseDTO) throws BussinessException;

    /**
     * 按照教师查找课程信息
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    CourseDTO queryByTeacher(CourseDTO courseDTO) throws BussinessException;

    /**
     * 按照课程ID查找课程信息
     * @param id
     * @return
     */
    CourseDTO queryById(String id) throws BussinessException;
}
