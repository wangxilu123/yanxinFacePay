package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.AgentUserExample;

public interface AgentUserMapper {
    long countByExample(AgentUserExample example);

    int deleteByExample(AgentUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentUser record);

    int insertSelective(AgentUser record);

    List<AgentUser> selectByExample(AgentUserExample example);

    AgentUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentUser record, @Param("example") AgentUserExample example);

    int updateByExample(@Param("record") AgentUser record, @Param("example") AgentUserExample example);

    int updateByPrimaryKeySelective(AgentUser record);

    int updateByPrimaryKey(AgentUser record);
    
    public List<AgentUser> selectAgentUserList(AgentUser agentUser);
}