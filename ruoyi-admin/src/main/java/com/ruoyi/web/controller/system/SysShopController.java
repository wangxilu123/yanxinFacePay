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
import com.ruoyi.common.utils.OSSClientUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.service.ShopService;

/**
 * 门店服务
 * @author wxl
 */
@Controller
@RequestMapping("/system/shop")
public class SysShopController extends BaseController
{
    private String prefix = "system/shop";

    @Autowired
    private ShopService shopService;
    
    @Autowired
	private OSSClientUtil ossClient;

    @RequiresPermissions("system:shop:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/shop";
    }

    /**
         * 门店列表
     */
    @RequiresPermissions("system:shop:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Shop shop)
    {
        startPage();
        List<Shop> list = shopService.selectShopList(shop);
        return getDataTable(list);
    }
    
    /**
     * 新增门店
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门店
     * @throws Exception 
     */
    @RequiresPermissions("system:shop:add")
    @Log(title = "门店管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Shop shop,@RequestParam("file") MultipartFile file) throws Exception
    {
		if(file!=null){
                	String name = ossClient.uploadImg2Oss(file);
            	    String imgUrl = ossClient.getImgUrl(name);
            	    shop.setLogoUrl(imgUrl);
        }
		shop.setCreateTime(new Date());
		shop.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(shopService.insertShop(shop));
    }
    
    
    /**
     * 修改门店
     */
    @GetMapping("/edit/{shopId}")
    public String edit(@PathVariable("shopId") Long shopId, ModelMap mmap)
    {
        mmap.put("shop", shopService.selectShopById(shopId));
        return prefix + "/edit";
    }

    /**
     * 修改保存门店
     */
    @RequiresPermissions("system:shop:edit")
    @Log(title = "门店管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Shop shop)
    {
        return toAjax(shopService.updateShop(shop));
    }
    
    /**
     * 删除门店
     * @throws Exception 
     */
    @RequiresPermissions("system:shop:remove")
    @Log(title = "门店管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception
    {
        return toAjax(shopService.deleteShopByIds(ids));
    }
    
}
