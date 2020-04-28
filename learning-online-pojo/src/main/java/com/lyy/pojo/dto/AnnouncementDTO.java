package com.lyy.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师公告
 * (Announcement)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:02:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO implements Serializable {
    private static final long serialVersionUID = 808588092642996019L;

    /**
     * id
     */
    private String id;

    /**
     * 课程
     */
    private String course;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 状态
     */
    private String state;

}