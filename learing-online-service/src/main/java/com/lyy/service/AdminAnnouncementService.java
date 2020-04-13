package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.pojo.dto.AdminAnnouncementDTO;

/**
 * 管理员公告业务层接口
 * @author LGX_TvT
 * @date 2020-03-28 16:42
 */
public interface AdminAnnouncementService {

    /**
     * 保存管理员公告
     * @param dto
     * @return
     * @throws Exception
     */
    boolean save(AdminAnnouncementDTO dto)  throws BussinessException;

    /**
     * 分页查找所有公告信息
     * @param dto
     * @return
     */
    AdminAnnouncementDTO queryAll(AdminAnnouncementDTO dto) throws BussinessException;

    /**
     * 删除管理员公告信息
     * @param announcementDTO
     * @return
     */
    boolean remove(AdminAnnouncementDTO announcementDTO) throws BussinessException;

    /**
     * 按照条件查找
     * @param dto
     * @return
     */
    AdminAnnouncementConditionDTO queryByCondition(AdminAnnouncementConditionDTO dto) throws BussinessException;
}
