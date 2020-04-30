package com.lyy.dao;

import com.lyy.dao.help.StudentJobProvider;
import com.lyy.pojo.entity.StudentJob;
import com.lyy.pojo.entity.extend.StudentJobExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 16:16
 */
@Mapper
public interface StudentJobDao {


    /**
     * 作业ID查找提交的作业信息
     * @param jobId
     * @return
     */
    @Select("SELECT student_job.*, student.`username` AS 'studentUserName', student.`name` AS 'studentName' FROM student_job,student WHERE student_job.state = 0 AND student_job.`student` = student.`id` AND student_job.`course_job` = #{jobId}")
    List<StudentJobExtend> queryStudentJobByJobId(String jobId);

    /**
     * 根据课程查找未提交作业的学生信息
     * @param jobId
     * @return
     */
    @Select("SELECT student.`username` AS 'studentUserName', student.`name` AS 'studentName' " +
            "FROM student_course, student " +
            "WHERE student_course.state = '0' AND student_course.`student` = student.`id` AND student_course.`course` = #{courseId} AND student_course.`check_state` = '1' AND student_course.`student` NOT IN (SELECT student_job.`student` " +
            "FROM student_job WHERE student_job.state = '0' AND student_job.`course_job` = #{jobId})")
    List<StudentJobExtend> queryMissStudentJobByCourseAndJob(String courseId, String jobId);

    /**
     * 更新学生作业信息
     * @param studentJob
     * @return
     */
    @UpdateProvider(type = StudentJobProvider.class, method = "update")
    int update(StudentJob studentJob);
}
