package com.lyy.service.impl;

import com.lyy.dao.StudentDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.StudentDTO;
import com.lyy.pojo.entity.Student;
import com.lyy.service.StudentLoginService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-05-05 14:57
 */
@Service
public class StudentLoginServiceImpl implements StudentLoginService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ConverterUtil converterUtil;

    @Override
    public StudentDTO login(StudentDTO dto) throws BussinessException {
        try {
            Student student = studentDao.queryByUserName(dto.getUserName());
            if(student != null && student.getPassword().equals(Md5Util.md5(dto.getPassword()))) {
                return converterUtil.copyPropertiesAndReturnNewOne(student, StudentDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_STUDENT_QUERY_FAIL_ERROR, "学生信息查找出错, 登录失败");
        }
        return null;
    }
}
