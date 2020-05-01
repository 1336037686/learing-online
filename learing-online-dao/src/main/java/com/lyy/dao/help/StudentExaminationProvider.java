package com.lyy.dao.help;

import com.lyy.pojo.entity.StudentExamination;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:06
 */
public class StudentExaminationProvider {

    /**
     * 更新
     * @return
     */
    public String update(StudentExamination studentExamination){
        StringBuffer sql=new StringBuffer("update student_examination set");
        List<String> list = new ArrayList<>();

        if(StringUtil.isNotEmpty(studentExamination.getTitle())) {
            list.add(" title = #{title}");
        }
        if(StringUtil.isNotEmpty(studentExamination.getContent())) {
            list.add(" content = #{content}");
        }
        if(StringUtil.isNotEmpty(studentExamination.getResource())) {
            list.add(" resource = #{resource}");
        }
        if(studentExamination.getTime() != null) {
            list.add(" time = #{time}");
        }
        if(StringUtil.isNotEmpty(studentExamination.getCheckState())) {
            list.add(" check_state = #{checkState}");
        }
        if(studentExamination.getScore() != null) {
            list.add(" score = #{score}");
        }
        if(StringUtil.isNotEmpty(studentExamination.getEvaluate())) {
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
