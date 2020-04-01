package com.lyy.pojo.vo;

import com.github.pagehelper.PageInfo;
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
public class TeacherResponseVO {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

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
    private String password;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 院系
     */
    private String department;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 数据条数
     */
    private Integer size;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * pageInfo
     */
    private PageInfo pageInfo;

}
