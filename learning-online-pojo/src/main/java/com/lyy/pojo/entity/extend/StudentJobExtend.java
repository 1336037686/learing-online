package com.lyy.pojo.entity.extend;

import com.lyy.pojo.entity.StudentJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-04-29 15:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentJobExtend extends StudentJob {

    /**
     * 学生学号
     */
    private String studentUserName;

    /**
     * 学生姓名
     */
    private String studentName;

}
