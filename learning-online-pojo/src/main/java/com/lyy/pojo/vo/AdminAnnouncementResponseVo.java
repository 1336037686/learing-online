package com.lyy.pojo.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LGX_TvT
 * @date 2020-03-28 16:37
 *
 * 表结构
 * CREATE TABLE admin_announcement(
 * 	id VARCHAR(100),
 * 	title VARCHAR(50),
 * 	content	TEXT,
 * 	TIME DATETIME,
 * 	state VARCHAR(10),
 * 	PRIMARY KEY (id)
 * )
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAnnouncementResponseVo {

    /**
     * id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date time;

    /**
     *
     */
    private PageInfo pageInfo;

}
