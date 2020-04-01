package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.SpecialtyDao;
import com.lyy.pojo.dto.SpecialtyDTO;
import com.lyy.pojo.entity.Specialty;
import com.lyy.service.SpecialtyService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-31 22:04
 */
@Service
public class StudentServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyDao specialtyDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存
     * @param specialtyDTO
     * @return
     */
    @Override
    public boolean save(SpecialtyDTO specialtyDTO) {
        specialtyDao.save(new Specialty(specialtyDTO.getId(), specialtyDTO.getDepId(), specialtyDTO.getName(), specialtyDTO.getState()));
        return true;
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public SpecialtyDTO queryAll(SpecialtyDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<Specialty> specialtyList = specialtyDao.queryAll();
        PageInfo<Specialty> pageInfo = new PageInfo<Specialty>(specialtyList);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    /**
     * 更新
     * @param specialtyDTO
     * @return
     */
    @Override
    public boolean update(SpecialtyDTO specialtyDTO) {
        specialtyDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(specialtyDTO, Specialty.class));
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean remove(String id) {
        specialtyDao.remove(id);
        return true;
    }
}
