package com.lyy.dao.help;

import com.lyy.pojo.entity.Resource;
import com.lyy.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-27 21:03
 */
public class ResourceProvider {

    /**
     * 更新
     * @return
     */
    public String update(Resource resource){
        StringBuffer sql=new StringBuffer("update resource set");
        List<String> list = new ArrayList<>();
        if(StringUtil.isNotEmpty(resource.getName())) {
            list.add(" `name` = #{name}");
        }
        if(StringUtil.isNotEmpty(resource.getAddr())) {
            list.add(" `addr` = #{addr}");
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
