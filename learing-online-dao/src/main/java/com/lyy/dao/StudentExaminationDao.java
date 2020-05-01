package com.lyy.dao;

import com.lyy.dao.help.StudentExaminationProvider;
import com.lyy.pojo.entity.StudentExamination;
import com.lyy.pojo.entity.extend.StudentExaminationExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-05-01 11:48
 */
@Mapper
public interface StudentExaminationDao {

    /**
     * 试卷ID查找提交的试卷信息
     * @param examId
     * @return
     */
    @Select("SELECT student_examination.*, student.`username` AS 'studentUserName', student.`name` AS 'studentName' FROM student_examination,student WHERE student_examination.state = 0 AND student_examination.`student` = student.`id` AND student_examination.`examination` = #{examId}")
    List<StudentExaminationExtend> queryStudentExamByExamId(String examId);

    /**
     * 根据课程ID以及试卷ID查找未提交试卷的学生信息
     * @param examId
     * @return
     */
    @Select("SELECT student.`username` AS 'studentUserName', student.`name` AS 'studentName' FROM student_course, student WHERE student_course.state = '0' AND student_course.`student` = student.`id` AND student_course.`course` = #{courseId} AND student_course.`check_state` = '1' AND student_course.`student` NOT IN (SELECT student_examination.`student` FROM student_examination WHERE student_examination.state = '0' AND student_examination.`examination` = #{examId})")
    List<StudentExaminationExtend> queryMissStudentExamByCourseAndExam(String courseId, String examId);


    /**
     * 更新学生考试信息
     * @param studentExamination
     * @return
     */
    @UpdateProvider(type = StudentExaminationProvider.class, method = "update")
    int update(StudentExamination studentExamination);
}
