package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.exception.base.BussinessException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.VideoDTO;
import com.lyy.pojo.entity.Video;
import com.lyy.pojo.vo.VideoQueryVO;
import com.lyy.service.VideoService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程视频控制层
 * @author LGX_TvT
 * @date 2020-04-12 18:11
 */
@Api(tags = "章节视频管理Api (V1版本)")
@RequestMapping("/v1/video")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ConverterUtil converterUtil;
    

    // TODO: 添加课程视频
    /**
     * 新增章节视频记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "新增章节视频记录", notes = "基础章节视频信息")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@RequestBody CommonRequest<VideoQueryVO> vo) throws AppException {
        try {
            VideoDTO videoDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), VideoDTO.class);
            videoDTO.setId(SnowFlakeUtil.generateId() + "");
            videoDTO.setState("0");
            boolean result = videoService.save(videoDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_VIDEO_SAVE_FAIL_ERROR, "章节视频信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "章节视频信息保存成功"), new ResponseBody<String>("章节视频信息保存成功"));
    }
    
    // TODO：删除课程视频
    /**
     * 删除课程章节记录
     * @param vo
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "删除章节视频记录", notes = "课程章节视频信息ID")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@RequestBody CommonRequest<VideoQueryVO> vo) throws AppException {
        try {
            VideoDTO videoDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), VideoDTO.class);
            boolean result = videoService.remove(videoDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_VIDEO_DELETE_FAIL_ERROR, "章节视频信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "章节视频信息删除成功"), new ResponseBody<String>("课程章节信息删除成功"));
    }
    
    // TODO: 修改课程视频
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
    public CommonResponse<String> doUpdate(@RequestBody CommonRequest<VideoQueryVO> vo) throws AppException {
        try {
            VideoDTO videoDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), VideoDTO.class);
            boolean result = videoService.update(videoDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        }  catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_VIDEO_UPDATE_FAIL_ERROR, "章节视频信息修改失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "章节视频信息修改成功"), new ResponseBody<String>("章节视频信息修改成功"));
    }
    
    // TODO：按照课程ID与章节ID查询视频
    @TokenVerify(required = false)
    @ApiOperation(value = "按照课程ID与章节ID查询视频", notes = "课程ID与章节ID")
    @ApiILog
    @GetMapping("/query/course/{courseId}/{sectionId}")
    public CommonResponse<List<Video>> doQueryAllByCourse(@PathVariable("courseId") String courseId, @PathVariable("sectionId") String sectionId) {
        List<Video> list = null;
        try {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setCourse(courseId);
            videoDTO.setSection(sectionId);
            list = videoService.queryAllByCourseAndSection(videoDTO);
        } catch (BussinessException b) {
            b.printStackTrace();
            throw new AppException(b.getCode(), b.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SECTION_DELETE_FAIL_ERROR, "章节视频信息查找失败");
        }
        return new CommonResponse<List<Video>>(new ResponseHead(StateCode.SUCCEED_CODE, "章节视频信息查找成功"), new ResponseBody<List<Video>>(list));
    }


}
