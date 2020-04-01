package com.lyy.dao;

import com.lyy.pojo.entity.Department;
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
public interface DepartmentDao {

    /**
     * 保存类别
     * @param category
     * @return
     */
    @Insert("insert into department values(#{id}, #{name}, #{state})")
    int save(Department category);

    /**
     * 查询所有类别
     * @return
     */
    @Select("select * from department where state = 0")
    List<Department> queryAll();

    /**
     * 按照id查找类别
     * @param id
     * @return
     */
    @Select("select * from department where state = 0 and id = #{id}")
    Department queryById(String id);

    /**
     * 更新类别
     * @param department
     * @return
     */
    @Update("update department set name = #{name} where id = #{id}")
    int update(Department department);

    /**
     * 删除类别
     * @param id
     * @return
     */
    @Update("update department set state = 1 where id = #{id}")
    int remove(String id);

}
