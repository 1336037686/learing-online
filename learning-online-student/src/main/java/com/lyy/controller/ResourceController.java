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
import com.lyy.pojo.dto.ResourceDTO;
import com.lyy.pojo.entity.Resource;
import com.lyy.service.ResourceService;
import com.lyy.utils.ConverterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 资源管理
 * @author LGX_TvT
 * @date 2020-05-04 14:38
 */
@Api(tags = "章节资源管理Api (V1版本)")
@RequestMapping("/v1/resource")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ConverterUtil converterUtil;

    //TODO：查找最新资源
    @TokenVerify(required = false)
    @ApiOperation(value = "查找最新资源", notes = "无")
    @ApiILog
    @GetMapping("/query/newest/{num}")
    public CommonResponse<List<Resource>> doQueryNewest(@PathVariable("num") Integer num) throws AppException {
        try {
            List<Resource> result = resourceService.queryNewest(num);
            return new CommonResponse<List<Resource>>(new ResponseHead(StateCode.SUCCEED_CODE, "资源信息查询成功"), new ResponseBody<>(result));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_RESOURCE_QUERY_FAIL_ERROR, "资源信息查询失败");
        }
    }

    /**
     * 按照课程与章节查找资源
     * @param courseId
     * @param courseId
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照课程查找资源集合", notes = "课程ID")
    @ApiILog
    @GetMapping("/query/{courseId}")
    public CommonResponse<Map> doQueryAllByCourseAndSection(@PathVariable("courseId") String courseId) {
        try {
            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setCourse(courseId);
            Map<String, Object> map = resourceService.queryMapByCourse(resourceDTO);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "资源信息查找成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_RESOURCE_DELETE_FAIL_ERROR, "资源信息查找失败");
        }

    }


    //TODO：按照课程章节ID查找资源
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
