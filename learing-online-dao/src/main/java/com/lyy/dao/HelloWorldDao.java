package com.lyy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-23 16:22
 */
@Mapper
public interface HelloWorldDao {

    @Select("select * from test")
    public List<String> hello();

}
