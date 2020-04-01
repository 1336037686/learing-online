package com.lyy.controller;

import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.vo.CourseQueryVO;
import com.lyy.pojo.vo.CourseResponseVO;
import com.lyy.service.CourseService;
import com.lyy.utils.ConverterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程信息管理 控制层
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "课程信息管理Api (V1版本)")
@RequestMapping("/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ConverterUtil converterUtil;


    /**
     * 分页查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页查找课程信息信息", notes = "需要页号，长度范围.")
    @ApiILog
    @PostMapping("/query")
    public CommonResponse<CourseResponseVO> doQueryAll(@org.springframework.web.bind.annotation.RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        CourseDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseDTO.class);
        CourseResponseVO courseResponseVO = null;
        try {
            CourseDTO courseDTO = courseService.queryAll(dto);
            courseResponseVO = converterUtil.copyPropertiesAndReturnNewOne(courseDTO, CourseResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
        return new CommonResponse<CourseResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查找成功"), new ResponseBody<CourseResponseVO>(courseResponseVO));
    }

    /**
     * 更新课程课程信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "修改课程信息", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        try {
            courseService.update(converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), CourseDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_UPDATE_FAIL_ERROR, "课程信息更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息更新成功"), new ResponseBody<String>("课程信息更新成功"));
    }



}
