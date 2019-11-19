package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Grade;
import com.dtdhehe.studentscore.mapper.GradeMapper;
import com.dtdhehe.studentscore.service.GradeService;
import com.dtdhehe.studentscore.util.BeansUtil;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/17 16:02
 * @description
 **/
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Grade findById(String id) {
        return gradeMapper.findById(id);
    }

    @Override
    public Grade findByNo(String majorId, Integer gradeNum) {
        return gradeMapper.findByNo(majorId,gradeNum);
    }

    @Override
    public Integer saveOrUpdate(Grade grade) {
        if (StringUtils.isEmpty(grade.getId())){
            grade.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(grade);
            return gradeMapper.save(grade);
        }else {
            Grade oldGrade = gradeMapper.findById(grade.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(grade,oldGrade);
            oldGrade.setUpdateTime(DateUtils.formatDateTime2());
            return gradeMapper.update(oldGrade);
        }
    }

    @Override
    public Map<String, Object> queryGrade(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> gradeList = gradeMapper.queryGrade(queryMap);
        //修改日期格式
        for (Map<String,Object> item : gradeList){
            //转换日期格式
            item.put("update_time",DateUtils.convertDateToViewType((String) item.get("update_time"),"datetime"));
        }
        //总条数
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(gradeList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",gradeList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        return gradeMapper.delete(id,DateUtils.formatDateTime2());
    }

    @Override
    public List<Grade> findByMajorId(String majorId) {
        if (!StringUtils.isEmpty(majorId)){
            return gradeMapper.findByMajorId(majorId);
        }
        return null;
    }
}
