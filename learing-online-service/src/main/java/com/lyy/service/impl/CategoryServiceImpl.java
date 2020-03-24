package com.lyy.service.impl;

import com.lyy.dao.CategoryDao;
import com.lyy.pojo.dto.CategoryDTO;
import com.lyy.pojo.entity.Category;
import com.lyy.service.CategoryService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:54
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存类别信息
     * @param categoryDTO
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(CategoryDTO categoryDTO) throws Exception{
        categoryDao.save(new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getState()));
        return true;
    }

    /**
     * 查询所有类别信息
     * @return
     * @throws Exception
     */
    @Override
    public List<CategoryDTO> queryAll() throws Exception {
        List<Category> categoryList = categoryDao.queryAll();
        System.out.println(categoryList);
        List<CategoryDTO> categoryDTOList = converterUtil.convertList(categoryList, CategoryDTO.class);
        return categoryDTOList;
    }

    /**
     * 按照id查找类别
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public CategoryDTO queryById(String id) throws Exception {
        Category category = categoryDao.queryById(id);
        CategoryDTO categoryDTO = converterUtil.copyComplicatedObjectAndReturnNewOne(category, CategoryDTO.class);
        return categoryDTO;
    }

    /**
     * 更新类别信息
     * @param categoryDTO
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(CategoryDTO categoryDTO) throws Exception {
        categoryDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(categoryDTO, Category.class));
        return true;
    }

    /**
     * 删除类别信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean remove(String id) throws Exception {
        categoryDao.remove(id);
        return true;
    }


}
