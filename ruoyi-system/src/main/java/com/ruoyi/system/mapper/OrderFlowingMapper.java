package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.OrderFlowingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFlowingMapper {
    long countByExample(OrderFlowingExample example);

    int deleteByExample(OrderFlowingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderFlowing record);

    int insertSelective(OrderFlowing record);

    List<OrderFlowing> selectByExample(OrderFlowingExample example);

    OrderFlowing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderFlowing record, @Param("example") OrderFlowingExample example);

    int updateByExample(@Param("record") OrderFlowing record, @Param("example") OrderFlowingExample example);

    int updateByPrimaryKeySelective(OrderFlowing record);

    int updateByPrimaryKey(OrderFlowing record);
    
    public List<OrderFlowing> selectOrderFlowingList(OrderFlowing orderFlowing);
}