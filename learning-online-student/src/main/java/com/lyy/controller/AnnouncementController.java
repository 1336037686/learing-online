package com.lyy.controller;

import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;
import com.lyy.common.StateCode;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.AdminAnnouncementDTO;
import com.lyy.pojo.entity.AdminAnnouncement;
import com.lyy.pojo.vo.AdminAnnouncementResponseVo;
import com.lyy.service.AdminAnnouncementService;
import com.lyy.utils.ConverterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公告管理控制层
 * @author LGX_TvT
 * @date 2020-05-02 16:56
 */
@Api(tags = "管理员公告管理Api (V1版本)")
@RequestMapping("/v1/announcement")
@RestController
public class AnnouncementController {

    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @Autowired
    private ConverterUtil converterUtil;

    // TODO： 获取首页公告列表
    /**
     * 分页查找管理员公告信息
     * @param num
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "查找管理员公告信息", notes = "需要页号，长度范围.")
    @ApiILog
    @GetMapping("/query/{num}")
    public CommonResponse<List<AdminAnnouncement>> doQueryAll(@PathVariable("num") Integer num) throws AppException {
        AdminAnnouncementResponseVo adminAnnouncementResponseVo = null;
        try {
            AdminAnnouncementDTO dto = new AdminAnnouncementDTO();
            dto.setCurrentPage(1);
            dto.setSize(num);
            AdminAnnouncementDTO announcementDTO = adminAnnouncementService.queryAll(dto);
            List list = announcementDTO.getPageInfo().getList();
            return new CommonResponse<List<AdminAnnouncement>>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息查找成功"), new ResponseBody<>(list));
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "管理公告信息查找失败");
        }

    }

    // TODO：查看所有公告


}
