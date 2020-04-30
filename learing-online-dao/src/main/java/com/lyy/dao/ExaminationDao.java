package com.lyy.dao;

import com.lyy.dao.help.ExaminationProvider;
import com.lyy.pojo.entity.Examination;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-30 19:22
 */
@Mapper
public interface ExaminationDao {

    @Insert("insert into examination values(#{id}, #{course}, #{title}, #{content}, #{startTime}, #{endTime}, #{resource}, #{state})")
    int save(Examination examination);

    @Update("update examination set state = 1 where id = #{id}")
    int remove(String id);

    @UpdateProvider(type = ExaminationProvider.class, method = "update")
    int update(Examination examination);

    @Select("select * from examination where state = 0 and course = #{courseId} order by start_time desc")
    List<Examination> queryAllByCourse(String courseId);

}
