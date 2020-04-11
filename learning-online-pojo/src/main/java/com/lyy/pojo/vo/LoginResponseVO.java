package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseVO {

    /**
     * id
     */
    private String id;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * token
     */
    private String token;

}
