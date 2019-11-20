package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Subject;
import com.dtdhehe.studentscore.mapper.SubjectMapper;
import com.dtdhehe.studentscore.service.SubjectService;
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
 * @date 2019/11/18 10:44
 * @description
 **/
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Subject findById(String id) {
        return subjectMapper.findById(id);
    }

    @Override
    public Subject findByNo(String subjectNo) {
        return subjectMapper.findByNo(subjectNo);
    }

    @Override
    public Integer saveOrUpdate(Subject subject) {
        if (StringUtils.isEmpty(subject.getId())){
            subject.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(subject);
            return subjectMapper.save(subject);
        }else {
            Subject oldSubject = subjectMapper.findById(subject.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(subject,oldSubject);
            oldSubject.setUpdateTime(DateUtils.formatDateTime2());
            return subjectMapper.update(oldSubject);
        }
    }

    @Override
    public Map<String, Object> querySubject(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Subject> subjectList = subjectMapper.querySubject(queryMap);
        //修改日期格式
        for (Subject item : subjectList){
            item.setUpdateTime(DateUtils.convertDateToViewType(item.getUpdateTime(),"datetime"));
        }
        //总条数
        PageInfo<Subject> pageInfo = new PageInfo<>(subjectList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",subjectList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        Subject subject = subjectMapper.findById(id);
        subject.setValidFlag(ConstantUtils.NOTACTIVE);
        subject.setUpdateTime(DateUtils.formatDateTime2());
        return subjectMapper.update(subject);
    }
}
