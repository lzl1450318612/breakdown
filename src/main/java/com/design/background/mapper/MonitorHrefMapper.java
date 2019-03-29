package com.design.background.mapper;

import com.design.background.entity.MonitorHref;

import java.util.List;

public interface MonitorHrefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorHref record);

    int insertSelective(MonitorHref record);

    MonitorHref selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorHref record);

    int updateByPrimaryKey(MonitorHref record);

    List<MonitorHref> selectAllHref();


    List<MonitorHref> selectTotalHref(MonitorHref monitorHref);
}