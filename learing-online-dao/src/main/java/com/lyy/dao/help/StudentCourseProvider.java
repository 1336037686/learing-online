package com.lyy.dao.help;

import com.lyy.pojo.entity.StudentCourse;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:42
 */
public class StudentCourseProvider {

    /**
     * 更新
     * @return
     */
    public String update(StudentCourse studentCourse){
        StringBuffer sql=new StringBuffer("update student_course set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(studentCourse.getCourse())) {
            list.add(" `course` = #{course}");
        }
        if(StringUtil.isNotEmpty(studentCourse.getStudent())) {
            list.add(" `student` = #{student}");
        }
        if(studentCourse.getTime() != null) {
            list.add(" `time` = #{time}");
        }
        if(StringUtil.isNotEmpty(studentCourse.getCheckState())) {
            list.add(" `check_state` = #{checkState}");
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
