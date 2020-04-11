package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.TeacherDao;
import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.entity.Teacher;
import com.lyy.pojo.entity.extend.TeacherExtend;
import com.lyy.service.TeacherService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-31 22:04
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存
     * @param teacherDTO
     * @return
     */
    @Override
    public boolean save(TeacherDTO teacherDTO) {
        Teacher teacher = converterUtil.copyPropertiesAndReturnNewOne(teacherDTO, Teacher.class);
        teacherDao.save(teacher);
        return true;
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public TeacherDTO queryAll(TeacherDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<TeacherExtend> teacherList = teacherDao.queryAll();
        PageInfo<TeacherExtend> pageInfo = new PageInfo<TeacherExtend>(teacherList);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    @Override
    public List<TeacherExtend> queryAll() {
        return teacherDao.queryAll();
    }

    /**
     * 更新
     * @param teacherDTO
     * @return
     */
    @Override
    public boolean update(TeacherDTO teacherDTO) {
        teacherDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(teacherDTO, Teacher.class));
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean remove(String id) {
        teacherDao.remove(id);
        return true;
    }

    /**
     * 按照姓名查找
     * @param dto
     * @return
     */
    @Override
    public TeacherDTO queryByName(TeacherDTO dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        List<Teacher> teacherList = teacherDao.queryByName(dto.getName());
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
        dto.setPageInfo(pageInfo);
        return dto;
    }

    /**
     * 按照id 查找
     * @param id
     * @return
     */
    @Override
    public TeacherDTO queryById(String id) {
        TeacherExtend teacher = teacherDao.queryById(id);
        teacher.setPassword(null);
        return converterUtil.copyPropertiesAndReturnNewOne(teacher, TeacherDTO.class);
    }

    /**
     * 修改密码
     * @param teacherDTO
     * @return
     */
    @Override
    public boolean updatePassword(TeacherDTO teacherDTO) {
        teacherDao.updatePassword(teacherDTO.getId(), teacherDTO.getPassword());
        return true;
    }
}
