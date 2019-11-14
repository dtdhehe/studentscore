package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Major;
import com.dtdhehe.studentscore.mapper.MajorMapper;
import com.dtdhehe.studentscore.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
