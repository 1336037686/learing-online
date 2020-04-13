package com.lyy.dao;

import com.lyy.dao.help.VideoProvider;
import com.lyy.pojo.entity.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:48
 */
@Mapper
public interface VideoDao {

    /**
     * 保存
     * @param section
     * @return
     */
    @Insert("insert into video values(#{id}, #{course}, #{section}, #{name}, #{addr}, #{order}, #{state})")
    int save(Video section);

    /**
     * 更新
     * @param section
     * @return
     */
    @UpdateProvider(type = VideoProvider.class, method = "update")
    int update(Video section);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update video set state = 1 where id = #{id}")
    int remove(String id);

}
