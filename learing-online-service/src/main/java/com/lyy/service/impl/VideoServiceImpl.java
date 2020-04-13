package com.lyy.service.impl;

import com.lyy.dao.VideoDao;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.VideoDTO;
import com.lyy.pojo.entity.Video;
import com.lyy.service.VideoService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:47
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存
     * @param videoDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean save(VideoDTO videoDTO) throws BussinessException {
        try {
            Video video = converterUtil.copyPropertiesAndReturnNewOne(videoDTO, Video.class);
            int result = videoDao.save(video);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_VIDEO_SAVE_FAIL_ERROR, "视频信息保存失败");
        }
        return true;
    }

    /**
     * 删除
     * @param videoDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean remove(VideoDTO videoDTO) throws BussinessException {
        try {
            int result = videoDao.remove(videoDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_VIDEO_DELETE_FAIL_ERROR, "视频信息删除失败");
        }
        return true;
    }

    /**
     * 修改
     * @param videoDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean update(VideoDTO videoDTO) throws BussinessException {
        try {
            Video video = converterUtil.copyPropertiesAndReturnNewOne(videoDTO, Video.class);
            int result = videoDao.update(video);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_VIDEO_UPDATE_FAIL_ERROR, "视频信息修改失败");
        }
        return true;
    }
}
