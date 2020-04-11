package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.TeacherDTO;

/**
 * @author LGX_TvT
 * @date 2020-04-10 16:16
 */
public interface TeacherLoginService {

    /**
     * 教师登陆
     * @param dto
     * @return
     */
    TeacherDTO login(TeacherDTO dto) throws BussinessException;

}
