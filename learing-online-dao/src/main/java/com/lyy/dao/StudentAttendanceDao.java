package com.lyy.dao;

import com.lyy.pojo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 4:20
 */
@Mapper
public interface StudentAttendanceDao {


    @Select("select student.username,student.name " +
            "from student_course,student " +
            "where student.`id` = student_course.`student` and student_course.`state` = 0 and student_course.`check_state` = 1 and student_course.`student` not in (SELECT student_attendance.`student` FROM student_attendance,attendance WHERE student_attendance.state = 0 AND student_attendance.`attendance` = attendance.`id` AND student_attendance.`attendance` = #{attendanceId})")
    List<Student> queryInfo(String attendanceId);
}
