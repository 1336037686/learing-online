package com.lyy.service;

import com.lyy.pojo.dto.SpecialtyDTO;
import com.lyy.pojo.entity.Specialty;

import java.util.List;

/**
 * 专业
 * @author LGX_TvT
 * @date 2020-03-31 22:02
 */
public interface SpecialtyService {

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
     * 查找专业信息
     * @return
     */
    List<Specialty> queryAll();

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
