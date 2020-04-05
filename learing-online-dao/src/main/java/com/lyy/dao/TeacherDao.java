package com.lyy.dao;

import com.lyy.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 教师Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface TeacherDao {

    /**
     * 保存
     * @param teacher
     * @return
     */
    @Insert("insert into teacher values(#{id}, #{userName}, #{name}, #{sex}, #{phone}, #{password}, #{address}, #{department}, #{email}, #{state})")
    int save(Teacher teacher);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from teacher where state = 0")
    List<Teacher> queryAll();

    /**
     * 按照id查找
     * @param id
     * @return
     */
    @Select("select * from teacher where state = 0 and id = #{id}")
    Teacher queryById(String id);

    /**
     * 更新
     * @param teacher
     * @return
     */
    @Update("update teacher set name = #{name}, sex = #{sex}, phone = #{phone}, password = #{password}, address = #{address}, department = #{department}, email = #{email} where id = #{id}")
    int update(Teacher teacher);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update teacher set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 按照姓名查找
     * @param name
     * @return
     */
    @Select("select * from teacher where state = 0 and name like CONCAT('%','${name}','%' )")
    List<Teacher> queryByName(String name);
}
