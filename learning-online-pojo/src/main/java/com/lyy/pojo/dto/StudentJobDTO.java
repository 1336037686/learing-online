package com.lyy.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentJobDTO {

    /**
     * id
     */
    private String id;

    /**
     * 作业
     */
    private String courseJob;

    /**
     * 学生
     */
    private String student;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件
     */
    private String resource;

    /**
     * 上交时间
     */
    private Date time;

    /**
     * 检查状态
     */
    private String checkState;

    /**
     * 教师评分
     */
    private Double score;

    /**
     * 教师评语
     */
    private String evaluate;

    /**
     * 状态
     */
    private String state;


}
