package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Score;
import com.dtdhehe.studentscore.mapper.ScoreMapper;
import com.dtdhehe.studentscore.service.ScoreService;
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
 * @author Xie_东
 * @version 1.0.0
 * @date 2019/11/25 11:09
 * @description
 **/
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public Map<String, Object> queryScore(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> scoreList = scoreMapper.queryScore(queryMap);
        //总条数
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(scoreList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",scoreList);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryScoreForEdit(String tid, String tsid) {
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("tid",tid);
        queryMap.put("tsid",tsid);
        List<Map<String,Object>> scoreList = scoreMapper.queryScore(queryMap);
        if (scoreList.size() != 0){
            return scoreList.get(0);
        }
        return null;
    }

    @Override
    public Integer saveOrUpdate(Score score) {
        if (StringUtils.isEmpty(score.getId())){
            score.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(score);
            return scoreMapper.save(score);
        }else {
            Score oldScore = scoreMapper.findById(score.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(score,oldScore);
            oldScore.setUpdateTime(DateUtils.formatDateTime2());
            return scoreMapper.update(oldScore);
        }
    }

    @Override
    public Integer delete(String id) {
        Score score = scoreMapper.findById(id);
        score.setValidFlag(ConstantUtils.NOTACTIVE);
        score.setUpdateTime(DateUtils.formatDateTime2());
        return scoreMapper.update(score);
    }
}
