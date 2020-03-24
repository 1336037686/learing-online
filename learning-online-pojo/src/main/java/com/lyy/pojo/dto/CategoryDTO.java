package com.lyy.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-03-24 17:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    /**
     * 类别ID
     */
    private String id;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 状态
     */
    private String state;


}
