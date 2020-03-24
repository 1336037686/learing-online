package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Administrator)实体类
 *
 * @author makejava
 * @since 2020-03-24 00:30:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator implements Serializable {
    private static final long serialVersionUID = -38982421546657014L;
    
    private String id;

    private String userName;
    
    private String passwd;
    
    private String phone;
    
    private String email;

}