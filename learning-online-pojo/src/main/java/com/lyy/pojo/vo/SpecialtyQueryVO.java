package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LGX_TvT
 * @date 2020-03-31 20:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyQueryVO {

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

    /**
     * 数据条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer currentPage;


}
