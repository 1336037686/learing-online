package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-13 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionQueryVO {

    /**
     * id
     */
    private String id;

    /**
     * 课程
     */
    private String course;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节顺序
     */
    private Integer order;

    /**
     * 章节简介
     */
    private String intro;

    /**
     * 数据条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer currentPage;


}
