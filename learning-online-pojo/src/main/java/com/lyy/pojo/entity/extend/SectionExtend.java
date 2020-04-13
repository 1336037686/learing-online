package com.lyy.pojo.entity.extend;

import com.lyy.pojo.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 章节扩展类
 * @author LGX_TvT
 * @date 2020-04-13 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionExtend extends Section {

    /**
     * 课程名称
     */
    private String courseName;

}
