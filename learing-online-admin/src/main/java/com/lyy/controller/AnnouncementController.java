package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.AdminAnnouncementConditionDTO;
import com.lyy.pojo.dto.AdminAnnouncementDTO;
import com.lyy.pojo.vo.AdminAnnouncementConditionQueryVo;
import com.lyy.pojo.vo.AdminAnnouncementQueryVo;
import com.lyy.pojo.vo.AdminAnnouncementResponseVo;
import com.lyy.service.AdminAnnouncementService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 管理员公告管理控制层
 * @author LGX_TvT
 * @date 2020-03-24 0:24
 */
@Api(tags = "管理员公告管理Api (V1版本)")
@RequestMapping("/v1/announcement")
@RestController
public class AnnouncementController {

    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 分页查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页查找管理员公告信息", notes = "需要页号，长度范围.")
    @ApiILog
    @PostMapping("/query")
    public CommonResponse<AdminAnnouncementResponseVo> doQueryAll(@org.springframework.web.bind.annotation.RequestBody CommonRequest<AdminAnnouncementQueryVo> vo) throws AppException {
        AdminAnnouncementDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AdminAnnouncementDTO.class);
        AdminAnnouncementResponseVo adminAnnouncementResponseVo = null;
        try {
            AdminAnnouncementDTO announcementDTO = adminAnnouncementService.queryAll(dto);
            adminAnnouncementResponseVo = converterUtil.copyPropertiesAndReturnNewOne(announcementDTO, AdminAnnouncementResponseVo.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "管理公告信息查找失败");
        }
        return new CommonResponse<AdminAnnouncementResponseVo>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息查找成功"), new ResponseBody<AdminAnnouncementResponseVo>(adminAnnouncementResponseVo));
    }

    /**
     * 保存管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "保存管理员公告信息", notes = "需要公告名称，公告内容（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<AdminAnnouncementQueryVo> vo) throws AppException {
        AdminAnnouncementDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AdminAnnouncementDTO.class);
        try {
            // 设置id
            dto.setId(SnowFlakeUtil.generateId() + "");
            // 设置状态
            dto.setState("0");
            // 设置时间
            dto.setTime(new Date());
            // 保存
            boolean result = adminAnnouncementService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_SAVE_FAIL_ERROR, "管理员公告信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息保存成功"), new ResponseBody<String>("管理员公告信息保存成功"));
    }

    /**
     * 删除管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = true)
    @ApiOperation(value = "删除管理员公告信息", notes = "需要公告ID.")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<AdminAnnouncementQueryVo> vo) throws AppException {
        AdminAnnouncementDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AdminAnnouncementDTO.class);
        try {
            boolean result = adminAnnouncementService.remove(dto);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_DELETE_FAIL_ERROR, "管理公告信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息删除成功"), new ResponseBody<String>("管理员公告信息删除成功"));
    }

    /**
     * 按照条件查找
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "条件搜索分页查找公告", notes = "需要查找内容.")
    @ApiILog
    @PostMapping("/condition")
    public CommonResponse<AdminAnnouncementResponseVo> doQueryByCondition(@org.springframework.web.bind.annotation.RequestBody CommonRequest<AdminAnnouncementConditionQueryVo> vo) throws AppException {
        AdminAnnouncementConditionDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), AdminAnnouncementConditionDTO.class);
        System.out.println(dto);
        AdminAnnouncementResponseVo responseVo = null;
        try {
            AdminAnnouncementConditionDTO adminAnnouncementConditionDTO = adminAnnouncementService.queryByCondition(dto);
            System.out.println(adminAnnouncementConditionDTO.getPageInfo().getList());
            responseVo = converterUtil.copyPropertiesAndReturnNewOne(adminAnnouncementConditionDTO, AdminAnnouncementResponseVo.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "管理公告信息查找失败");
        }
        return new CommonResponse<AdminAnnouncementResponseVo>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息查找成功"), new ResponseBody<AdminAnnouncementResponseVo>(responseVo));
    }


}
