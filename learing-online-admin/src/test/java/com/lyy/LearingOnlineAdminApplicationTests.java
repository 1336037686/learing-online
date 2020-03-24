package com.lyy;

import com.lyy.dao.CategoryDao;
import com.lyy.pojo.entity.Category;
import com.lyy.utils.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearingOnlineAdminApplicationTests {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    void contextLoads() {
        int save = categoryDao.save(new Category(SnowFlakeUtil.generateId() + "", "123", "0"));
        System.out.println(save);
    }

}
