package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.dto.StudentCourseDTO;
import com.lyy.pojo.entity.extend.CourseExtend;
import com.lyy.pojo.vo.CourseResponseVO;
import com.lyy.pojo.vo.StudentCourseQueryVO;
import com.lyy.service.CourseService;
import com.lyy.service.StudentCourseService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程展示控制层
 * @author LGX_TvT
 * @date 2020-05-02 16:50
 */
@Api(tags = "课程管理Api (V1版本)")
@RequestMapping("/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 根据类型查找所有课程
     * @param type
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据类型查找所有课程", notes = "无")
    @ApiILog
    @GetMapping("/query/{type}")
    public CommonResponse<List<CourseExtend>> doQueryAll(@PathVariable("type") String type) {
        try {
            List<CourseExtend> result =  courseService.queryAllByType(type);
            return new CommonResponse<List<CourseExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "查找课程成功"), new ResponseBody<>(result));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程失败");
        }
    }


    /**
     * 查找首页推荐课程[类别 - 课程]
     * @param num
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "查找首页推荐课程", notes = "无")
    @ApiILog
    @GetMapping("/query/categoryAndCourse/{num}")
    public CommonResponse<Map> doQueryCategoryAndCourse(@PathVariable("num") Integer num) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = courseService.queryCategoryAndCourse(num);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "推荐课程查询失败");
        }
        return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "推荐课程查询成功"), new ResponseBody<>(result));
    }

    /**
     * 按照人气排序查找课程
     * @param num
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "按照人气排序查找课程", notes = "无")
    @ApiILog
    @GetMapping("/query/course/moods/{num}")
    public CommonResponse<List<CourseExtend>> doQueryMoodsCourse(@PathVariable("num") Integer num) {
        try {
            List<CourseExtend> result = courseService.queryMoodsCourse(num);
            return new CommonResponse<List<CourseExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "查找人气课程成功"), new ResponseBody<>(result));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找人气课程失败");
        }
    }

    /**
     * 根据ID查找课程
     * @param id
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据ID查找课程", notes = "无")
    @ApiILog
    @GetMapping("/query/course/{id}")
    public CommonResponse<CourseResponseVO> doQueryCourseById(@PathVariable("id") String id) {
        try {
            CourseDTO result =  courseService.queryById(id);
            CourseResponseVO courseResponseVO = converterUtil.copyPropertiesAndReturnNewOne(result, CourseResponseVO.class);
            return new CommonResponse<CourseResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "课程查询成功"), new ResponseBody<>(courseResponseVO));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程查询失败");
        }
    }

    /**
     * 根据课程ID查找课程目录
     * @param id
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查找课程目录", notes = "无")
    @ApiILog
    @GetMapping("/query/catalog/{id}")
    public CommonResponse<Map> doQueryCatalogById(@PathVariable("id") String id) {
        try {
            Map<String, Object> map =  courseService.queryCatalogById(id);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "目录信息查询成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "目录信息查询失败");
        }
    }

    /**
     * 根据课程ID查找课程目录
     * @param id
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查找课程目录信息", notes = "无")
    @ApiILog
    @GetMapping("/query/catalog/all/{id}")
    public CommonResponse<Map> doQueryCatalogAllById(@PathVariable("id") String id) {
        try {
            Map<String, Object> map =  courseService.queryCatalogAllById(id);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "目录信息查询成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "目录信息查询失败");
        }
    }

    // TODO：根据关键字检索课程
    /**
     * 分页查找管理员公告信息
     * @param name
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "关键字查找课程信息信息", notes = "关键字.")
    @ApiILog
    @GetMapping("/condition/{name}")
    public CommonResponse<List<CourseExtend>> doQueryAllByName(@PathVariable("name") String name) throws AppException {
        try {
            CourseDTO dto = new CourseDTO();
            dto.setName(name);
            dto.setCurrentPage(0);
            dto.setSize(0);
            CourseDTO courseDTO = courseService.queryByName(dto);
            return new CommonResponse<List<CourseExtend>>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查找成功"), new ResponseBody<>(courseDTO.getPageInfo().getList()));
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }

    }

    // TODO: 学生申请加入课程
    /**
     * 添加学生选课信息
     * @param vo
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "添加学生选课信息", notes = "学生选课信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<StudentCourseQueryVO> vo) {
        try {
            StudentCourseDTO studentCourseDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), StudentCourseDTO.class);
            studentCourseDTO.setId(SnowFlakeUtil.generateId() + "");
            studentCourseDTO.setTime(new Date());
            studentCourseDTO.setState("0");
            boolean result = studentCourseService.save(studentCourseDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_STUDENT_COURSE_SAVE_FAIL_ERROR, "学生选课信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "学生选课信息保存成功"), new ResponseBody<String>("学生选课信息保存成功"));
    }



    // TODO: 根据学生ID查找课程

    /**
     * 根据学生ID查找课程
     * @param id
     * @return
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "根据课程ID查找课程目录", notes = "无")
    @ApiILog
    @GetMapping("/query/student/{id}")
    public CommonResponse<Map> doQueryCourseByStudentId(@PathVariable("id") String id) {
        try {
            Map<String, Object> map =  courseService.queryCourseByStudentId(id);
            return new CommonResponse<Map>(new ResponseHead(StateCode.SUCCEED_CODE, "课程信息查询成功"), new ResponseBody<>(map));
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查询失败");
        }
    }



}
