package com.lyy.dao;

import com.lyy.pojo.entity.Specialty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 院系Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface SpecialtyDao {

    /**
     * 保存专业
     * @param specialty
     * @return
     */
    @Insert("insert into specialty values(#{id}, #{depId}, #{name}, #{state})")
    int save(Specialty specialty);

    /**
     * 查询所有专业
     * @return
     */
    @Select("select * from specialty where state = 0")
    List<Specialty> queryAll();

    /**
     * 按照id查找专业
     * @param id
     * @return
     */
    @Select("select * from specialty where state = 0 and id = #{id}")
    Specialty queryById(String id);

    /**
     * 更新专业
     * @param specialty
     * @return
     */
    @Update("update specialty set name = #{name}, dep_id = #{depId} where id = #{id}")
    int update(Specialty specialty);

    /**
     * 删除专业
     * @param id
     * @return
     */
    @Update("update specialty set state = 1 where id = #{id}")
    int remove(String id);

}
