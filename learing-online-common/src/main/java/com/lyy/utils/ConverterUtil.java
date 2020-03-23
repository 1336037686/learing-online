package com.lyy.utils;

import com.alibaba.fastjson.JSON;
import com.lyy.common.CommonResponse;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 将各种类型的对象进行转换
 * @author LGX_TvT
 * @date 2019-12-18 11:12
 */
@Component
public class ConverterUtil {

    public ConverterUtil(){}
    /**
     * 将两个对象属性进行同步，每条属性必须有着相同的名字
     * @param src 源对象
     * @param target 目标对象
     */
    public void copyProperties(Object src,Object target){
        if (src==null||target==null){
            throw new BussinessException(ErrorCode.SYSTEM_CONVERTER_ERROR, "数据转换错误");
        }
        BeanUtils.copyProperties(src, target);
    }

    /**
     * 复制属性到指定类，同时返回拷贝对象
     * @param src
     * @param target
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T copyPropertiesAndReturnNewOne(Object src,Class<T> target) {
        check(src,target);
        try {
            T newOne = target.newInstance();
            BeanUtils.copyProperties(src,newOne);
            return newOne;
        }catch (Exception e){
            throw new BussinessException(ErrorCode.SYSTEM_CONVERTER_ERROR, "数据转换错误");
        }
    }

    /**
     * 复杂对象拷贝
     * @param src
     * @param target
     * @param <T>
     * @return
     */
    public <T> T copyComplicatedObjectAndReturnNewOne(Object src,Class<T> target) {
        check(src,target);
        return JSON.parseObject(JSON.toJSONString(src),target);
    }

    private void check(Object src,Class<?> target){
        if (src==null||target==null){
            throw new BussinessException(ErrorCode.SYSTEM_CONVERTER_ERROR, "数据转换错误");
        }
    }
    /**
     * 将json格式的数据转为指定类型的对象
     * @param src json数据
     * @param targetClass 指定对象Class
     * @return Object，需要进行强转
     */
    public <T> T getObjectFromJson(String src,Class<T> targetClass) {
        return JSON.parseObject(src,targetClass);
    }

    /**
     * 将生对象转换成熟对象
     * @param rawObject 要转换的对象，@RequestBody里是以LinkedHashMap作为存储，里面Key为属性名,value为值。
     * @param targetClass 要转换的对象类文件
     * @return 转换完成后的对象
     */
    public <T> T getObjectFromJson(Object rawObject,Class<T> targetClass){
        String object=JSON.toJSONString(rawObject);
        return JSON.parseObject(object,targetClass);
    }


    /**
     * 从CommonResponse中获取指定类
     * @param commonResponse
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T parseCommonResponse(CommonResponse commonResponse, Class<T> targetClass){
        return JSON.parseObject(JSON.toJSONString(commonResponse.getBody().getData()),targetClass);
    }

    /**
     * 将List中的pojo进行转换。比如List<DO>转成List<DTO>
     * @param list list
     * @param targetClass 目标类型
     * @return 转换完成的List
     * @throws IllegalAccessException 此类不能被直接访问
     * @throws InstantiationException 实例化异常，比如此类没有无参构造函数
     */
    public <T,K> List<K> convertList(List<T> list,Class<K> targetClass) {
        if (list.size()==0||targetClass==null){
            return new ArrayList<K>();
        }
        List<K> resultList = new ArrayList<>();
        for (T t : list){
            K target;
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                throw new BussinessException(ErrorCode.SYSTEM_CONVERTER_ERROR, "数据转换错误");
            }
            copyProperties(t,target);
            resultList.add(target);
        }
        return resultList;
    }

}
