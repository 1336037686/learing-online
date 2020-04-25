package com.lyy.pojo.entity.extend;

import com.lyy.pojo.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-24 20:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseExtend extends Course {
    /**
     * 课程类别名称
     */
    private String typeName;

    /**
     * 教师名称
     */
    private String teacherName;

}
