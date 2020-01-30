package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.AgentUser;

/**
 * 代理商服务层
 * 
 * @author wxl
 */
public interface AgentUserService
{
	
	 public List<AgentUser> selectAgentUserList();
	 
	 public List<AgentUser> selectAgentUserList(AgentUser agentUser);
	 
	 public int insertAg(AgentUser agentUser);
	 
	 public int updateAg(AgentUser agentUser);
	 
	 public int deleteAgByIds(String ids) throws Exception;
	 
	 public AgentUser selectAgById(Long agentUserId);
}
