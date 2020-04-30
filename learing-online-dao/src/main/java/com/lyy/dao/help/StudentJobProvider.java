package com.lyy.dao.help;

import com.lyy.pojo.entity.StudentJob;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:06
 */
public class StudentJobProvider {

    /**
     * 更新
     * @return
     */
    public String update(StudentJob studentJob){
        StringBuffer sql=new StringBuffer("update student_job set");
        List<String> list = new ArrayList<>();

        if(StringUtil.isNotEmpty(studentJob.getTitle())) {
            list.add(" title = #{title}");
        }
        if(StringUtil.isNotEmpty(studentJob.getContent())) {
            list.add(" content = #{content}");
        }
        if(StringUtil.isNotEmpty(studentJob.getResource())) {
            list.add(" resource = #{resource}");
        }
        if(studentJob.getTime() != null) {
            list.add(" time = #{time}");
        }
        if(StringUtil.isNotEmpty(studentJob.getCheckState())) {
            list.add(" check_state = #{checkState}");
        }
        if(studentJob.getScore() != null) {
            list.add(" score = #{score}");
        }
        if(StringUtil.isNotEmpty(studentJob.getEvaluate())) {
            list.add(" evaluate = #{evaluate}");
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
