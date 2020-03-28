package com.lyy.controller;

import com.lyy.common.ResponseBody;
import com.lyy.common.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.AppException;
import com.lyy.log.annotation.ApiILog;
import com.lyy.pojo.dto.CategoryDTO;
import com.lyy.pojo.vo.CategoryQueryVO;
import com.lyy.pojo.vo.CategoryResponseVO;
import com.lyy.service.CategoryService;
import com.lyy.utils.ConverterUtil;
import com.lyy.utils.SnowFlakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 保存课程类别信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存课程类别信息", notes = "需要类别名称（必填信息）")
    @ApiILog
    @PostMapping("/save")
    public CommonResponse<String> doSave(@org.springframework.web.bind.annotation.RequestBody CommonRequest<CategoryQueryVO> vo) throws AppException {
        try {
            categoryService.save(new CategoryDTO(SnowFlakeUtil.generateId() + "", vo.getBody().getData().getCategoryName(), "0"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_CATEGORY_SAVE_FAIL_ERROR, "类别信息保存失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "类别信息添加成功"), new ResponseBody<String>("类别信息添加成功"));
    }

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

    /**
     * 按照id查找类别
     * @return
     */
    @ApiOperation(value = "按照id查询所有类别信息", notes = "id(必填)")
    @ApiILog
    @GetMapping("/query/{id}")
    public CommonResponse<CategoryResponseVO> doQueryById(@PathVariable("id") String id) throws AppException{
        CategoryResponseVO vo = null;
        try {
            CategoryDTO dto = categoryService.queryById(id);
            vo = converterUtil.copyPropertiesAndReturnNewOne(dto, CategoryResponseVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_CATEGORY_QUERY_FAIL_ERROR, "类别信息查询失败");
        }
        return new CommonResponse<CategoryResponseVO>(new ResponseHead(StateCode.SUCCEED_CODE, "类别信息查询成功"), new ResponseBody<CategoryResponseVO>(vo));
    }

    /**
     * 更新课程类别信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "修改课程类别信息", notes = "id,类别名称，状态必填")
    @ApiILog
    @PutMapping("/update")
    public CommonResponse<String> doUpdate(@org.springframework.web.bind.annotation.RequestBody CommonRequest<CategoryQueryVO> vo) throws AppException {
        try {
            categoryService.update(converterUtil.copyComplicatedObjectAndReturnNewOne(vo.getBody().getData(), CategoryDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_CATEGORY_UPDATE_FAIL_ERROR, "类别信息更新失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "类别信息更新成功"), new ResponseBody<String>("类别信息更新成功"));
    }

    /**
     * 保存课程类别信息
     * @param vo
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "删除课程类别信息", notes = "id（必填）")
    @ApiILog
    @DeleteMapping("/remove")
    public CommonResponse<String> doRemove(@org.springframework.web.bind.annotation.RequestBody CommonRequest<CategoryQueryVO> vo) throws AppException {
        try {
            categoryService.remove(vo.getBody().getData().getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.SERVICE_CATEGORY_DELETE_FAIL_ERROR, "类别信息删除失败");
        }
        return new CommonResponse<String>(new ResponseHead(StateCode.SUCCEED_CODE, "类别信息删除成功"), new ResponseBody<String>("类别信息删除成功"));
    }


}
