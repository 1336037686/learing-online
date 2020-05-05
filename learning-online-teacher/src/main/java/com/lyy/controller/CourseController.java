package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.entity.extend.CourseExtend;
import com.lyy.pojo.vo.CourseQueryVO;
import com.lyy.pojo.vo.CourseResponseVO;
import com.lyy.service.CourseService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-10 17:55
 */
@SuppressWarnings("all")
@Api(tags = "课程管理Api (V1版本)")
@RequestMapping("/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ConverterUtil converterUtil;

    // TODO： 新增课程记录
    /**
     * 新增课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增课程记录", notes = "基础课程信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        try {
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseDTO.class);
            courseDTO.setId(SnowFlakeUtil.generateId() + "");
            courseDTO.setTime(new Date());
            courseDTO.setState("0");
            courseDTO.setCheckState("0");
            boolean result = courseService.save(courseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息保存成功"), new ResponseBody<String>("课程信息保存成功"));
    }


    // TODO： 修改课程记录
    /**
     * 修改课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "修改课程记录", notes = "基础课程信息")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        try {
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseDTO.class);
            courseDTO.setCheckState("0");
            boolean result = courseService.update(courseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息修改成功"), new ResponseBody<String>("课程信息修改成功"));
    }

    // TODO： 删除课程记录
    /**
     * 删除课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "删除课程记录", notes = "课程信息ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        try {
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseDTO.class);
            boolean result = courseService.remove(courseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息删除成功"), new ResponseBody<String>("课程信息删除成功"));
    }

    // TODO： 按照教师ID查找课程记录
    /**
     * 按照教师ID查找课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照教师ID查找课程记录", notes = "教师ID")
    @ApiILog
    @PostMapping("/query/teacher")
    public CommonResponse<CourseResponseVO> doQueryByTeacherId(@RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        CourseResponseVO courseResponseVO = null;
        try {
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), CourseDTO.class);
            CourseDTO dto = courseService.queryByTeacher(courseDTO);
            courseResponseVO = converterUtil.copyPropertiesAndReturnNewOne(dto, CourseResponseVO.class);
        } catch (BussinessException b) {
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
        return new CommonResponse<CourseResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查找成功"), new ResponseBody<CourseResponseVO>(courseResponseVO));
    }

    // TODO： 按照教师ID查找课程记录
    /**
     * 按照教师ID查找课程记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照教师ID查找所有课程记录", notes = "教师ID")
    @ApiILog
    @GetMapping("/query/teacher/{id}")
    public CommonResponse<List<CourseExtend>> doQueryAllByTeacherId(@PathVariable("id") String teacherId) throws AppException {
        List<CourseExtend> courseResponseVO = null;
        try {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setTeacher(teacherId);
            courseResponseVO = courseService.queryAllPassByTeacher(courseDTO);
        } catch (BussinessException b) {
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
        return new CommonResponse<List<CourseExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查找成功"), new ResponseBody<List<CourseExtend>>(courseResponseVO));
    }

    // TODO： 按照ID查找课程记录
    /**
     * 按照ID查找课程记录
     * @param id
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照ID查找课程记录", notes = "课程信息ID")
    @ApiILog
    @GetMapping("/query/{id}")
    public CommonResponse<CourseResponseVO> doQueryById(@PathVariable("id") String id) throws AppException {
        CourseResponseVO courseResponseVO = null;
        try {
            CourseDTO dto = courseService.queryById(id);
            courseResponseVO = converterUtil.copyPropertiesAndReturnNewOne(dto, CourseResponseVO.class);
        } catch (BussinessException b) {
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
        return new CommonResponse<CourseResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查找成功"), new ResponseBody<CourseResponseVO>(courseResponseVO));
    }

    // TODO： 条件搜索课程信息记录

    /**
     * 条件搜索课程信息记录
     * 条件包括 按照分类查找， 按照关键字查找， 按照教师查找， 按照
     * @param vo
     * @return
     * @throws AppException
     */
    public CommonResponse<CourseResponseVO> doQueryByCondition(@RequestBody CommonRequest<CourseQueryVO> vo) throws AppException {
        return null;
    }




}
