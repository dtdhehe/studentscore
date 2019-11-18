package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Subject;
import com.dtdhehe.studentscore.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/18 10:44
 * @description
 **/
@Service
public class SubjectServiceImpl implements SubjectService {
    @Override
    public Subject findById(String id) {
        return null;
    }

    @Override
    public Subject findByNo(String subjectNo) {
        return null;
    }

    @Override
    public Integer saveOrUpdate(Subject subject) {
        return null;
    }

    @Override
    public Map<String, Object> querySubject(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public Integer delete(String id) {
        return null;
    }
}
