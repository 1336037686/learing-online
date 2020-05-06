package com.lyy.dao;

import com.lyy.dao.help.StudentCourseProvider;
import com.lyy.pojo.entity.StudentCourse;
import com.lyy.pojo.entity.extend.CourseExtend;
import com.lyy.pojo.entity.extend.StudentCourseExtend;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:38
 */
@Mapper
public interface StudentCourseDao {

    /**
     * 添加选课信息
     * @param studentCourse
     * @return
     */
    @Insert("insert into student_course values(#{id}, #{course}, #{student}, #{time}, #{checkState}, #{state})")
    int save(StudentCourse studentCourse);

    /**
     * 删除选课信息
     * @param id
     * @return
     */
    @Update("update student_course set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 修改选课信息
     * @param studentCourse
     * @return
     */
    @UpdateProvider(type = StudentCourseProvider.class, method = "update")
    int update(StudentCourse studentCourse);

    /**
     * 根据课程查找选课信息
     * @param course
     * @return
     */
    @Select("SELECT student_course.*, student.`username` AS 'username', student.`name` AS 'name', student.`email` AS 'email', department.`name` AS 'department', specialty.`name` AS  'specialty' " +
            "FROM student_course, student, department, specialty " +
            "WHERE student_course.`state` = 0 AND student_course.`student` = student.`id` AND student.`specialty` = specialty.`id` AND specialty.`dep_id` = department.`id` AND student_course.`course` = #{course} " +
            "ORDER BY student_course.`time` DESC")
    List<StudentCourseExtend> queryAllByCourse(String course);

    /**
     * 根据课程和学生ID查找选课
     * @param course
     * @param student
     * @return
     */
    @Select("select * from student_course where course = #{course} and student = #{student} and state = 0 and check_state != '2'")
    StudentCourse queryByCourseAndStudent(String course, String student);

    /**
     * 根据学生ID查询课程
     * 此check_state为学生检查
     * @param id
     * @return
     */
    @Select("SELECT  course.id,course.name,course.time,course.`intro`,course.`type`,course.`cover`,course.`teacher`,student_course.`check_state`, course.state, teacher.name AS 'teacherName', category.category_name AS 'typeName'  FROM course,student_course,teacher,category WHERE course.`type` = category.`id` AND category.`state` = '0' AND course.`state` = 0 AND student_course.`state` = '0' AND teacher.`state` = '0' AND course.`teacher` = teacher.`id` AND student_course.`course` = course.`id` AND student_course.`student` = #{id}")
    List<CourseExtend> queryByStudent(String id);
}
