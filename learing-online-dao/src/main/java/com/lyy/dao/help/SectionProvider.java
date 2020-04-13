package com.lyy.dao.help;

import com.lyy.pojo.entity.Section;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-13 20:05
 */
public class SectionProvider {

    /**
     * 更新
     * @return
     */
    public String update(Section section){
        StringBuffer sql=new StringBuffer("update section set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(section.getCourse())) {
            list.add(" `course` = #{course}");
        }
        if(StringUtil.isNotEmpty(section.getName())) {
            list.add(" `name` = #{name}");
        }
        if(section.getOrder() != null) {
            list.add(" `order` = #{order}");
        }
        if(StringUtil.isNotEmpty(section.getIntro())) {
            list.add(" `intro` = #{intro}");
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
