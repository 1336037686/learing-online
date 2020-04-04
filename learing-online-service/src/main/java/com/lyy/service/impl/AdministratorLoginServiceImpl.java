package com.lyy.service.impl;

import com.lyy.dao.AdministratorDao;
import com.lyy.pojo.dto.AdministratorDTO;
import com.lyy.pojo.entity.Administrator;
import com.lyy.service.AdministratorLoginService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-04-02 15:53
 */
@Service
public class AdministratorLoginServiceImpl implements AdministratorLoginService {

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 登陆
     * @param dto
     * @return
     */
    @Override
    public AdministratorDTO Login(AdministratorDTO dto) {
        Administrator administrator = administratorDao.queryByUserName(dto.getUserName());
        AdministratorDTO administratorDTO = converterUtil.copyPropertiesAndReturnNewOne(administrator, AdministratorDTO.class);
        if(Md5Util.md5(dto.getPassword()).equals(administrator.getPassword())) {
            return administratorDTO;
        }
        return null;
    }
}
