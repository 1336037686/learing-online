package com.lyy;

import com.lyy.dao.AdminAnnouncementDao;
import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.pojo.entity.AdminAnnouncement;
import com.lyy.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class LearingOnlineAdminApplicationTests {

    @Autowired
    private AdminAnnouncementDao dao;

    @Test
    void contextLoads() {
        Date startTime = DateUtil.parseDate("2020-03-28");
        Date endTime = DateUtil.parseDate("2020-03-29");
        List<AdminAnnouncement> adminAnnouncements = dao.queryByCondition(new AdminAnnouncementConditionDTO(startTime, endTime, ""));
        System.out.println(adminAnnouncements);
    }

}
