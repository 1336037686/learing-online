package com.lyy.dao;

import com.lyy.pojo.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface CategoryDao {

    /**
     * 保存类别
     * @param category
     * @return
     */
    @Insert("insert into category values(#{id}, #{categoryName}, #{state})")
    int save(Category category);

    /**
     * 查询所有类别
     * @return
     */
    @Select("select * from category where state = 0")
    List<Category> queryAll();

    /**
     * 按照id查找类别
     * @param id
     * @return
     */
    @Select("select * from category where state = 0 and id = #{id}")
    Category queryById(String id);

    /**
     * 更新类别
     * @param category
     * @return
     */
    @Update("update category set category_name = #{categoryName} where id = #{id}")
    int update(Category category);

    /**
     * 删除类别
     * @param id
     * @return
     */
    @Update("update category set state = 1 where id = #{id}")
    int remove(String id);

}
