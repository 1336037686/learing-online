package com.lyy.service;

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
    CourseDTO queryAllAndNotCheck(CourseDTO dto);

    /**
     * 更新
     * @param courseDTO
     * @return
     */
    boolean update(CourseDTO courseDTO);

}
