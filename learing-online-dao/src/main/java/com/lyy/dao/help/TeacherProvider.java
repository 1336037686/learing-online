package com.lyy.dao.help;

import com.lyy.pojo.entity.Teacher;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-21 23:00
 */
public class TeacherProvider {

    /**
     * 更新
     * @return
     */
    public String update(Teacher teacher){
        StringBuffer sql=new StringBuffer("update teacher set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(teacher.getName())) {
            list.add("  `name` = #{name}");
        }
        if(StringUtil.isNotEmpty(teacher.getSex())) {
            list.add(" sex = #{sex}");
        }
        if(StringUtil.isNotEmpty(teacher.getPhone())) {
            list.add(" phone = #{phone}");
        }
        if(StringUtil.isNotEmpty(teacher.getPassword())) {
            list.add(" password = #{password}");
        }
        if(StringUtil.isNotEmpty(teacher.getAddress())) {
            list.add(" address = #{address}");
        }
        if(StringUtil.isNotEmpty(teacher.getDepartment())) {
            list.add(" department = #{department}");
        }
        if(StringUtil.isNotEmpty(teacher.getEmail())) {
            list.add(" email = #{email}");
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
