package com.lyy.pojo.dto;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author LGX_TvT
 * @date 2020-03-31 20:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

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
     * 教师名称
     */
    private String teacherName;

    /**
     * 课程类别名称
     */
    private String typeName;

    /**
     * 审核状态
     */
    private String checkState;

    /**
     * 状态
     */
    private String state;

    /**
     * 数据条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * pageInfo
     */
    private PageInfo pageInfo;

}
