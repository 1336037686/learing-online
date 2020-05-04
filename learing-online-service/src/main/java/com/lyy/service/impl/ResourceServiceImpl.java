package com.lyy.service.impl;

import com.lyy.dao.ResourceDao;
import com.lyy.dao.SectionDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.ResourceDTO;
import com.lyy.pojo.entity.Resource;
import com.lyy.pojo.entity.Section;
import com.lyy.service.ResourceService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-04-27 22:24
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private SectionDao sectionDao;

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

    @Override
    public List<Resource> queryNewest(Integer num) {
        try {
            List<Resource> result = resourceDao.queryNewest(num);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_RESOURCE_QUERY_FAIL_ERROR, "资源信息查找失败");
        }
    }

    /**
     * 根据课程查找资源列表
     * @param resourceDTO
     * @return
     */
    @Override
    public Map<String, Object> queryMapByCourse(ResourceDTO resourceDTO) {
        try {
            Map<String, Object> map = new HashMap<>();
            List<Section> sections = sectionDao.queryAllByCourse(resourceDTO.getCourse());
            for (Section section : sections) {
                Resource resource = new Resource();
                resource.setCourse(resourceDTO.getCourse());
                resource.setSection(section.getId());
                List<Resource> resources = resourceDao.queryAllByCourseAndSection(resource);
                if(resources.size() > 0) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id", section.getId());
                    m.put("list", resources);
                    map.put(section.getName(), m);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_RESOURCE_QUERY_FAIL_ERROR, "资源信息查找失败");
        }
    }
}
