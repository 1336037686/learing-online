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

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String state;

}