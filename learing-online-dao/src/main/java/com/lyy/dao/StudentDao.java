package com.lyy.dao;

import com.lyy.pojo.entity.Student;
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
public interface StudentDao {

    /**
     * 保存专业
     * @param student
     * @return
     */
    @Insert("insert into student values(#{id}, #{userName}, #{name}, #{sex}, #{phone}, #{password}, #{specialty}, #{stuClass}, #{address}, #{email}, #{startTime}, #{state})")
    int save(Student student);

    /**
     * 查询所有专业
     * @return
     */
    @Select("select * from student where state = 0")
    List<Student> queryAll();

    /**
     * 按照id查找专业
     * @param id
     * @return
     */
    @Select("select * from student where state = 0 and id = #{id}")
    Student queryById(String id);

    /**
     * 更新专业
     * @param student
     * @return
     */
    @Update("update student set name = #{name}, sex = #{sex}, phone = #{phone}, password = #{password}, specialty = #{specialty}, stu_class = #{stuClass}, address = #{address}, email = #{email}, start_time = #{startTime} where id = #{id}")
    int update(Student student);

    /**
     * 删除专业
     * @param id
     * @return
     */
    @Update("update student set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 按照姓名查找
     * @param name
     * @return
     */
    @Select("select * from student where state = 0 and name like CONCAT('%','${name}','%' )")
    List<Student> queryByName(String name);
}
