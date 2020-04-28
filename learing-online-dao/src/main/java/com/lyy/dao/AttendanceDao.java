package com.lyy.dao;

import com.lyy.pojo.entity.Attendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 3:50
 */
@Mapper
public interface AttendanceDao {

    /**
     * 保存
     * @param attendance
     * @return
     */
    @Insert("insert into attendance values (#{id}, #{course}, #{startTime}, #{endTime}, #{state})")
    int save(Attendance attendance);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update attendance set state = 1 where id = #{id}")
    int remove(String id);

    /**
     * 根据课程查询签到记录
     * @param course
     * @return
     */
    @Select("select * from attendance where state = 0 and course = #{course} order by start_time desc")
    List<Attendance> queryAllByCourse(String course);

    /**
     * 根据ID查询签到记录
     * @param id
     * @return
     */
    @Select("select * from attendance where state = 0 and id = #{id}")
    Attendance queryById(String id);

}
