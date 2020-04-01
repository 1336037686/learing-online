package com.lyy.service;

import com.lyy.pojo.dto.TeacherDTO;

/**
 * 专业
 * @author LGX_TvT
 * @date 2020-03-31 22:02
 */
public interface TeacherService {

    /**
     * 保存专业信息
     * @param teacherDTO
     * @return
     */
    boolean save(TeacherDTO teacherDTO);

    /**
     * 查找专业信息
     * @param dto
     * @return
     */
    TeacherDTO queryAll(TeacherDTO dto);

    /**
     * 更新专业信息
     * @param teacherDTO
     * @return
     */
    boolean update(TeacherDTO teacherDTO);

    /**
     * 删除专业信息
     * @param id
     * @return
     */
    boolean remove(String id);
}
