package com.lyy.dao;

import com.lyy.dao.help.SectionProvider;
import com.lyy.pojo.entity.Section;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-13 20:04
 */
@Mapper
public interface SectionDao {

    /**
     * 保存
     * @param section
     * @return
     */
    @Insert("insert into section values(#{id}, #{course}, #{name}, #{order}, #{intro}, #{state})")
    int save(Section section);

    /**
     * 更新
     * @param section
     * @return
     */
    @UpdateProvider(type = SectionProvider.class, method = "update")
    int update(Section section);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update section set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 按照课程ID查询章节
     * @param course
     * @return
     */
    @Select("SELECT * FROM section WHERE state = 0 AND course = #{course} ORDER BY `order` ASC ")
    List<Section> queryAllByCourse(String course);
}
