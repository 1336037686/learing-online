package com.lyy.pojo.entity.extend;

import com.lyy.pojo.entity.StudentExamination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LGX_TvT
 * @date 2020-05-01 11:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExaminationExtend extends StudentExamination {

    /**
     * 学生学号
     */
    private String studentUserName;

    /**
     * 学生姓名
     */
    private String studentName;

}
