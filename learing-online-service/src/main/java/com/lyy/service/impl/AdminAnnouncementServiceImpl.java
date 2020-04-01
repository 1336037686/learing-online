package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.AdminAnnouncementDao;
import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.pojo.dto.AdminAnnouncementDTO;
import com.lyy.pojo.entity.AdminAnnouncement;
import com.lyy.service.AdminAnnouncementService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员公告业务实现类
 * @author LGX_TvT
 * @date 2020-03-28 16:42
 */
@Service
public class AdminAnnouncementServiceImpl implements AdminAnnouncementService {

    @Autowired
    private AdminAnnouncementDao adminAnnouncementDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存管理员公告
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(AdminAnnouncementDTO dto) throws Exception {
        AdminAnnouncement announcement = converterUtil.copyPropertiesAndReturnNewOne(dto, AdminAnnouncement.class);
        adminAnnouncementDao.save(announcement);
        return true;
    }

    /**
     * 查找管理员公告信息
     * @param dto
     * @return
     */
    @Override
    public AdminAnnouncementDTO queryAll(AdminAnnouncementDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<AdminAnnouncement> adminAnnouncements = adminAnnouncementDao.queryAll();
        PageInfo<AdminAnnouncement> pageInfo = new PageInfo<AdminAnnouncement>(adminAnnouncements);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    /**
     * 根据id删除管理员公告信息
     * @param announcementDTO
     * @return
     */
    @Override
    public boolean remove(AdminAnnouncementDTO announcementDTO) {
        adminAnnouncementDao.remove(announcementDTO.getId());
        return true;
    }

    /**
     * 根据条件查找
     * @param dto
     * @return
     */
    @Override
    public AdminAnnouncementConditionDTO queryByCondition(AdminAnnouncementConditionDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<AdminAnnouncement> adminAnnouncements = adminAnnouncementDao.queryByCondition(dto);
        PageInfo<AdminAnnouncement> pageInfo = new PageInfo<>(adminAnnouncements);
        dto.setPageInfo(pageInfo);
        return dto;
    }


}
