package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.OrderFlowingExample;
import com.ruoyi.system.mapper.OrderFlowingMapper;
import com.ruoyi.system.service.OrderFlowingService;

/**
 * 交易流水服务
 * @author wxl
 */
@Service
public class OrderFlowingServiceImpl implements OrderFlowingService
{
	
	@Autowired
    private OrderFlowingMapper orderFlowingMapper;

	@Override
	public List<OrderFlowing> selectOrderFlowingList() {
		
		OrderFlowingExample example = new OrderFlowingExample();
		example.setOrderByClause("id desc");
		List<OrderFlowing> list = orderFlowingMapper.selectByExample(example);
		return list;
	}
	 /**
         *分页查询
     */
	@Override
	public List<OrderFlowing> selectOrderFlowingList(OrderFlowing orderFlowing) {
		
		return orderFlowingMapper.selectOrderFlowingList(orderFlowing);
	}
	@Override
	public int insertOrderFlowing(OrderFlowing orderFlowing) {
		return orderFlowingMapper.insert(orderFlowing);
	}
	@Override
	public int deleteOrderFlowingByIds(String ids) throws Exception {
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			orderFlowingMapper.deleteByPrimaryKey(Integer.parseInt(id[i]));
		}
		return 1;
	}
	@Override
	public int updateOrderFlowing(OrderFlowing orderFlowing) {
		return orderFlowingMapper.updateByPrimaryKey(orderFlowing);
	}
	@Override
	public OrderFlowing selectOrderFlowingById(Long orderFlowingId) {
		return orderFlowingMapper.selectByPrimaryKey(orderFlowingId.intValue());
	}

	
	
}
