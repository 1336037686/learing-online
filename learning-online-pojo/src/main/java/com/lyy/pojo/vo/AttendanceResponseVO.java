package com.lyy.pojo.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 考勤表
 * @author LGX_TvT
 * @date 2020-04-29 3:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseVO {

    /**
     * id
     */
    private String id;

    /**
     * 课程
     */
    private String course;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * pageInfo
     */
    private PageInfo pageInfo;


}
