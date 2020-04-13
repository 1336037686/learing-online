package com.lyy.dao;

import com.lyy.dao.help.CourseProvider;
import com.lyy.pojo.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 课程信息Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface CourseDao {

    /**
     * 保存
     * @param course
     * @return
     */
    @Insert("insert into course values(#{id}, #{name}, #{time}, #{intro}, #{type}, #{cover}, #{teacher}, #{checkState}, #{state})")
    int save(Course course);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from course where state = 0")
    List<Course> queryAll();

    /**
     * 查询所有
     * @return
     */
    @Select("select * from course where state = 0 and checkState = 0")
    List<Course> queryAllAndNotCheck();


    /**
     * 按照id查找
     * @param id
     * @return
     */
    @Select("select * from course where state = 0 and id = #{id}")
    Course queryById(String id);

    /**
     * 按照教师id查找
     * @param teacher
     * @return
     */
    @Select("select * from course where state = 0 and teacher = #{teacher}")
    List<Course> queryByTeacher(String teacher);

    /**
     * 更新
     * @param course
     * @return
     */
    @UpdateProvider(type = CourseProvider.class, method = "update")
    int update(Course course);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update course set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 按照课程名称模糊查找
     * @param name
     * @return
     */
    @Select("select * from course where state = 0 and name like CONCAT('%','${name}','%' )")
    List<Course> queryByName(String name);



}
