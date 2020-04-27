package com.lyy.service.impl;

import com.lyy.dao.ResourceDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ResourceDTO;
import com.lyy.pojo.entity.Resource;
import com.lyy.service.ResourceService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-27 22:24
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 新增
     * @param resourceDTO
     * @return
     */
    @Override
    public boolean save(ResourceDTO resourceDTO) throws BussinessException {
        try {
            Resource resource = converterUtil.copyPropertiesAndReturnNewOne(resourceDTO, Resource.class);
            int result = resourceDao.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_RESOURCE_SAVE_FAIL_ERROR, "资源信息保存失败");
        }
        return true;
    }

    /**
     * 删除
     * @param resourceDTO
     * @return
     */
    @Override
    public boolean remove(ResourceDTO resourceDTO) throws BussinessException {
        try {
            int result = resourceDao.remove(resourceDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_RESOURCE_DELETE_FAIL_ERROR, "资源信息删除失败");
        }
        return true;
    }

    /**
     * 修改
     * @param resourceDTO
     * @return
     */
    @Override
    public List<Resource> queryAllByCourseAndSection(ResourceDTO resourceDTO) throws BussinessException {
        try {
            Resource resource = converterUtil.copyPropertiesAndReturnNewOne(resourceDTO, Resource.class);
            List<Resource> result = resourceDao.queryAllByCourseAndSection(resource);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_RESOURCE_QUERY_FAIL_ERROR, "资源信息查找失败");
        }
    }
}
