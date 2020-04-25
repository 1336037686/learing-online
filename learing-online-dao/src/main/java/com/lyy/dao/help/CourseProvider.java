package com.lyy.dao.help;

import com.lyy.pojo.entity.Course;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-12 21:51
 */
public class CourseProvider {

    /**
     * 更新
     * @return
     */
    public String update(Course course){
        StringBuffer sql=new StringBuffer("update course set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(course.getName())) {
            list.add(" name = #{name}");
        }
        if(course.getTime() != null) {
            list.add(" time = #{time}");
        }
        if(StringUtil.isNotEmpty(course.getIntro())) {
            list.add(" intro = #{intro}");
        }
        if(StringUtil.isNotEmpty(course.getType())) {
            list.add(" type = #{type}");
        }
        if(StringUtil.isNotEmpty(course.getCover())) {
            list.add(" cover = #{cover}");
        }
        if(StringUtil.isNotEmpty(course.getTeacher())) {
            list.add(" teacher = #{teacher}");
        }
        if(StringUtil.isNotEmpty(course.getCheckState())) {
            list.add(" checkState = #{checkState}");
        }
        for (int i = 0; i < list.size(); i++) {
            if(list.size() - 1 == i) {
                sql.append(list.get(i));
            } else {
                sql.append(list.get(i) + ",");
            }
        }
        sql.append("  where id = #{id}");
        return sql.toString();
    }


}
