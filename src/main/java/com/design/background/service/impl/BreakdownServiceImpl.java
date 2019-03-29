package com.design.background.service.impl;

import com.design.background.entity.Breakdown;
import com.design.background.mapper.BreakdownMapper;
import com.design.background.service.BreakdownService;
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
public class BreakdownServiceImpl implements BreakdownService {

    @Autowired
    private BreakdownMapper breakdownMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return breakdownMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Breakdown record) {
        return breakdownMapper.insert(record);
    }

    @Override
    public int insertSelective(Breakdown record) {
        return breakdownMapper.insertSelective(record);
    }

    @Override
    public Breakdown selectByPrimaryKey(Long id) {
        return breakdownMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Breakdown record) {
        return breakdownMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Breakdown record) {
        return breakdownMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Breakdown> getBreakdownList(Breakdown breakdown, Date createTimeStart, Date createTimeEnd, Date handleTimeStart, Date handleTimeEnd) {
        return breakdownMapper.getBreakdownList(breakdown,createTimeStart,createTimeEnd,handleTimeStart,handleTimeEnd);
    }
}
