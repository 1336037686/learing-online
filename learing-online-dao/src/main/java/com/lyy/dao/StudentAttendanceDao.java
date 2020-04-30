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


    @Select("SELECT student.username, student.name FROM student_course,student WHERE student_course.`check_state` = '1' AND student_course.`state` = '0' AND student_course.`student` = student.`id` AND student_course.`course` = (SELECT course FROM attendance WHERE id = #{attendanceId} AND state = '0') AND student.`id` NOT IN (SELECT student_attendance.`student` FROM student_attendance WHERE student_attendance.`state` = '0' AND student_attendance.`attendance` = #{attendanceId})")
    List<Student> queryInfo(String attendanceId);
}
