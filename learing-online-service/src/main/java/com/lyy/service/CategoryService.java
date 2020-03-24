package com.lyy.service;

import com.lyy.pojo.dto.CategoryDTO;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:54
 */
public interface CategoryService {

    /**
     * 保存类别信息
     * @param categoryDTO
     * @return
     */
    boolean save(CategoryDTO categoryDTO) throws Exception;

    /**
     * 查询所有类别信息
     * @return
     * @throws Exception
     */
    List<CategoryDTO> queryAll() throws Exception;

    /**
     * 按照id查找类别
     * @param id
     * @return
     * @throws Exception
     */
    CategoryDTO queryById(String id) throws Exception;

    /**
     * 更新类别信息
     * @param categoryDTO
     */
    boolean update(CategoryDTO categoryDTO) throws Exception;

    /**
     * 删除类别信息
     * @param id
     * @return
     * @throws Exception
     */
    boolean remove(String id) throws Exception;
}
