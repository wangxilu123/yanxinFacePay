package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.AgentUserService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.MerchantService;
import com.ruoyi.system.service.OrderFlowingService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    
    @Autowired
    private OrderFlowingService orderFlowingService;
    
    @Autowired
    private AgentUserService agentUserService;
    
    @Autowired
	private MerchantService merchantService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统首页统计
    @GetMapping("/system/main")
    public String main(ModelMap mmap){
    	
    	Calendar cale = Calendar.getInstance();
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String firstday, lastday;  
        // 获取前月的第一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());  
        // 获取前月的最后一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = format.format(cale.getTime());  
        
    	
    	Integer orderNumber=0;
    	
    	Double orderTotalAmount=0.00;
    	
    	Double orderGrossProfit=0.00;
    	
    	Double orderNetProfit=0.00;
    	
    	List<OrderFlowing> list = new ArrayList<OrderFlowing>();
        String roleType = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();
        
        if("管理员".equals(roleType)) {
        		//根据代理商查询所有交易流水
        	    OrderFlowing  orderFlowing = new OrderFlowing();
        	    Map<String, Object> map = new HashMap<String, Object>();
        	    map.put("beginTime", firstday);
        	    map.put("endTime", lastday);
        	    orderFlowing.setParams(map);
            	list = orderFlowingService.selectOrderFlowingList(orderFlowing);
            	orderNumber=list.size();
            	for(OrderFlowing orderFlow : list) {
            		//
            		AgentUser agentUser = agentUserService.selectAgById(orderFlow.getAgentUserId().longValue());
            		orderTotalAmount+=orderFlow.getOrderAmount().doubleValue();
            		orderGrossProfit+=orderFlow.getBenefitProfit().doubleValue();
            		orderNetProfit+=orderFlow.getBenefitProfit().doubleValue()*(1-agentUser.getSeparateProportion());
            	}
        }
        
		/*
		 * if("代理商".equals(roleType)) { AgentUser agentUser = new AgentUser();
		 * agentUser.setUserId(ShiroUtils.getUserId().intValue()); List<AgentUser>
		 * agentList = agentUserService.selectAgentUserList(agentUser);
		 * if(agentList.size()!=0) { //根据代理商查询所有交易流水
		 * orderFlow.setAgentUserId(agentList.get(0).getId()); list =
		 * orderFlowingService.selectOrderFlowingList(orderFlow); } }
		 * if("商户".equals(roleType)) {
		 * 
		 * Merchant merchant = new Merchant();
		 * merchant.setUserId(ShiroUtils.getUserId().intValue());
		 * 
		 * List<Merchant> merchantList = merchantService.selectMerchantList(merchant);
		 * if(merchantList.size()!=0) { //根据商户查询所有交易流水
		 * orderFlow.setMerchantId(merchantList.get(0).getId()); list =
		 * orderFlowingService.selectOrderFlowingList(orderFlow); }
		 * 
		 * }
		 */
        mmap.put("orderNumber",orderNumber);
        mmap.put("orderTotalAmount",orderTotalAmount);
        mmap.put("orderGrossProfit",orderGrossProfit);
        mmap.put("orderNetProfit",orderNetProfit);
        return "main_v1";
    }
}
