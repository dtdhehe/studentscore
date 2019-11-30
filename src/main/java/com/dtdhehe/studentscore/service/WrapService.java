package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Wrap;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/28 17:56
 * @description
 **/
public interface WrapService {

    /**
     * 根据id查找对象
     * @param id
     * @return
     */
    Wrap findById(String id);

    /**
     * 新增或修改
     * @param wrap
     * @return
     */
    Integer saveOrUpdate(Wrap wrap);

    /**
     * 根据条件查询轮播图信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryWrap(Map<String,Object> queryMap);

    /**
     * 根据id删除轮播图
     * @param id
     * @return
     */
    Integer delete(String id);

}
