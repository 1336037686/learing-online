package com.lyy.controller;

import com.lyy.authority.annotation.TokenVerify;
import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;
import com.lyy.common.StateCode;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.vo.ResourceResponseVO;
import com.lyy.utils.cdn.CdnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源上传控制层
 * @author LGX_TvT
 * @date 2020-04-12 13:04
 */
@Api(tags = "基础资源上传管理Api (V1版本)")
@RequestMapping("/v1/upload")
@RestController
public class ResourceUploadController {

    @Autowired
    private CdnUtil cdnUtil;

    /**
     * 图片上传
     * @param file
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "图片上传接口", notes = "图片")
    @ApiILog
    @PostMapping("/image")
    public CommonResponse<ResourceResponseVO> doUploadImage(MultipartFile file) throws AppException{
        ResourceResponseVO resourceResponseVO = null;
        try {
            String url = cdnUtil.uploadImage(file.getOriginalFilename(), file.getInputStream(), file.getSize());
            resourceResponseVO = new ResourceResponseVO(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_CDN_ERROR, "上传资源失败");
        }
        return new CommonResponse<ResourceResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "上传成功"), new ResponseBody<ResourceResponseVO>(resourceResponseVO));
    }

    // TODO: 视频上传
    /**
     * 视频上传
     * @param file
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "视频上传接口", notes = "视频")
    @ApiILog
    @PostMapping("/video")
    public CommonResponse<ResourceResponseVO> doUploadVideo(MultipartFile file) throws AppException{
        ResourceResponseVO resourceResponseVO = null;
        try {
            String url = cdnUtil.uploadVideo(file.getOriginalFilename(), file.getInputStream(), file.getSize());
            resourceResponseVO = new ResourceResponseVO(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_CDN_ERROR, "上传资源失败");
        }
        return new CommonResponse<ResourceResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "上传成功"), new ResponseBody<ResourceResponseVO>(resourceResponseVO));
    }


    // TODO：压缩包等资源上传
    /**
     * 压缩包等资源上传
     * @param file
     * @return
     * @throws AppException
     */
    @TokenVerify(required = false)
    @ApiOperation(value = "资源上传接口", notes = "压缩包/资源")
    @ApiILog
    @PostMapping("/resource")
    public CommonResponse<ResourceResponseVO> doUploadResource(MultipartFile file) throws AppException{
        ResourceResponseVO resourceResponseVO = null;
        try {
            String url = cdnUtil.uploadResource(file.getOriginalFilename(), file.getInputStream(), file.getSize());
            resourceResponseVO = new ResourceResponseVO(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SYSTEM_CDN_ERROR, "上传资源失败");
        }
        return new CommonResponse<ResourceResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "上传成功"), new ResponseBody<ResourceResponseVO>(resourceResponseVO));
    }


}
