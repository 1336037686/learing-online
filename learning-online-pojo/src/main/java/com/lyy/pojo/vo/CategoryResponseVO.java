package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseVO {

    /**
     * 类别ID
     */
    private String id;

    /**
     * 类别名称
     */
    private String categoryName;

}
