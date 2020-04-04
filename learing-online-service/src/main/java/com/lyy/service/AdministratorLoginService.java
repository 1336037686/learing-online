package com.lyy.service;

import com.lyy.pojo.dto.AdministratorDTO;

/**
 * @author LGX_TvT
 * @date 2020-04-02 15:51
 */
public interface AdministratorLoginService {

    /**
     * 登陆
     * @param dto
     * @return
     */
    AdministratorDTO Login(AdministratorDTO dto);



}
