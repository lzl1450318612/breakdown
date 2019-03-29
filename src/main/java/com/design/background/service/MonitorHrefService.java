package com.design.background.service;

import com.design.background.entity.MonitorHref;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description：
 */
public interface MonitorHrefService {
    int deleteByPrimaryKey(Long id);

    int insert(MonitorHref record);

    int insertSelective(MonitorHref record);

    MonitorHref selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorHref record);

    int updateByPrimaryKey(MonitorHref record);

    /**
     * @Description: 查找所有正常的链接
     * @Param:
     * @return:
     * @Author: 李紫林
     * @Date: 2019/3/5
     */
    List<MonitorHref> selectAllHref();


    /**
     * @Description: 查找全部链接，包括不正常的
     * @Param:
     * @return:
     * @Author: 李紫林
     * @Date: 2019/3/5
     */
    List<MonitorHref> selectTotalHref(MonitorHref monitorHref);
}
