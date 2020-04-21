package com.lyy.controller;

import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;
import com.lyy.common.StateCode;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.DepartmentDTO;
import com.lyy.pojo.vo.DepartmentResponseVO;
import com.lyy.service.DepartmentService;
import com.lyy.utils.ConverterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
