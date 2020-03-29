package com.lyy.dao.help;

import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.utils.DateUtil;

/**
 * @author LGX_TvT
 * @date 2020-03-29 1:38
 */
public class AdminAnnouncementProvider {

    /**
     * 条件查询
     * @return
     */
    public String queryByCondition(AdminAnnouncementConditionDTO dto){
        StringBuffer sql=new StringBuffer("select * from admin_announcement where state = 0");
        if (dto.getStartTime() != null && dto.getEndTime() != null){
            sql.append(" and TIME BETWEEN '" + DateUtil.parseDate(dto.getStartTime(), DateUtil.DATE_PATTERN_DEFAULT) + "' AND '" + DateUtil.parseDate(dto.getEndTime(), DateUtil.DATE_PATTERN_DEFAULT) + "'");
        }
        if (!dto.getSelectContent().trim().equals("")){
            sql.append(" and title like '%" + dto.getSelectContent() + "%'");
        }
        sql.append("  ORDER BY TIME DESC");
        return sql.toString();
    }


}
