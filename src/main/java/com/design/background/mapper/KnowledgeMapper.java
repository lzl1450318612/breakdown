package com.design.background.mapper;

import com.design.background.entity.Knowledge;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    Knowledge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);

    List<Knowledge> selectKnowledgeList(@Param("knowledge") Knowledge knowledge,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}