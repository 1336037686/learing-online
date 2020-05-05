package com.lyy.dao;

import com.lyy.dao.help.StudentProvider;
import com.lyy.pojo.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 学生Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface StudentDao {

    /**
     * 保存学生
     * @param student
     * @return
     */
    @Insert("insert into student values(#{id}, #{userName}, #{name}, #{sex}, #{phone}, #{password}, #{specialty}, #{stuClass}, #{address}, #{email}, #{startTime}, #{state})")
    int save(Student student);

    /**
     * 查询所有学生
     * @return
     */
    @Select("select * from student where state = 0")
    List<Student> queryAll();

    /**
     * 按照id查找学生
     * @param id
     * @return
     */
    @Select("select * from student where state = 0 and id = #{id}")
    Student queryById(String id);

    /**
     * 按照学号查找学生
     * @param userName
     * @return
     */
    @Select("select * from student where state = 0 and username = #{userName}")
    Student queryByUserName(String userName);

    /**
     * 更新学生
     * @param student
     * @return
     */
    @UpdateProvider(type = StudentProvider.class, method = "update")
    int update(Student student);

    /**
     * 删除学生
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
