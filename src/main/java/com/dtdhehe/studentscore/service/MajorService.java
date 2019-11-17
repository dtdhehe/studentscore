package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Major;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/14 17:32
 * @description
 **/
public interface MajorService {

    /**
     * 根据id查找专业
     * @param id
     * @return
     */
    Major findById(String id);

    /**
     * 根据专业编号查找专业
     * @param majorNo
     * @return
     */
    Major findByNo(String majorNo);

    /**
     * 新增/修改专业
     * @param major
     * @return
     */
    Integer saveOrUpdate(Major major);

    /**
     * 根据条件查询专业信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryMajor(Map<String,Object> queryMap);

    /**
     * 删除专业
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 根据学院id查询所有专业
     * @param id
     * @return
     */
    List<Major> findByDepartmentId(String id);

}
