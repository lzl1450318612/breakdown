package com.design.background.controller.admin;

import com.design.background.entity.Breakdown;
import com.design.background.entity.MonitorHref;
import com.design.background.entity.SysUser;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.BreakdownService;
import com.design.background.service.MonitorHrefService;
import com.design.background.util.StringToDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description： 故障controller
 */
@Controller
@RequestMapping("/admin/breakdown")
public class BreakdownController {
    @Autowired
    private BreakdownService breakdownService;
    @Autowired
    private MonitorHrefService monitorHrefService;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * @Description: 用来展示故障列表的方法
     * @Param: [model, pageNo, pageSize, startTime, endTime, title]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/showList")
    public String showList(Model model,
                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                           @RequestParam(value = "createTimeStart", required = false) String createTimeStart,
                           @RequestParam(value = "createTimeEnd", required = false) String createTimeEnd,
                           @RequestParam(value = "handleTimeStart", required = false) String handleTimeStart,
                           @RequestParam(value = "handleTimeEnd", required = false) String handleTimeEnd,
                           @RequestParam(value = "description", required = false) String description)
    {
        Date createStart = null;
        Date createEnd = null;
        Date handleStart = null;
        Date handleEnd = null;

        Breakdown breakdown = new Breakdown();

        if (description != null && !"".equals(description.trim())) {
            breakdown.setDescription(description);
            model.addAttribute("description", description.trim());
        }
        if (createTimeStart != null && !"".equals(createTimeStart.trim())) {
            createStart = StringToDate.toUtilDate(createTimeStart);
            model.addAttribute("createTimeStart", createTimeStart.trim());
        }
        if (createTimeEnd != null && !"".equals(createTimeEnd.trim())) {
            createEnd = StringToDate.toUtilDate(createTimeEnd);
            model.addAttribute("createTimeEnd", createTimeEnd.trim());
        }
        if (handleTimeStart != null && !"".equals(handleTimeStart.trim())) {
            handleStart = StringToDate.toUtilDate(handleTimeStart);
            model.addAttribute("handleTimeStart", handleTimeStart.trim());
        }
        if (handleTimeEnd != null && !"".equals(handleTimeEnd.trim())) {
            handleEnd = StringToDate.toUtilDate(handleTimeEnd);
            model.addAttribute("handleTimeEnd", handleTimeEnd.trim());
        }

        PageHelper.startPage(pageNo, pageSize);
        List<Breakdown> breakdownList = breakdownService.getBreakdownList(breakdown,createStart,createEnd,handleStart,handleEnd);
        PageInfo<Breakdown> pageInfo = new PageInfo<Breakdown>(breakdownList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/breakdown/breakdownList";
    }


    /**
     * @Description: 用来删除故障信息的方法
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
            MonitorHref monitorHref = new MonitorHref();
            monitorHref.setId(breakdownService.selectByPrimaryKey(id[i]).getHrefId());
            monitorHref.setStatus(0);
            monitorHrefService.updateByPrimaryKeySelective(monitorHref);
            flag = breakdownService.deleteByPrimaryKey(id[i]);
            if(flag == 0)
            {
                return ResultData.setCodeAndMessage(0,"删除成功","删除失败");
            }
        }
        return ResultData.setCodeAndMessage(1,"删除成功","删除失败");
    }

    /**
     * @Description: 用来跳转到故障信息编辑页面的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/toedit/{id}")
    public String toEdit(Model model, @PathVariable("id")Long id)
    {
        Breakdown breakdown = breakdownService.selectByPrimaryKey(id);
        model.addAttribute("breakdown",breakdown);
        return "/admin/breakdown/edit_breakdown";
    }

    /**
     * @Description: 用来编辑故障信息的方法
     * @Param: [title, content, id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @ResponseBody
    @RequestMapping(value = "/edit", produces = "application/json")
    public ResultData edit(String description,Long id,Integer status,
                           @RequestParam(value = "handleInfo", required = false) String handleInfo)
    {
        Breakdown breakdown = new Breakdown();
        breakdown.setId(id);
        breakdown.setDescription(description);
        breakdown.setStatus(status);
        breakdown.setCreateTime(new Date());
        if(handleInfo != null && !"".equals(handleInfo.trim()))
        {
            breakdown.setHandleInfo(handleInfo);
        }

        Breakdown b =  breakdownService.selectByPrimaryKey(id);
        MonitorHref monitorHref = new MonitorHref();
        monitorHref.setId(b.getHrefId());

        if(status.equals(0))
        {
            monitorHref.setStatus(1);
        }
        else if(status.equals(1))
        {
            monitorHref.setStatus(0);
            breakdown.setHandleTime(new Date());
        }
        int result = breakdownService.updateByPrimaryKeySelective(breakdown);
        monitorHrefService.updateByPrimaryKeySelective(monitorHref);
        return ResultData.setCodeAndMessage(result,"修改成功","修改失败");
    }



    /**
     * @Description: 用来跳转到故障指派页面的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/topoint/{id}")
    public String toPoint(Model model, @PathVariable("id")Long id)
    {

        Breakdown breakdown = breakdownService.selectByPrimaryKey(id);
        List<SysUser> resultList = new ArrayList<>();
        SysUser sysUser = new SysUser();
        List<SysUser> sysUserList = userMapper.selectByUser(sysUser);
        for(SysUser s:sysUserList)
        {
            if(s.getRoles().get(0).getName().equals("维修人员"))
            {
                resultList.add(s);
            }
        }
        model.addAttribute("breakdownId",id);
        model.addAttribute("repairList",resultList);
        return "/admin/breakdown/point";
    }

    /**
     * @Description: 用来指派故障处理人员的方法
     * @Param: [title, content, id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @ResponseBody
    @RequestMapping(value = "/point", produces = "application/json")
    public ResultData point(Long repairId,Long id)
    {
        Breakdown breakdown = new Breakdown();
        breakdown.setHandlerId(repairId);
        breakdown.setStatus(2);
        breakdown.setId(id);
        int result = breakdownService.updateByPrimaryKeySelective(breakdown);

        return ResultData.setCodeAndMessage(result,"指派成功","指派失败");
    }

    /**
     * @Description: 用来催单的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @ResponseBody
    @RequestMapping(value = "/urge/{id}", produces = "application/json")
    public ResultData urge( @PathVariable("id")Long id)
    {
        Breakdown breakdown = new Breakdown();
        breakdown.setIsurged(true);
        breakdown.setId(id);
        int result = breakdownService.updateByPrimaryKeySelective(breakdown);
        return ResultData.setCodeAndMessage(result,"成功","失败");
    }

}
