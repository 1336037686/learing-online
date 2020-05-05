package com.lyy.dao.help;

import com.lyy.pojo.entity.Student;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 2:42
 */
public class StudentProvider {

    /**
     * 更新
     * @return
     */
    public String update(Student student){
        StringBuffer sql=new StringBuffer("update student set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(student.getName())) {
            list.add(" `name` = #{name}");
        }
        if(StringUtil.isNotEmpty(student.getSex())) {
            list.add(" `sex` = #{sex}");
        }
        if(StringUtil.isNotEmpty(student.getPhone())) {
            list.add(" `phone` = #{phone}");
        }
        if(StringUtil.isNotEmpty(student.getPassword())) {
            list.add(" `password` = #{password}");
        }
        if(StringUtil.isNotEmpty(student.getSpecialty())) {
            list.add(" `specialty` = #{specialty}");
        }
        if(StringUtil.isNotEmpty(student.getStuClass())) {
            list.add(" `stu_class` = #{stuClass}");
        }
        if(StringUtil.isNotEmpty(student.getAddress())) {
            list.add(" `address` = #{address}");
        }
        if(StringUtil.isNotEmpty(student.getEmail())) {
            list.add(" `email` = #{email}");
        }
        if(StringUtil.isNotEmpty(student.getEmail())) {
            list.add(" `email` = #{email}");
        }
        if(student.getStartTime() != null) {
            list.add(" `start_time` = #{startTime}");
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
