package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.SectionDTO;
import com.lyy.pojo.entity.Section;
import com.lyy.pojo.vo.SectionQueryVO;
import com.lyy.service.SectionService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

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

    // TODO: 新增章节
    /**
     * 新增课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增课程章节记录", notes = "基础课程章节信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<SectionQueryVO> vo) throws AppException {
        try {
            SectionDTO sectionDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), SectionDTO.class);
            sectionDTO.setId(SnowFlakeUtil.generateId() + "");
            sectionDTO.setState("0");
            boolean result = sectionService.save(sectionDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SECTION_SAVE_FAIL_ERROR, "课程章节信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程章节信息保存成功"), new ResponseBody<String>("课程章节信息保存成功"));
    }


    // TODO: 修改章节
    /**
     * 修改课程章节记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改课程章节记录", notes = "基础课程章节信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<SectionQueryVO> vo) throws AppException {
        try {
            SectionDTO sectionDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), SectionDTO.class);
            boolean result = sectionService.update(sectionDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程章节信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程章节信息修改成功"), new ResponseBody<String>("课程章节信息修改成功"));
    }

    // TODO：删除章节
    /**
     * 删除课程章节记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "删除课程章节记录", notes = "课程章节信息ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<SectionQueryVO> vo) throws AppException {
        try {
            SectionDTO sectionDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), SectionDTO.class);
            boolean result = sectionService.remove(sectionDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SECTION_DELETE_FAIL_ERROR, "课程章节信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程章节信息删除成功"), new ResponseBody<String>("课程章节信息删除成功"));
    }

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
