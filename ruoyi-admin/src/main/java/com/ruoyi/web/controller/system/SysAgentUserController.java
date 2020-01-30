package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.service.AgentUserService;

/**
 * 蜻蜓设备代理商类
 * @author wxl
 */
@Controller
@RequestMapping("/system/agentUser")
public class SysAgentUserController extends BaseController
{
    private String prefix = "system/agentUser";

    @Autowired
    private AgentUserService agentUserService;

    @RequiresPermissions("system:agentUser:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/agentUser";
    }

    /**
         * 代理商列表
     */
    @RequiresPermissions("system:agentUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AgentUser agentUser)
    {
        startPage();
        List<AgentUser> list = agentUserService.selectAgentUserList(agentUser);
        return getDataTable(list);
    }
    
    /**
     * 新增代理商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代理商
     */
    @RequiresPermissions("system:agentUser:add")
    @Log(title = "代理商管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AgentUser agentUser)
    {
    	agentUser.setCreateTime(new Date());
    	agentUser.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(agentUserService.insertAg(agentUser));
    }
    
    
    /**
     * 修改代理商
     */
    @GetMapping("/edit/{agentUserId}")
    public String edit(@PathVariable("agentUserId") Long agentUserId, ModelMap mmap)
    {
        mmap.put("agentUser", agentUserService.selectAgById(agentUserId));
        return prefix + "/edit";
    }

    /**
     * 修改保存代理商
     */
    @RequiresPermissions("system:agentUser:edit")
    @Log(title = "代理商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated AgentUser agentUser)
    {
    	agentUser.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(agentUserService.updateAg(agentUser));
    }
    
    /**
     * 删除代理商
     * @throws Exception 
     */
    @RequiresPermissions("system:agentUser:remove")
    @Log(title = "代理商管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception
    {
        return toAjax(agentUserService.deleteAgByIds(ids));
    }
    
}
