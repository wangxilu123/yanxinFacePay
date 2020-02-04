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
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.service.MerchantService;

/**
 * 蜻蜓设备商户
 * @author wxl
 */
@Controller
@RequestMapping("/system/merchant")
public class SysMerchantController extends BaseController
{
    private String prefix = "system/merchant";

    @Autowired
    private MerchantService merchantService;

    @RequiresPermissions("system:merchant:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/merchant";
    }

    /**
         * 商户列表
     */
    @RequiresPermissions("system:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Merchant merchant)
    {
        startPage();
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        return getDataTable(list);
    }
    
    /**
     * 新增商户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户
     */
    @RequiresPermissions("system:merchant:add")
    @Log(title = "商户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Merchant merchant,@RequestParam("files") MultipartFile[] files)
    {
    	merchant.setCreateTime(new Date());
    	merchant.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(merchantService.insertMerchant(merchant));
    }
    
    
    /**
     * 修改商户
     */
    @GetMapping("/edit/{merchantId}")
    public String edit(@PathVariable("merchantId") Long merchantId, ModelMap mmap)
    {
        mmap.put("merchant", merchantService.selectMerchantById(merchantId));
        return prefix + "/edit";
    }

    /**
     * 修改保存商户
     */
    @RequiresPermissions("system:merchant:edit")
    @Log(title = "商户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Merchant merchant)
    {
    	merchant.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(merchantService.updateMerchant(merchant));
    }
    
    /**
     * 删除商户
     * @throws Exception 
     */
    @RequiresPermissions("system:merchant:remove")
    @Log(title = "商户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception
    {
        return toAjax(merchantService.deleteMerchantByIds(ids));
    }
    
}
