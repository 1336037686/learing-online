package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:02:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = -71892834555720879L;
    
    private String id;
    
    private String categoryName;

}