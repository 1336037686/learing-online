package com.lyy.pojo.dto;

import com.github.pagehelper.PageInfo;
import com.lyy.pojo.entity.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-29 0:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseDTO extends StudentCourse {

    /**
     * 学号
     */
    private String username;

    /**
     * 名字
     */
    private String name;

    /**
     * 院系
     */
    private String department;

    /**
     * 专业
     */
    private String specialty;

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
