package com.lyy.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-03-31 15:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

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
