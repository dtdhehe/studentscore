package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.TeachSubject;
import com.dtdhehe.studentscore.mapper.TeachSubjectMapper;
import com.dtdhehe.studentscore.service.TeachSubjectService;
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
 * @date 2019/11/24 16:31
 * @description
 **/
@Service
public class TeachSubjectServiceImpl implements TeachSubjectService {

    @Autowired
    private TeachSubjectMapper teachSubjectMapper;

    @Override
    public TeachSubject findById(String id) {
        return teachSubjectMapper.findById(id);
    }

    @Override
    public TeachSubject findBySubjectIdAndGradeId(String subjectId, String gradeId) {
        return teachSubjectMapper.findBySubjectIdAndGradeId(subjectId, gradeId);
    }

    @Override
    public Integer saveOrUpdate(TeachSubject teachSubject) {
        if (StringUtils.isEmpty(teachSubject.getId())){
            teachSubject.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(teachSubject);
            return teachSubjectMapper.save(teachSubject);
        }else {
            TeachSubject oldTeachSubject = teachSubjectMapper.findById(teachSubject.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(teachSubject,oldTeachSubject);
            oldTeachSubject.setUpdateTime(DateUtils.formatDateTime2());
            return teachSubjectMapper.update(oldTeachSubject);
        }
    }

    @Override
    public Map<String, Object> queryTeachSubject(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> teachSubjectList = teachSubjectMapper.queryTeachSubject(queryMap);
        //修改日期格式
        for (Map<String,Object> item : teachSubjectList){
            item.put("update_time",DateUtils.convertDateToViewType((String) item.get("update_time"),"datetime"));
        }
        //总条数
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(teachSubjectList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",teachSubjectList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        TeachSubject teachSubject = teachSubjectMapper.findById(id);
        teachSubject.setValidFlag(ConstantUtils.NOTACTIVE);
        teachSubject.setUpdateTime(DateUtils.formatDateTime2());
        return teachSubjectMapper.update(teachSubject);
    }
}
