package com.lyy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LGX_TvT
 * @date 2020-03-29 1:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAnnouncementConditionQueryVo extends AdminAnnouncementQueryVo {

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 查找内容
     */
    private String selectContent;

}
