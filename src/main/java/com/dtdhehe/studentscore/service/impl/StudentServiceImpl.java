package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/18 22:32
 * @description
 **/
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Student findById(String id) {
        return null;
    }

    @Override
    public Student findBySno(String sno) {
        return null;
    }

    @Override
    public Integer saveOrUpdate(Student student) {
        return null;
    }

    @Override
    public Map<String, Object> queryStudent(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public Integer delete(String id) {
        return null;
    }

    @Override
    public List<Student> findByGradeId(String id) {
        return null;
    }
}
