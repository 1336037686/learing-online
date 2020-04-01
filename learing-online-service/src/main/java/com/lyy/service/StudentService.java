package com.lyy.service;

import com.lyy.pojo.dto.SpecialtyDTO;

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
    boolean save(SpecialtyDTO specialtyDTO);

    /**
     * 查找专业信息
     * @param dto
     * @return
     */
    SpecialtyDTO queryAll(SpecialtyDTO dto);

    /**
     * 更新专业信息
     * @param specialtyDTO
     * @return
     */
    boolean update(SpecialtyDTO specialtyDTO);

    /**
     * 删除专业信息
     * @param id
     * @return
     */
    boolean remove(String id);
}
