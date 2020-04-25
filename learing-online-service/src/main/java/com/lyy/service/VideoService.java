package com.lyy.service;

import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.VideoDTO;
import com.lyy.pojo.entity.Video;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-04-13 21:02
 */
public interface VideoService {

    /**
     * 保存
     * @param videoDTO
     * @return
     * @throws BussinessException
     */
    boolean save(VideoDTO videoDTO) throws BussinessException;

    /**
     * 删除
     * @param videoDTO
     * @return
     */
    boolean remove(VideoDTO videoDTO) throws BussinessException;

    /**
     * 更新
     * @param videoDTO
     * @return
     * @throws BussinessException
     */
    boolean update(VideoDTO videoDTO) throws BussinessException;

    /**
     * 按照课程ID与章节ID查询视频
     * @param videoDTO
     * @return
     */
    List<Video> queryAllByCourseAndSection(VideoDTO videoDTO);
}
