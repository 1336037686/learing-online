package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 课程信息中间表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCourse implements Serializable {
    private static final long serialVersionUID = -60899624031138502L;

    /**
     * id
     */
    private String id;

    /**
     * 课程号
     */
    private String courseId;

    /**
     * 教师id
     */
    private String teacherId;


}