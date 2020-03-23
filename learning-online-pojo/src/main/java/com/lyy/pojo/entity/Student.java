package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:02:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -88982781248507656L;
    
    private String id;
    
    private String name;
    
    private String sex;
    
    private String phone;
    
    private String passwd;
    
    private String major;
    
    private String stuClass;
    
    private String addr;


}