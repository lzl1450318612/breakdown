package com.design.background.controller.admin;

import com.design.background.entity.Breakdown;
import com.design.background.entity.MonitorHref;
import com.design.background.service.BreakdownService;
import com.design.background.service.MonitorHrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description： 用来执行springBoot定时任务的controller
 */
@Component
public class Mission {
    @Autowired
    private MonitorHrefService monitorHrefService;
    @Autowired
    private BreakdownService breakdownService;

    public final static long ONE_Minute =  60 * 1000;

    /**
     * @Description: 用来定时检测链接异常的controller
     * @Param: []
     * @return: void
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){

        System.out.println("正在执行定时任务");
        List<MonitorHref> monitorHrefList = monitorHrefService.selectAllHref();
        for(MonitorHref m:monitorHrefList)
        {

            System.out.println(m.getHref());
            try {
                URL url = new URL(m.getHref());
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    int statusCode = httpURLConnection.getResponseCode();
                    System.out.println(statusCode);
                    // 如果返回的状态码显示异常
                    if(!((statusCode>=200 && statusCode<=206)||(statusCode>=300 && statusCode<=307)))
                    {
                        // 1代表异常，处理完成后重新置为0
                        m.setStatus(1);
                        monitorHrefService.updateByPrimaryKeySelective(m);
                        Breakdown breakdown = new Breakdown();
                        breakdown.setCreateTime(new Date());
                        // 0代表未处理
                        breakdown.setStatus(0);
                        breakdown.setStatuscode(statusCode);
                        breakdown.setDescription("服务器异常！状态码："+statusCode+"    Url:"+m.getHref());
                        breakdown.setHrefId(m.getId());
                        breakdownService.insertSelective(breakdown);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    m.setStatus(1);
                    monitorHrefService.updateByPrimaryKeySelective(m);
                    Breakdown breakdown = new Breakdown();
                    breakdown.setCreateTime(new Date());
                    // 0代表未处理
                    breakdown.setStatus(0);
                    breakdown.setStatuscode(404);
                    breakdown.setDescription("服务器异常！    Url:"+m.getHref());
                    breakdown.setHrefId(m.getId());
                    breakdownService.insertSelective(breakdown);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

}
