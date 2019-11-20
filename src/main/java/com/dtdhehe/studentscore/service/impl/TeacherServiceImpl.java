package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.mapper.TeacherMapper;
import com.dtdhehe.studentscore.service.TeacherService;
import com.dtdhehe.studentscore.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 16:29
 * @description
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findById(String id) {
        return teacherMapper.findById(id);
    }

    @Override
    public Teacher findByTno(String tno) {
        return teacherMapper.findByTno(tno);
    }

    @Override
    public Integer saveOrUpdate(Teacher teacher) {
        return null;
    }

    @Override
    public Map<String, Object> queryTeacher(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> gradeList = teacherMapper.queryTeacher(queryMap);
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
        return null;
    }
}
