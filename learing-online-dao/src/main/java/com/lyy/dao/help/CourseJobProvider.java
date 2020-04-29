package com.lyy.dao.help;

import com.lyy.pojo.entity.CourseJob;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:06
 */
public class CourseJobProvider {

    /**
     * 更新
     * @return
     */
    public String update(CourseJob courseJob){
        StringBuffer sql=new StringBuffer("update course_job set");
        List<String> list = new ArrayList<>();

        if(StringUtil.isNotEmpty(courseJob.getTitle())) {
            list.add(" title = #{title}");
        }
        if(StringUtil.isNotEmpty(courseJob.getContent())) {
            list.add(" content = #{content}");
        }
        if(StringUtil.isNotEmpty(courseJob.getResource())) {
            list.add(" resource = #{resource}");
        }
        if(courseJob.getStartTime() != null) {
            list.add(" start_time = #{startTime}");
        }
        if(courseJob.getEndTime() != null) {
            list.add(" end_time = #{endTime}");
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
