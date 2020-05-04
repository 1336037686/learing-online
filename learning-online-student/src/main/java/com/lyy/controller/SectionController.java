package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;
import com.lyy.common.StateCode;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.SectionDTO;
import com.lyy.pojo.entity.Section;
import com.lyy.service.SectionService;
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
 * 课程章节控制层
 * @author LGX_TvT
 * @date 2020-04-12 18:04
 */
@Api(tags = "课程章节管理Api (V1版本)")
@RequestMapping("/v1/section")
@RestController
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ConverterUtil converterUtil;

    // TODO: 按照课程ID查询章节
    @TokenVerify(required = false)
    @ApiOperation(value = "按照课程ID查询章节", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/course/{id}")
    public CommonResponse<List<Section>> doQueryAllByCourse(@PathVariable("id") String courseId) {
        List<Section> list = null;
        try {
            SectionDTO sectionDTO = new SectionDTO();
            sectionDTO.setCourse(courseId);
            list = sectionService.queryAllByCourse(sectionDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SECTION_DELETE_FAIL_ERROR, "课程章节信息查找失败");
        }
        return new CommonResponse<List<Section>>(new ResponseHead(StateCode.SUCCEED_CODE, "课程章节信息查找成功"), new ResponseBody<List<Section>>(list));
    }
}
