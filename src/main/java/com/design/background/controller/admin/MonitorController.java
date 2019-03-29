package com.design.background.controller.admin;

import com.design.background.entity.MonitorHref;
import com.design.background.model.ResultData;
import com.design.background.service.MonitorHrefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/5
 * @Description： 用来管理监控url的controller
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorController {
    @Autowired
    private MonitorHrefService monitorHrefService;


    /**
     * @Description: 展示监控url列表的方法
     * @Param: [model, pageNo, pageSize, href]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @RequestMapping("/showList")
    public String showList(Model model,
                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                           @RequestParam(value = "href", required = false) String href)
    {
        MonitorHref monitorHref = new MonitorHref();

        if (href != null && !"".equals(href.trim())) {
            monitorHref.setHref(href);
            model.addAttribute("href", href.trim());
        }

        PageHelper.startPage(pageNo, pageSize);
        List<MonitorHref> monitorHrefList = monitorHrefService.selectTotalHref(monitorHref);
        PageInfo<MonitorHref> pageInfo = new PageInfo<MonitorHref>(monitorHrefList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/monitor/monitorList";
    }



    /**
     * @Description: 用来删除监控URL的方法
     * @Param: [id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public ResultData delete(@RequestBody Long[] id)
    {
        int flag = 0;
        // 循环遍历传过来需要删除的id数组
        for(int i = 0;i < id.length; i++) {
            flag = monitorHrefService.deleteByPrimaryKey(id[i]);
            if(flag == 0)
            {
                return ResultData.setCodeAndMessage(0,"删除成功","删除失败");
            }
        }
        return ResultData.setCodeAndMessage(1,"删除成功","删除失败");
    }



    /**
     * @Description: 用于跳转到添加监控url界面的方法
     * @Param: []
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @RequestMapping("/toadd")
    public String toAdd()
    {
        return "/admin/monitor/add_monitor";
    }




    /**
     * @Description: 用来添加监控URL的方法
     * @Param: [title, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/addMonitor", produces = "application/json")
    public ResultData addMonitor(String href)
    {
        MonitorHref monitorHref = new MonitorHref();
        monitorHref.setHref(href);
        monitorHref.setStatus(0);

        int result = monitorHrefService.insertSelective(monitorHref);
        return ResultData.setCodeAndMessage(result,"添加成功","添加失败");
    }




    /**
     * @Description: 用来跳转到监控URL编辑页面的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/toedit/{id}")
    public String toEdit(Model model, @PathVariable("id")Long id)
    {
        MonitorHref monitorHref = monitorHrefService.selectByPrimaryKey(id);
        model.addAttribute("monitorHref",monitorHref);
        return "/admin/monitor/edit_monitor";
    }


    /**
     * @Description: 用来编辑知识库信息的方法
     * @Param: [title, content, id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @ResponseBody
    @RequestMapping(value = "/edit", produces = "application/json")
    public ResultData edit(String href,Long id)
    {
        MonitorHref monitorHref = new MonitorHref();
        monitorHref.setId(id);
        monitorHref.setHref(href);
        int result = monitorHrefService.updateByPrimaryKeySelective(monitorHref);
        return ResultData.setCodeAndMessage(result,"修改成功","修改失败");
    }
}
