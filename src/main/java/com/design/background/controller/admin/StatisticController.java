package com.design.background.controller.admin;

import com.design.background.entity.Breakdown;
import com.design.background.model.StatisticModel;
import com.design.background.service.BreakdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/8
 * @Description： 用于统计分析故障的controller
 */
@Controller
public class StatisticController {
    @Autowired
    private BreakdownService breakdownService;


    /**
     * @Description: 用于获取统计分析信息的方法
     * @Param: []
     * @return: com.design.background.model.StatisticModel
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @ResponseBody
    @RequestMapping(value = "/admin/statisticgetinfo", produces = "application/json")
    public StatisticModel getStatisticinfo()
    {

        List<Breakdown> breakdownList = breakdownService.getBreakdownList(new Breakdown(),null,null,null,null);
        List<Integer> countList = new ArrayList<>();
        List<Integer> statusList = new ArrayList<>();

        for(Breakdown breakdown:breakdownList)
        {
            boolean flag = false;
            if(breakdown.getStatuscode() != null)
            {
                for(int i = 0;i<statusList.size();i++)
                {
                    if(statusList.get(i).equals(breakdown.getStatuscode()))
                    {
                        countList.set(i,countList.get(i)+1);
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                {
                    statusList.add(breakdown.getStatuscode());
                    countList.add(1);
                }
            }
        }
        return new StatisticModel(countList,statusList);
    }



    @RequestMapping(value = "/admin/statistic")
    public String getStatistic()
    {
        return "/admin/statistic/statistic";
    }
}
