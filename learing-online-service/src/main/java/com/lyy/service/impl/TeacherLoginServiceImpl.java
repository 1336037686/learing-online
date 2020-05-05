package com.lyy.service.impl;

import com.lyy.dao.TeacherDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.entity.Teacher;
import com.lyy.service.TeacherLoginService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-04-10 16:19
 */
@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 登录
     * @param dto
     * @return
     * @throws BussinessException
     */
    @Override
    public TeacherDTO login(TeacherDTO dto) throws BussinessException {
        try {
            Teacher teacher = teacherDao.queryByUserName(dto.getUserName());
            if(teacher != null && teacher.getPassword().equals(Md5Util.md5(dto.getPassword()))) {
                return converterUtil.copyPropertiesAndReturnNewOne(teacher, TeacherDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_QUERY_FAIL_ERROR, "学生信息查找出错, 登录失败");
        }
        return null;
    }


}
