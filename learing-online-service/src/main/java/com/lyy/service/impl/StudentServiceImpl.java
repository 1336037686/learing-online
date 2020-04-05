package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.StudentDao;
import com.lyy.pojo.dto.StudentDTO;
import com.lyy.pojo.entity.Student;
import com.lyy.service.StudentService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-31 22:04
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存
     * @param studentDTO
     * @return
     */
    @Override
    public boolean save(StudentDTO studentDTO) {
        Student student = converterUtil.copyPropertiesAndReturnNewOne(studentDTO, Student.class);
        studentDao.save(student);
        return true;
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public StudentDTO queryAll(StudentDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<Student> studentList = studentDao.queryAll();
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    /**
     * 更新
     * @param studentDTO
     * @return
     */
    @Override
    public boolean update(StudentDTO studentDTO) {
        studentDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(studentDTO, Student.class));
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean remove(String id) {
        studentDao.remove(id);
        return true;
    }

    /**
     * 条件查找
     * @param dto
     * @return
     */
    @Override
    public StudentDTO queryByName(StudentDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<Student> studentList = studentDao.queryByName(dto.getName());
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
        dto.setPageInfo(pageInfo);
        return dto;
    }
}
