package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:04
 *
 * CREATE TABLE video(
 *      id	VARCHAR(100),
 *      course	VARCHAR(100),
 *      section	VARCHAR(100),
 *      NAME	VARCHAR(500),
 *      addr	VARCHAR(500),
 *      `order`	INT,
 *      state	VARCHAR(10),
 *      PRIMARY KEY(id)
 * )
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

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
     * 状态
     */
    private String state;



}
