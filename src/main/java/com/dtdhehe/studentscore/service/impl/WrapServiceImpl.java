package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.mapper.WrapMapper;
import com.dtdhehe.studentscore.service.WrapService;
import com.dtdhehe.studentscore.util.BeansUtil;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
