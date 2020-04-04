package com.lyy.controller;

import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.SpecialtyDTO;
import com.lyy.pojo.entity.Specialty;
import com.lyy.pojo.vo.SpecialtyQueryVO;
import com.lyy.pojo.vo.SpecialtyResponseVO;
import com.lyy.service.SpecialtyService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学校专业管理 控制层
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "学校专业管理Api (V1版本)")
@RequestMapping("/v1/specialty")
@RestController
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存专业信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存专业信息", notes = "需要类别名称（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<SpecialtyQueryVO> vo) throws AppException {
        try {
            SpecialtyDTO specialtyDTO = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), SpecialtyDTO.class);

            // 添加id
            specialtyDTO.setId(SnowFlakeUtil.generateId() + "");
            //添加状态
            specialtyDTO.setState("0");

            boolean result = specialtyService.save(specialtyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SPECIALTY_SAVE_FAIL_ERROR, "专业信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "专业信息添加成功"), new ResponseBody<String>("专业信息添加成功"));
    }

    /**
     * 分页查找管理员公告信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页查找管理员公告信息", notes = "需要页号，长度范围.")
    @ApiILog
    @PostMapping("/query")
    public CommonResponse<SpecialtyResponseVO> doQueryAll(@org.springframework.web.bind.annotation.RequestBody CommonRequest<SpecialtyQueryVO> vo) throws AppException {
        SpecialtyDTO dto = converterUtil.copyPropertiesAndReturnNewOne(vo.getBody().getData(), SpecialtyDTO.class);
        SpecialtyResponseVO specialtyResponseVO = null;
        try {
            SpecialtyDTO specialtyDTO = specialtyService.queryAll(dto);
            specialtyResponseVO = converterUtil.copyPropertiesAndReturnNewOne(specialtyDTO, SpecialtyResponseVO.class);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "专业信息查找失败");
        }
        return new CommonResponse<SpecialtyResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "专业信息查找成功"), new ResponseBody<SpecialtyResponseVO>(specialtyResponseVO));
    }

    /**
     * 查找所有专业信息
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "查找全部专业信息", notes = "")
    @ApiILog
    @GetMapping("/query")
    public CommonResponse<List<Specialty>> doQueryAll() throws AppException {
        List<Specialty> specialtyList = null;
        try {
            specialtyList = specialtyService.queryAll();
        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR, "管理公告信息查找失败");
        }
        return new CommonResponse<List<Specialty>>(new ResponseHead(StateCode.SUCCEED_CODE, "管理员公告信息查找成功"), new ResponseBody<List<Specialty>>(specialtyList));
    }


    /**
     * 更新课程专业信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "修改专业信息", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<SpecialtyQueryVO> vo) throws AppException {
        try {
            specialtyService.update(converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), SpecialtyDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SPECIALTY_UPDATE_FAIL_ERROR, "专业信息更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "专业信息更新成功"), new ResponseBody<String>("专业信息更新成功"));
    }

    /**
     * 保存课程专业信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "删除专业信息", notes = "id（必填）")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<SpecialtyQueryVO> vo) throws AppException {
        try {
            specialtyService.remove(vo.getBody().getData().getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_SPECIALTY_DELETE_FAIL_ERROR, "专业信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "专业信息删除成功"), new ResponseBody<String>("专业信息删除成功"));
    }
}
