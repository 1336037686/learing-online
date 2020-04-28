package com.lyy.pojo.entity;

import java.util.Date;

/**
 * 学生签到中间表
 * @author LGX_TvT
 * @date 2020-04-29 4:04
 */
public class StudentAttendance {

    /**
     * id
     */
    private String id;

    /**
     * 签到ID
     */
    private String attendance;


    /**
     * 学生ID
     */
    private String student;

    /**
     * 签到时间
     */
    private Date time;

    /**
     * 状态
     */
    private String state;


}
