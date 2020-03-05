package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.service.AgentUserService;
import com.ruoyi.system.service.MerchantService;
import com.ruoyi.system.service.OrderFlowingService;

/**
 * 交易流水
 * @author wxl
 */
@Controller
@RequestMapping("/system/orderFlow")
public class SysOrderFlowingController extends BaseController
{
    private String prefix = "system/orderFlow";

    @Autowired
    private OrderFlowingService orderFlowingService;
    
    @Autowired
    private MerchantService merchantService;
    
    @Autowired
    private AgentUserService agentUserService;
    
    @RequiresPermissions("system:orderFlow:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/orderFlow";
    }

    /**
         * 交易流水列表
     */
    @RequiresPermissions("system:orderFlow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderFlowing orderFlow)
    {
        startPage();
        List<OrderFlowing> list = new ArrayList<OrderFlowing>();
        String roleType = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        
        if("管理员".equals(roleType)) {
        		//根据代理商查询所有交易流水
            	list = orderFlowingService.selectOrderFlowingList(orderFlow);
        }
        
        if("代理商".equals(roleType)) {
        	AgentUser agentUser = new AgentUser();
        	agentUser.setUserId(ShiroUtils.getUserId().intValue());
        	List<AgentUser> agentList = agentUserService.selectAgentUserList(agentUser);
        	if(agentList.size()!=0) {
        		//根据代理商查询所有交易流水
            	orderFlow.setAgentUserId(agentList.get(0).getId());
            	list = orderFlowingService.selectOrderFlowingList(orderFlow);
        	} 
        }
        if("商户".equals(roleType)) {
        	
        	Merchant merchant = new Merchant();
        	merchant.setUserId(ShiroUtils.getUserId().intValue());
        	
        	List<Merchant> merchantList = merchantService.selectMerchantList(merchant);
        	if(merchantList.size()!=0) {
        		//根据商户查询所有交易流水
            	orderFlow.setMerchantId(merchantList.get(0).getId());
            	list = orderFlowingService.selectOrderFlowingList(orderFlow);
        	}
        	
        }
        return getDataTable(list);
    }
}
