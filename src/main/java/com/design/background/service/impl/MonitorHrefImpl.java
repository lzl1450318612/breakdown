package com.design.background.service.impl;

import com.design.background.entity.MonitorHref;
import com.design.background.mapper.MonitorHrefMapper;
import com.design.background.service.MonitorHrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description：
 */
@Service
public class MonitorHrefImpl implements MonitorHrefService {

    @Autowired
    private MonitorHrefMapper monitorHrefMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return monitorHrefMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MonitorHref record) {
        return monitorHrefMapper.insert(record);
    }

    @Override
    public int insertSelective(MonitorHref record) {
        return monitorHrefMapper.insertSelective(record);
    }

    @Override
    public MonitorHref selectByPrimaryKey(Long id) {
        return monitorHrefMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MonitorHref record) {
        return monitorHrefMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MonitorHref record) {
        return monitorHrefMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MonitorHref> selectAllHref() {
        return monitorHrefMapper.selectAllHref();
    }

    @Override
    public List<MonitorHref> selectTotalHref(MonitorHref monitorHref) {
        return monitorHrefMapper.selectTotalHref(monitorHref);
    }
}
