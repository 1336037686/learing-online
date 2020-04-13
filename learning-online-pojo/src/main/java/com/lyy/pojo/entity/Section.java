package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程章节
 * @author LGX_TvT
 * @date 2020-04-13 16:41
 *
 * CREATE TABLE section(
 *      id	VARCHAR(100),
 *      course	VARCHAR(100),
 *      `name`	VARCHAR(100),
 *      `order`   INT,
 *      intro	VARCHAR(200),
 *      state	VARCHAR(10),
 *      PRIMARY KEY(id)
 * )
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

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
     * 状态
     */
    private String state;

}
