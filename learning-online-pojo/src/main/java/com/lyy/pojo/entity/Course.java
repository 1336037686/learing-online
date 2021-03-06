package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Course)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:02:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = -61339672738466706L;

    /**
     * id， 课程号
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 时间
     */
    private Date time;

    /**
     * 课程简介
     */
    private String intro;

    /**
     * 类型
     */
    private String type;

    /**
     * 课程封面
     */
    private String cover;

    /**
     * 负责老师
     */
    private String teacher;

    /**
     * 审核状态
     */
    private String checkState;

    /**
     * 状态
     */
    private String state;
}