package com.lyy.dao;

import com.lyy.dao.help.TeacherProvider;
import com.lyy.pojo.entity.Teacher;
import com.lyy.pojo.entity.extend.TeacherExtend;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 教师Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface TeacherDao {

    /**
     * 保存
     * @param teacher
     * @return
     */
    @Insert("insert into teacher values(#{id}, #{userName}, #{name}, #{sex}, #{phone}, #{password}, #{address}, #{department}, #{email}, #{state})")
    int save(Teacher teacher);

    /**
     * 查询所有
     * @return
     */
    @Select("SELECT teacher.*,department.name AS 'departmentName' FROM teacher,department WHERE department.id = teacher.department AND teacher.state = 0")
    List<TeacherExtend> queryAll();

    /**
     * 按照id查找
     * @param id
     * @return
     */
    @Select("SELECT teacher.*,department.name AS 'departmentName' FROM teacher,department WHERE department.id = teacher.department AND teacher.state = 0 AND teacher.id = #{id}")
    TeacherExtend queryById(String id);

    /**
     * 更新
     * @param teacher
     * @return
     */
    @UpdateProvider(type = TeacherProvider.class, method = "update")
    int update(Teacher teacher);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update teacher set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 按照姓名查找
     * @param name
     * @return
     */
    @Select("select * from teacher where state = 0 and name like CONCAT('%','${name}','%' )")
    List<Teacher> queryByName(String name);

    /**
     * 按照账号查找
     * @param userName
     * @return
     */
    @Select("select * from teacher where state = 0 and username = #{userName}")
    Teacher queryByUserName(String userName);

    /**
     * 修改密码
     * @param id
     * @return
     */
    @Update("update teacher set password = #{password} where id = #{id}")
    int updatePassword(String id, String password);
}
