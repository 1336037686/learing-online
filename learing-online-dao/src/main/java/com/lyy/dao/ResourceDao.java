package com.lyy.dao;

import com.lyy.dao.help.ResourceProvider;
import com.lyy.pojo.entity.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-27 21:03
 */
@Mapper
public interface ResourceDao {

    /**
     * 保存
     * @param resource
     * @return
     */
    @Insert("insert into resource values(#{id}, #{course}, #{section}, #{name}, #{addr}, #{state})")
    int save(Resource resource);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("update resource set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 更新
     * @param resource
     * @return
     */
    @UpdateProvider(type = ResourceProvider.class, method = "update")
    int update(Resource resource);

    /**
     * 查询全部
     * @param resource
     * @return
     */
    @Select("select * from resource where state = 0 and course = #{course} and section = #{section}")
    List<Resource> queryAllByCourseAndSection(Resource resource);


    /**
     * 查找最新资源
     * @param num
     * @return
     */
    @Select("select * from resource where state = 0 limit #{num}")
    List<Resource> queryNewest(Integer num);
}