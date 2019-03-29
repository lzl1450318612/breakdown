package com.design.background.controller.admin;

import com.design.background.entity.Breakdown;
import com.design.background.entity.MonitorHref;
import com.design.background.entity.SysUser;
import com.design.background.mapper.SysUserMapper;
import com.design.background.mapper.UserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.BreakdownService;
import com.design.background.service.MonitorHrefService;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/8
 * @Description： 用于维修人员故障管理的controller
 */
@Controller
@RequestMapping("/admin/mybreakdown")
public class MyBreakdownController {

    @Autowired
    private BreakdownService breakdownService;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private MonitorHrefService monitorHrefService;


    /**
     * @Description: 用于展示维修人员个人被指派故障列表的方法
     * @Param: [model, pageNo, pageSize]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @RequestMapping("/showList")
    public String showList(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
    {
        Breakdown breakdown = new Breakdown();
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = userMapper.selectByUsername(loginUserName);
        breakdown.setHandlerId(loginUser.getId());

        PageHelper.startPage(pageNo, pageSize);
        List<Breakdown> breakdownList = breakdownService.getBreakdownList(breakdown,null,null,null,null);
        PageInfo<Breakdown> pageInfo = new PageInfo<Breakdown>(breakdownList);
        model.addAttribute("pageInfo", pageInfo);
        return "/admin/mybreakdown/breakdownlist";
    }


    /**
     * @Description: 用来解决故障的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @ResponseBody
    @RequestMapping(value = "/ok/{id}", produces = "application/json")
    public ResultData ok(@PathVariable("id")Long id)
    {
        Breakdown breakdown = new Breakdown();
        breakdown.setId(id);
        breakdown.setStatus(1);
        breakdown.setHandleTime(new Date());

        Breakdown b = breakdownService.selectByPrimaryKey(id);

        MonitorHref monitorHref = new MonitorHref();
        monitorHref.setStatus(0);
        monitorHref.setId(b.getHrefId());

        monitorHrefService.updateByPrimaryKeySelective(monitorHref);
        int result = breakdownService.updateByPrimaryKeySelective(breakdown);


        return ResultData.setCodeAndMessage(result,"成功","失败");
    }

}
