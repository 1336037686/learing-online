package com.lyy;

import com.lyy.dao.AdminAnnouncementDao;
import com.lyy.pojo.entity.AdminAnnouncement;
import com.lyy.utils.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class LearingOnlineAdminApplicationTests {

    @Autowired
    private AdminAnnouncementDao dao;

    @Test
    void contextLoads() {
//        Date startTime = DateUtil.parseDate("2020-03-28");
//        Date endTime = DateUtil.parseDate("2020-03-29");
//        List<AdminAnnouncement> adminAnnouncements = dao.queryByCondition(new AdminAnnouncementConditionDTO(startTime, endTime, ""));
//        System.out.println(adminAnnouncements);
        for (int i = 0; i < 200; i++) {
            dao.save(new AdminAnnouncement(SnowFlakeUtil.generateId() + "", "title" + i, "<h1>This is Test</h1>", new Date(), "0"));
            System.out.println("save " + i + " succeed ...");
        }
        System.out.println("ok...");
    }

}
