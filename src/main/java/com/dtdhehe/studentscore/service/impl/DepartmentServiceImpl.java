package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Department;
import com.dtdhehe.studentscore.mapper.DepartmentMapper;
import com.dtdhehe.studentscore.service.DepartmentService;
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
 * @author Xie_东
 * @version 1.0.0
 * @date 2019/11/9 23:32
 * @description
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department findByNo(String departmentNo) {
        return departmentMapper.findByNo(departmentNo);
    }

    @Override
    public Integer saveOrUpdate(Department department) {
        if (StringUtils.isEmpty(department.getId())){
            department.setId(ConstantUtils.getUniqueKey());
            department.setValidFlag(ConstantUtils.ACTIVE);
            department.setCreateTime(DateUtils.formatDateTime2());
            department.setUpdateTime(DateUtils.formatDateTime2());
            return departmentMapper.save(department);
        }else {

        }
        return null;
    }

    @Override
    public Map<String,Object> queryDepartment(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Department> departmentList = departmentMapper.queryDepartment(queryMap);
        //总条数
        PageInfo<Department> pageInfo = new PageInfo<>(departmentList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",departmentList);
        return resultMap;
    }
}
