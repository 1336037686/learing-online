package com.lyy;

import com.lyy.pojo.dto.StudentDTO;
import com.lyy.service.StudentService;
import com.lyy.utils.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LearningOnlineTeacherApplicationTests {

    @Autowired
    StudentService studentService;

    @Test
    @Transactional
    void contextLoads() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(SnowFlakeUtil.generateId() + "");
        studentDTO.setName("赵六");
        studentService.save(studentDTO);
    }

}
