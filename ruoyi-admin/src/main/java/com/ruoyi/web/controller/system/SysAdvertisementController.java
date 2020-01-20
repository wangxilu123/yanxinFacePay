package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ruoyi.system.domain.Advertisement;
import com.ruoyi.system.service.AdvertisementService;

/**
 * 蜻蜓设备广告类
 * 
 * @author wxl
 */
@Controller
@RequestMapping("/system/advertisement")
public class SysAdvertisementController extends BaseController
{
    private String prefix = "system/advertisement";

    @Autowired
    private AdvertisementService advertisementService;

    @RequiresPermissions("system:advertisement:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/advertisement";
    }

    /**
         * 蜻蜓设备广告列表
     */
    @RequiresPermissions("system:advertisement:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Advertisement advertisement)
    {
        startPage();
        List<Advertisement> list = advertisementService.selectAdvertisementList(advertisement);
        return getDataTable(list);
    }
    
    /**
     * 新增广告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告
     */
    @RequiresPermissions("system:advertisement:add")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, Advertisement advertisement)
    {
    	advertisement.setCreateTime(new Date());
        advertisement.setCreater(ShiroUtils.getLoginName());
        return toAjax(advertisementService.insertAd(advertisement));
    }
    
    /**
     * 删除广告
     * @throws Exception 
     */
    @RequiresPermissions("system:advertisement:remove")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception
    {
        return toAjax(advertisementService.deleteAdByIds(ids));
    }
    
}
