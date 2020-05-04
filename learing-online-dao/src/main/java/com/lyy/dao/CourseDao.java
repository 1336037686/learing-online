package com.lyy.dao;

import com.lyy.dao.help.CourseProvider;
import com.lyy.pojo.entity.Course;
import com.lyy.pojo.entity.extend.CourseExtend;
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
    @Select("SELECT course.*, teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,teacher,category WHERE category.id = course.type AND teacher.id = course.teacher AND course.state = 0")
    List<CourseExtend> queryAll();

    /**
     * 查询所有
     * @return
     */
    @Select("select * from course where state = 0 and check_state = 0")
    List<Course> queryAllAndNotCheck();


    /**
     * 按照id查找
     * @param id
     * @return
     */
    @Select("SELECT course.*, teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,teacher,category where category.id = course.type AND teacher.id = course.teacher AND course.state = 0 AND course.id = #{id}")
    CourseExtend queryById(String id);

    /**
     * 按照type查找相应数量的课程
     * @param type
     * @return
     */
    @Select("select course.id,course.name,course.cover,course.time,teacher.name as 'teacher' from course,teacher where course.teacher = teacher.id and course.state = '0' and course.check_state = '1' and course.type = #{type} limit #{num}")
    List<Course> queryByTypeAndNum(String type, Integer num);


    /**
     * 按照type查找
     * @param type
     * @return
     */
    @Select("select course.id,course.name,course.cover,course.time,teacher.name as 'teacher' from course,teacher where course.teacher = teacher.id and course.state = '0' and course.check_state = '1' and course.type = #{type}")
    List<Course> queryByType(String type);

    /**
     * 按照教师id查找
     * @param teacher
     * @return
     */
    @Select("SELECT course.*, teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,teacher,category " +
            "WHERE category.id = course.type AND teacher.id = course.teacher AND course.state = 0 AND course.teacher = #{teacher}")
    List<CourseExtend> queryByTeacher(String teacher);

    /**
     * 按照教师id查找所有审核通过
     * @param teacher
     * @return
     */
    @Select("SELECT course.*, teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,teacher,category " +
            "WHERE category.id = course.type AND teacher.id = course.teacher AND course.check_state = 1 AND course.state = 0 AND course.teacher = #{teacher}")
    List<CourseExtend> queryAllPassByTeacher(String teacher);


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
    @Select("SELECT course.*, teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,teacher,category WHERE category.id = course.type AND teacher.id = course.teacher AND course.state = 0 and course.name like CONCAT('%','${name}','%' )")
    List<CourseExtend> queryByName(String name);


    /**
     * 查找人气课程
     * @param num
     * @return
     */
    @Select("SELECT course.*, COUNT(student_course.`id`),teacher.name AS 'teacherName', category.category_name AS 'typeName' FROM course,student_course,teacher,category WHERE course.`teacher` = teacher.`id` AND category.`id` = course.`type` AND course.`id` = student_course.`course` AND course.state = '0' GROUP BY course.`id` ORDER BY COUNT(student_course.`id`) DESC LIMIT #{num}")
    List<CourseExtend> queryMoodsCourse(Integer num);
}
