package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 教师类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 160950302497489967L;

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 家庭住址
     */
    private String addr;

    /**
     * 院系
     */
    private String colleges;

    /**
     * 邮箱
     */
    private String email;

}