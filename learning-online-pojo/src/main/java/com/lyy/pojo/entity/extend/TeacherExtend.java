package com.lyy.pojo.entity.extend;

import com.lyy.pojo.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师实体类扩展
 * @author LGX_TvT
 * @date 2020-04-10 17:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExtend extends Teacher {

    /**
     * 院系名称
     */
    private String departmentName;

}
