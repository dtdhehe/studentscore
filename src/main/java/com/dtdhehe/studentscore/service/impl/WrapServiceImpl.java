package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.mapper.WrapMapper;
import com.dtdhehe.studentscore.service.WrapService;
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
 * @date 2019/11/28 18:29
 * @description
 **/
@Service
public class WrapServiceImpl implements WrapService {

    @Autowired
    private WrapMapper wrapMapper;

    @Override
    public Wrap findById(String id) {
        return wrapMapper.findById(id);
    }

    @Override
    public Integer saveOrUpdate(Wrap wrap) {
        if (StringUtils.isEmpty(wrap.getId())){
            wrap.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(wrap);
            return wrapMapper.save(wrap);
        }else {
            Wrap oldWrap = wrapMapper.findById(wrap.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(wrap,oldWrap);
            oldWrap.setUpdateTime(DateUtils.formatDateTime2());
            return wrapMapper.update(oldWrap);
        }
    }

    @Override
    public Map<String, Object> queryWrap(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> wrapList = wrapMapper.queryWrap(queryMap);
        //修改日期格式
        for (Map<String,Object> item : wrapList){
            item.put("update_time",DateUtils.convertDateToViewType((String) item.get("update_time"),"datetime"));
        }
        //总条数
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(wrapList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",wrapList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        Wrap wrap = wrapMapper.findById(id);
        wrap.setValidFlag(ConstantUtils.NOTACTIVE);
        wrap.setUpdateTime(DateUtils.formatDateTime2());
        return wrapMapper.update(wrap);
    }

    @Override
    public List<Wrap> queryAll() {
        return wrapMapper.queryAll();
    }
}
