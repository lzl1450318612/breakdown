package com.design.background.controller.admin;

import com.design.background.entity.Knowledge;
import com.design.background.entity.SysUser;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.KnowledgeService;
import com.design.background.util.StringToDate;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/4
 * @Description： 用于故障知识库的controller
 */
@Controller
@RequestMapping("/admin/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * @Description: 用来展示知识列表的方法
     * @Param: [model, pageNo, pageSize, startTime, endTime, title]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/showList")
    public String showList(Model model,
                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "title", required = false) String title)
    {
        Date start = null;
        Date end = null;

        Knowledge knowledge = new Knowledge();

        if (title != null && !"".equals(title.trim())) {
            knowledge.setTitle(title.trim());
            model.addAttribute("title", title.trim());
        }
        if (startTime != null && !"".equals(startTime.trim())) {
            start = StringToDate.toUtilDate(startTime);
            model.addAttribute("startTime", startTime.trim());
        }
        if (endTime != null && !"".equals(endTime.trim())) {
            end = StringToDate.toUtilDate(endTime);
            model.addAttribute("endTime", endTime.trim());
        }

        PageHelper.startPage(pageNo, pageSize);
        List<Knowledge> knowledgeList = knowledgeService.getKnowledgeList(knowledge,start,end);
        PageInfo<Knowledge> pageInfo = new PageInfo<Knowledge>(knowledgeList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/knowledge/knowledgeList";
    }

    /**
     * @Description: 用来删除知识库信息的方法
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
            flag = knowledgeService.deleteByPrimaryKey(id[i]);
            if(flag == 0)
            {
                return ResultData.setCodeAndMessage(0,"删除成功","删除失败");
            }
        }
        return ResultData.setCodeAndMessage(1,"删除成功","删除失败");
    }


    /**
     * @Description: 跳转到添加知识信息界面的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/13
     */
    @RequestMapping("/toadd")
    public String toAdd()
    {
        return "/admin/knowledge/add_knowledge";
    }




    /**
     * @Description: 用来添加知识库信息的方法
     * @Param: [title, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/addKnowledge", produces = "application/json")
    public ResultData addKnowledge(String title,String content)
    {
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = sysUserMapper.selectByUsername(loginUserName);
        Knowledge knowledge = new Knowledge();
        knowledge.setTitle(title);
        knowledge.setContent(content);
        knowledge.setWriterId(loginUser.getId());
        knowledge.setCreateTime(new Date());

        int result = knowledgeService.insertSelective(knowledge);
        return ResultData.setCodeAndMessage(result,"添加成功","添加失败");
    }


    /**
     * @Description: 用来跳转到知识库编辑页面的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/toedit/{id}")
    public String toEdit(Model model, @PathVariable("id")Long id)
    {
        Knowledge knowledge = knowledgeService.selectByPrimaryKey(id);
        model.addAttribute("knowledge",knowledge);
        return "/admin/knowledge/edit_knowledge";
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
    public ResultData edit(String title,String content,Long id)
    {
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = sysUserMapper.selectByUsername(loginUserName);

        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        knowledge.setTitle(title);
        knowledge.setCreateTime(new Date());
        knowledge.setContent(content);
        knowledge.setWriterId(loginUser.getId());
        int result = knowledgeService.updateByPrimaryKeySelective(knowledge);
        return ResultData.setCodeAndMessage(result,"修改成功","修改失败");
    }


}
