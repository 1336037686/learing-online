package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ResourceDTO;
import com.lyy.pojo.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-27 22:19
 */
public interface ResourceService {

    /**
     * 新增
     * @param resourceDTO
     * @return
     */
    boolean save(ResourceDTO resourceDTO) throws BussinessException;

    /**
     * 删除
     * @param resourceDTO
     * @return
     */
    boolean remove(ResourceDTO resourceDTO) throws BussinessException;

    /**
     * 根据章节ID和课程查找
     * @param resourceDTO
     * @return
     */
    List<Resource> queryAllByCourseAndSection(ResourceDTO resourceDTO) throws BussinessException;

    /**
     * 查找最新资源
     * @param num
     * @return
     */
    List<Resource> queryNewest(Integer num);

    /**
     * 根据课程查找资源列表
     * @param resourceDTO
     * @return
     */
    Map<String, Object> queryMapByCourse(ResourceDTO resourceDTO);
}
