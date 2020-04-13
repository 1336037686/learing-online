package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoQueryVO {
    /**
     * id
     */
    private String id;

    /**
     * 课程
     */
    private String course;

    /**
     * 专业
     */
    private String section;

    /**
     * 名称
     */
    private String name;

    /**
     * URL地址
     */
    private String addr;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 数据条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer currentPage;
}
