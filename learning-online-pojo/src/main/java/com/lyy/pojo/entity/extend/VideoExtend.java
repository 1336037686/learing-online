package com.lyy.pojo.entity.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoExtend {

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 章节名称
     */
    private String sectionName;

}
