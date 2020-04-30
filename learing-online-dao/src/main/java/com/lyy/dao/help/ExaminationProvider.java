package com.lyy.dao.help;

import com.lyy.pojo.entity.Examination;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:06
 */
public class ExaminationProvider {

    /**
     * 更新
     * @return
     */
    public String update(Examination examination){
        StringBuffer sql=new StringBuffer("update examination set");
        List<String> list = new ArrayList<>();

        if(StringUtil.isNotEmpty(examination.getTitle())) {
            list.add(" title = #{title}");
        }
        if(StringUtil.isNotEmpty(examination.getContent())) {
            list.add(" content = #{content}");
        }
        if(StringUtil.isNotEmpty(examination.getResource())) {
            list.add(" resource = #{resource}");
        }
        if(examination.getStartTime() != null) {
            list.add(" start_time = #{startTime}");
        }
        if(examination.getEndTime() != null) {
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
