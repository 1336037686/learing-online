package com.lyy.service;

import com.lyy.pojo.dto.DepartmentDTO;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:54
 */
public interface DepartmentService {

    /**
     * 保存
     * @param departmentDTO
     * @return
     */
    boolean save(DepartmentDTO departmentDTO) throws Exception;

    /**
     * 查询
     * @return
     * @throws Exception
     */
    List<DepartmentDTO> queryAll() throws Exception;

    /**
     * 按照
     * @param id
     * @return
     * @throws Exception
     */
    DepartmentDTO queryById(String id) throws Exception;

    /**
     * 更新
     * @param departmentDTO
     */
    boolean update(DepartmentDTO departmentDTO) throws Exception;

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    boolean remove(String id) throws Exception;
}
