package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 院系专业实体类
 * @author LGX_TvT
 * @date 2020-03-31 20:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialty {

    /**
     * id
     */
    private String id;

    /**
     * 院系名称
     */
    private String depId;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 状态
     */
    private String state;

}
