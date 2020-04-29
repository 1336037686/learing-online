package com.lyy.dao;

import com.lyy.dao.help.CourseJobProvider;
import com.lyy.pojo.entity.CourseJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:00
 */
@Mapper
public interface CourseJobDao {

    @Insert("insert into course_job values(#{id}, #{course}, #{title}, #{content}, #{resource}, #{startTime}, #{endTime}, #{state})")
    int save(CourseJob courseJob);

    @Update("update course_job set state = 1 where id = #{id}")
    int remove(String id);

    @UpdateProvider(type = CourseJobProvider.class, method = "update")
    int update(CourseJob courseJob);

    @Select("select * from course_job where state = 0 and course = #{courseId}")
    List<CourseJob> queryAllByCourse(String courseId);
}
