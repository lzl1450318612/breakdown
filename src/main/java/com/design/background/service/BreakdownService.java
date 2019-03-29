package com.design.background.service;



import com.design.background.entity.Breakdown;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description：
 */
public interface BreakdownService {
    int deleteByPrimaryKey(Long id);

    int insert(Breakdown record);

    int insertSelective(Breakdown record);

    Breakdown selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Breakdown record);

    int updateByPrimaryKey(Breakdown record);


    /**
     * @Description: 获取指定的故障列表
     * @Param:
     * @return:
     * @Author: 李紫林
     * @Date: 2019/3/5
     */
    List<Breakdown> getBreakdownList(Breakdown breakdown, Date createTimeStart,Date createTimeEnd,Date handleTimeStart,Date handleTimeEnd);
}
