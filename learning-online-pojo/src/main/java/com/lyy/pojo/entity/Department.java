package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校院系实体类
 * @author LGX_TvT
 * @date 2020-03-31 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    /**
     * id
     */
    private String id;

    /**
     * 院系名
     */
    private String name;

    /**
     * 状态
     */
    private String state;


}
