package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-27 21:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    /**
     * id
     */
    private String id;

    /**
     * 课程名称
     */
    private String course;

    /**
     * 章节
     */
    private String section;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源地址
     */
    private String addr;

    /**
     * 状态
     */
    private String state;

}
