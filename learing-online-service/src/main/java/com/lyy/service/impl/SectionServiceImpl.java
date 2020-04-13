package com.lyy.service.impl;

import com.lyy.dao.SectionDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.SectionDTO;
import com.lyy.pojo.entity.Section;
import com.lyy.service.SectionService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程章节业务层
 * @author LGX_TvT
 * @date 2020-04-13 20:03
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存
     * @param sectionDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean save(SectionDTO sectionDTO) throws BussinessException {
        try {
            Section section = converterUtil.copyPropertiesAndReturnNewOne(sectionDTO, Section.class);
            int result = sectionDao.save(section);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_SECTION_SAVE_FAIL_ERROR, "专业信息保存失败");
        }
        return true;
    }

    /**
     * 更新
     * @param sectionDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean update(SectionDTO sectionDTO) throws BussinessException {
        try {
            Section section = converterUtil.copyPropertiesAndReturnNewOne(sectionDTO, Section.class);
            int result = sectionDao.update(section);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_SECTION_UPDATE_FAIL_ERROR, "专业信息更新失败");
        }
        return true;
    }

    /**
     * 删除
     * @param sectionDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean remove(SectionDTO sectionDTO) throws BussinessException {
        try {
            int result = sectionDao.remove(sectionDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_SECTION_DELETE_FAIL_ERROR, "专业信息删除失败");
        }
        return true;
    }
}
