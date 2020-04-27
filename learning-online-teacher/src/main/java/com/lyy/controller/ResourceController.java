package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.ResourceDTO;
import com.lyy.pojo.entity.Resource;
import com.lyy.pojo.vo.ResourceQueryVO;
import com.lyy.service.ResourceService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程资源列表
 * @author LGX_TvT
 * @date 2020-04-12 18:13
 */
@Api(tags = "章节资源管理Api (V1版本)")
@RequestMapping("/v1/resource")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ConverterUtil converterUtil;

    // TODO： 添加资源
    /**
     * 新增课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增课程章节资源", notes = "基础资源信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<ResourceQueryVO> vo) throws AppException {
        try {
            ResourceDTO resourceDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), ResourceDTO.class);
            resourceDTO.setId(SnowFlakeUtil.generateId() + "");
            resourceDTO.setState("0");
            boolean result = resourceService.save(resourceDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_RESOURCE_SAVE_FAIL_ERROR, "资源信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "资源信息保存成功"), new ResponseBody<String>("资源信息保存成功"));
    }

    // TODO： 删除资源
    /**
     * 删除课程章节记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "删除资源信息记录", notes = "资源信息ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<ResourceQueryVO> vo) throws AppException {
        try {
            ResourceDTO resourceDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), ResourceDTO.class);
            boolean result = resourceService.remove(resourceDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_RESOURCE_DELETE_FAIL_ERROR, "资源信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "资源信息删除成功"), new ResponseBody<String>("资源信息删除成功"));
    }

    // TODO： 按照课程与章节查找资源

    /**
     * 按照课程与章节查找资源
     * @param courseId
     * @param sectionId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照课程与章节查找资源", notes = "课程与章节ID")
    @ApiILog
    @GetMapping("/query/{courseId}/{sectionId}")
    public CommonResponse<List<Resource>> doQueryAllByCourseAndSection(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId) {
        List<Resource> list = null;
        try {
            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setCourse(courseId);
            resourceDTO.setSection(sectionId);
            list = resourceService.queryAllByCourseAndSection(resourceDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_RESOURCE_DELETE_FAIL_ERROR, "资源信息查找失败");
        }
        return new CommonResponse<List<Resource>>(new ResponseHead(StateCode.SUCCEED_CODE, "资源信息查找成功"), new ResponseBody<List<Resource>>(list));
    }

}
