package com.lyy.controller;

import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;
import com.lyy.common.StateCode;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CategoryDTO;
import com.lyy.pojo.vo.CategoryResponseVO;
import com.lyy.service.CategoryService;
import com.lyy.utils.ConverterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-23 22:01
 */
@Api(tags = "课程类别管理Api (V1版本)")
@RequestMapping("/v1/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ConverterUtil converterUtil;


    /**
     * 查询所有类别
     * @return
     */
    @ApiOperation(value = "查询所有类别信息", notes = "无参数")
    @ApiILog
    @GetMapping("/query")
    public CommonResponse<List<CategoryResponseVO>> doQueryAll() throws AppException{
        List<CategoryResponseVO> list = null;
        try {
            List<CategoryDTO> dtoList = categoryService.queryAll();
            list = converterUtil.convertList(dtoList, CategoryResponseVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_CATEGORY_QUERY_FAIL_ERROR, "类别信息查询失败");
        }
        return new CommonResponse<List<CategoryResponseVO>>(new ResponseHead(StateCode.SUCCEED_CODE, "类别信息查询成功"), new ResponseBody<List<CategoryResponseVO>>(list));
    }
}
