package com.lyy.dao.help;

import com.lyy.pojo.entity.Video;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:48
 */
public class VideoProvider {


    /**
     * 更新
     * @return
     */
    public String update(Video video){
        StringBuffer sql=new StringBuffer("update video set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(video.getCourse())) {
            list.add(" `course` = #{course}");
        }
        if(StringUtil.isNotEmpty(video.getSection())) {
            list.add(" `section` = #{section}");
        }
        if(StringUtil.isNotEmpty(video.getName())) {
            list.add(" `name` = #{name}");
        }
        if(StringUtil.isNotEmpty(video.getAddr())) {
            list.add(" `addr` = #{addr}");
        }
        if(video.getOrder() != null) {
            list.add(" `order` = #{order}");
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
