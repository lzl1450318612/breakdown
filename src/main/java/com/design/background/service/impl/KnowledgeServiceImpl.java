package com.design.background.service.impl;

import com.design.background.entity.Knowledge;
import com.design.background.mapper.KnowledgeMapper;
import com.design.background.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description：
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return knowledgeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Knowledge record) {
        return knowledgeMapper.insert(record);
    }

    @Override
    public int insertSelective(Knowledge record) {
        return knowledgeMapper.insertSelective(record);
    }

    @Override
    public Knowledge selectByPrimaryKey(Long id) {
        return knowledgeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Knowledge record) {
        return knowledgeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Knowledge record) {
        return knowledgeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Knowledge> getKnowledgeList(Knowledge knowledge, Date startTime, Date endTime) {
        return knowledgeMapper.selectKnowledgeList(knowledge,startTime,endTime);
    }
}
