package com.lyy.controller;

import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.DepartmentDTO;
import com.lyy.pojo.vo.DepartmentQueryVO;
import com.lyy.pojo.vo.DepartmentResponseVO;
import com.lyy.service.DepartmentService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学校院系管理 控制层
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "学校院系管理Api (V1版本)")
@RequestMapping("/v1/department")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存院系信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存院系信息", notes = "需要类别名称（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<DepartmentQueryVO> vo) throws AppException {
        try {
            boolean result = departmentService.save(new DepartmentDTO(SnowFlakeUtil.generateId() + "", vo.getBody().getData().getName(), "0"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_DEPARTMENT_SAVE_FAIL_ERROR, "院系信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "院系信息添加成功"), new ResponseBody<String>("院系信息添加成功"));
    }

    /**
     * 查询所有类别
     * @return
     */
    @ApiOperation(value = "查询所有院系信息", notes = "无参数")
    @ApiILog
    @GetMapping("/query")
    public CommonResponse<List<DepartmentResponseVO>> doQueryAll() throws AppException{
        List<DepartmentResponseVO> list = null;
        try {
            List<DepartmentDTO> dtoList = departmentService.queryAll();
            list = converterUtil.convertList(dtoList, DepartmentResponseVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_DEPARTMENT_QUERY_FAIL_ERROR, "院系信息查询失败");
        }
        return new CommonResponse<List<DepartmentResponseVO>>(new ResponseHead(StateCode.SUCCEED_CODE, "院系信息查询成功"), new ResponseBody<List<DepartmentResponseVO>>(list));
    }

    /**
     * 按照id查找类别
     * @return
     */
    @ApiOperation(value = "按照id查询院系信息", notes = "id(必填)")
    @ApiILog
    @GetMapping("/query/{id}")
    public CommonResponse<DepartmentResponseVO> doQueryById(@PathVariable("id") String id) throws AppException{
        DepartmentResponseVO vo = null;
        try {
            DepartmentDTO dto = departmentService.queryById(id);
            vo = converterUtil.copyPropertiesAndReturnNewOne(dto, DepartmentResponseVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_DEPARTMENT_QUERY_FAIL_ERROR, "院系信息查询失败");
        }
        return new CommonResponse<DepartmentResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "院系信息查询成功"), new ResponseBody<DepartmentResponseVO>(vo));
    }

    /**
     * 更新课程院系信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "修改院系信息", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<DepartmentQueryVO> vo) throws AppException {
        try {
            departmentService.update(converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), DepartmentDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_DEPARTMENT_UPDATE_FAIL_ERROR, "院系信息更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "院系信息更新成功"), new ResponseBody<String>("院系信息更新成功"));
    }

    /**
     * 保存课程院系信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "删除院系信息", notes = "id（必填）")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<DepartmentQueryVO> vo) throws AppException {
        try {
            departmentService.remove(vo.getBody().getData().getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_DEPARTMENT_DELETE_FAIL_ERROR, "院系信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "院系信息删除成功"), new ResponseBody<String>("院系信息删除成功"));
    }


}
