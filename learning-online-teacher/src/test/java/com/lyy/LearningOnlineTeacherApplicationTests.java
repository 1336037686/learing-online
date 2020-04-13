package com.lyy;

import com.lyy.utils.cdn.CdnUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class LearningOnlineTeacherApplicationTests {

    @Autowired
    private CdnUtil cdnUtil;


    @Test
    void contextLoads() {
        String s = cdnUtil.uploadVideo(new File("C:\\Users\\LGX\\Desktop\\20200110_104506.mp4"));
        System.out.println(s);
    }

}
