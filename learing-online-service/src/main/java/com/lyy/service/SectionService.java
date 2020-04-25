package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.SectionDTO;
import com.lyy.pojo.entity.Section;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-13 19:56
 */
public interface SectionService {

    /**
     * 保存
     * @param sectionDTO
     * @return
     */
    boolean save(SectionDTO sectionDTO) throws BussinessException;

    /**
     * 修改
     * @param sectionDTO
     * @return
     */
    boolean update(SectionDTO sectionDTO) throws BussinessException;

    /**
     * 删除
     * @param sectionDTO
     * @return
     */
    boolean remove(SectionDTO sectionDTO) throws BussinessException;

    /**
     * 按照课程ID查询章节
     * @param sectionDTO
     * @return
     */
    List<Section> queryAllByCourse(SectionDTO sectionDTO);
}
