package com.lyy.service;

import com.lyy.pojo.dto.TeacherDTO;
import com.lyy.pojo.entity.extend.TeacherExtend;

import java.util.List;

/**
 * 教师
 * @author LGX_TvT
 * @date 2020-03-31 22:02
 */
public interface TeacherService {

    /**
     * 保存教师信息
     * @param teacherDTO
     * @return
     */
    boolean save(TeacherDTO teacherDTO);

    /**
     * 查找教师信息
     * @param dto
     * @return
     */
    TeacherDTO queryAll(TeacherDTO dto);

    /**
     * 查找教师信息
     * @return
     */
    List<TeacherExtend> queryAll();


    /**
     * 更新教师信息
     * @param teacherDTO
     * @return
     */
    boolean update(TeacherDTO teacherDTO);

    /**
     * 删除教师信息
     * @param id
     * @return
     */
    boolean remove(String id);

    /**
     * 教师姓名查找
     * @param dto
     * @return
     */
    TeacherDTO queryByName(TeacherDTO dto);

    /**
     * 教师ID查找
     * @param id
     * @return
     */
    TeacherDTO queryById(String id);

    /**
     * 修改密码
     * @param teacherDTO
     * @return
     */
    boolean updatePassword(TeacherDTO teacherDTO);
}
