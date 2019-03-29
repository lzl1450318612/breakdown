package com.design.background.controller.admin;

import com.design.background.entity.Breakdown;
import com.design.background.entity.MonitorHref;
import com.design.background.model.ResultData;
import com.design.background.service.BreakdownService;
import com.design.background.service.MonitorHrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author： 李紫林
 * @Date： 2019/3/7
 * @Description： 用户投诉controller
 */
@Controller
@RequestMapping("/admin/complain")
public class ComplainController {
    @Autowired
    private BreakdownService breakdownService;
    @Autowired
    private MonitorHrefService monitorHrefService;


    /**
     * @Description: 用于跳转到用户投诉界面的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @RequestMapping("/toaddcomplain")
    public String toAddComplain()
    {
        return "/admin/breakdown/complain";
    }

    /**
     * @Description: 用户添加一个故障
     * @Param: [url, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @ResponseBody
    @RequestMapping(value = "/addcomplain", produces = "application/json")
    public ResultData addcomplain(String url,String content)
    {
        // 先向监控链接表中插入用户提交的故障链接，便于日后修好后监测
        MonitorHref monitorHref = new MonitorHref();
        monitorHref.setHref(url);
        monitorHref.setStatus(1);
        monitorHrefService.insertSelective(monitorHref);
        System.out.println("========"+monitorHref.getId());
        // 再向故障列表中插入故障信息
        Breakdown breakdown = new Breakdown();
        breakdown.setHrefId(monitorHref.getId());
        breakdown.setStatus(0);
        breakdown.setCreateTime(new Date());
        breakdown.setDescription(content+"    Url:"+url);
        int result = breakdownService.insertSelective(breakdown);
        return ResultData.setCodeAndMessage(result,"添加成功","添加失败");
    }
}
