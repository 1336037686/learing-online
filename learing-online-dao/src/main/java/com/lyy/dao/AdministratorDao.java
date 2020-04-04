package com.lyy.dao;

import com.lyy.pojo.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员Dao
 * @author LGX_TvT
 * @date 2020-03-24 0:55
 */
@Mapper
public interface AdministratorDao {


    /**
     * 按照账号查找
     * @param userName
     * @return
     */
    @Select("select * from administrator where state = 0 and user_name = #{userName}")
    Administrator queryByUserName(String userName);

}
