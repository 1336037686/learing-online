package com.lyy.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdminAnnouncement)实体类
 *
 * @author makejava
 * @since 2020-03-23 21:01:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAnnouncement implements Serializable {
    private static final long serialVersionUID = -36274989612419111L;
    
    private String id;
    
    private String title;
    
    private String content;
    
    private Date time;

    private String state;

}