package com.design.background.mapper;

import com.design.background.entity.Breakdown;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BreakdownMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Breakdown record);

    int insertSelective(Breakdown record);

    Breakdown selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Breakdown record);

    int updateByPrimaryKey(Breakdown record);

    List<Breakdown> getBreakdownList(@Param("breakdown") Breakdown breakdown, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("handleTimeStart") Date handleTimeStart, @Param("handleTimeEnd") Date handleTimeEnd);

}