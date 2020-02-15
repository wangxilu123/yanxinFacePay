package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.OrderFlowing;

/**
 * 交易流水服务层
 * 
 * @author wxl
 */
public interface OrderFlowingService
{
	
	 public List<OrderFlowing> selectOrderFlowingList();
	 
	 public List<OrderFlowing> selectOrderFlowingList(OrderFlowing orderFlowing);
	 
	 public int insertOrderFlowing(OrderFlowing orderFlowing);
	 
	 public int updateOrderFlowing(OrderFlowing orderFlowing);
	 
	 public int deleteOrderFlowingByIds(String ids) throws Exception;
	 
	 public OrderFlowing selectOrderFlowingById(Long orderFlowingId);
}
