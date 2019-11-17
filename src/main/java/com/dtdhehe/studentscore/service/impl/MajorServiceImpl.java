package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Major;
import com.dtdhehe.studentscore.mapper.MajorMapper;
import com.dtdhehe.studentscore.service.MajorService;
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
 * @date 2019/11/14 17:35
 * @description
 **/
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public Major findById(String id) {
        return majorMapper.findById(id);
    }

    @Override
    public Major findByNo(String majorNo) {
        return majorMapper.findByNo(majorNo);
    }

    @Override
    public Integer saveOrUpdate(Major major) {
        if (StringUtils.isEmpty(major.getId())){
            major.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(major);
            return majorMapper.save(major);
        }else {
            Major oldMajor = majorMapper.findById(major.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(major,oldMajor);
            oldMajor.setUpdateTime(DateUtils.formatDateTime2());
            return majorMapper.update(oldMajor);
        }
    }

    @Override
    public Map<String, Object> queryMajor(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Major> departmentList = majorMapper.queryMajor(queryMap);
        //修改日期格式
        for (Major item : departmentList){
            item.setUpdateTime(DateUtils.convertDateToViewType(item.getUpdateTime(),"datetime"));
        }
        //总条数
        PageInfo<Major> pageInfo = new PageInfo<>(departmentList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",departmentList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        return majorMapper.delete(id,DateUtils.formatDateTime2());
    }

    @Override
    public List<Major> findByDepartmentId(String id) {
        if (!StringUtils.isEmpty(id)){
            return majorMapper.findByDepartmentId(id);
        }
        return null;
    }
}
