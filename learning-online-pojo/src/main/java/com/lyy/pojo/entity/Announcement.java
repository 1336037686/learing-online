package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Announcement)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:02:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement implements Serializable {
    private static final long serialVersionUID = 808588092642996019L;
    
    private String id;
    
    private String courseId;
    
    private String title;
    
    private String content;
    
    private Date time;
    
    private String checkStatus;

}