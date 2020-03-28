package com.lyy.dao;

import com.lyy.dao.help.AdminAnnouncementProvider;
import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.pojo.entity.AdminAnnouncement;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 管理员公告Dao
 * @author LGX_TvT
 * @date 2020-03-28 16:43
 */
@Mapper
public interface AdminAnnouncementDao {

    /**
     * 保存管理员公告
     * @param adminAnnouncement
     * @return
     */
    @Insert("insert into admin_announcement values(#{id}, #{title}, #{content}, #{time}, #{state})")
    int save(AdminAnnouncement adminAnnouncement);

    /**
     * 查询所有管理员公告
     * @return
     */
    @Select("select * from admin_announcement where state = 0")
    List<AdminAnnouncement> queryAll();

    /**
     * 按照id查找管理员公告
     * @param id
     * @return
     */
    @Select("select * from admin_announcement where state = 0 and id = #{id}")
    AdminAnnouncement queryById(String id);

    /**
     * 删除管理员公告
     * @param id
     * @return
     */
    @Update("update admin_announcement set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 根据条件查找
     * @return
     */
    @SelectProvider(type = AdminAnnouncementProvider.class,method = "queryByCondition") //多条件关键注释
    List<AdminAnnouncement> queryByCondition(AdminAnnouncementConditionDTO adminAnnouncementConditionDTO);


}
