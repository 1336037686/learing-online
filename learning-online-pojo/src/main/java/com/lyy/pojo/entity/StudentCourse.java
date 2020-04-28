package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LGX_TvT
 * @date 2020-04-29 0:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    /**
     * id
     */
    private String id;

    /**
     * 课程
     */
    private String course;

    /**
     * 学生
     */
    private String student;

    /**
     * 时间
     */
    private Date time;

    /**
     * 审核状态
     */
    private String checkState;

    /**
     * 状态
     */
    private String state;

}
