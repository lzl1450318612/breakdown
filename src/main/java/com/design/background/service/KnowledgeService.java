package com.design.background.service;

import com.design.background.entity.Knowledge;

        import java.util.Date;
        import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description：
 */
public interface KnowledgeService {
    int deleteByPrimaryKey(Long id);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    Knowledge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);

    List<Knowledge> getKnowledgeList(Knowledge knowledge, Date startTime, Date endTime);
}
