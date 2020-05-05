package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentDTO;

/**
 * @author LGX_TvT
 * @date 2020-05-05 14:55
 */
public interface StudentLoginService {
    StudentDTO login(StudentDTO dto) throws BussinessException;
}
