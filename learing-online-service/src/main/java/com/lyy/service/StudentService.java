package com.lyy.service;

import com.lyy.pojo.dto.StudentDTO;

/**
 * 专业
 * @author LGX_TvT
 * @date 2020-03-31 22:02
 */
public interface StudentService {

    /**
     * 保存专业信息
     * @param specialtyDTO
     * @return
     */
    public boolean save(StudentDTO specialtyDTO);

    /**
     * 查找专业信息
     * @param dto
     * @return
     */
    StudentDTO queryAll(StudentDTO dto);

    /**
     * 更新专业信息
     * @param studentDTO
     * @return
     */
    boolean update(StudentDTO studentDTO);

    /**
     * 删除专业信息
     * @param id
     * @return
     */
    boolean remove(String id);

    /**
     * 条件查找
     * @param dto
     * @return
     */
    StudentDTO queryByName(StudentDTO dto);
}
