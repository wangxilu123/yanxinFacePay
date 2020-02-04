package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.AgentUserExample;
import com.ruoyi.system.mapper.AgentUserMapper;
import com.ruoyi.system.service.AgentUserService;

/**
 * 代理商服务
 * @author wxl
 */
@Service
public class AgentUserServiceImpl implements AgentUserService
{
	
	@Autowired
    private AgentUserMapper agentUserMapper;

	@Override
	public List<AgentUser> selectAgentUserList() {
		
		AgentUserExample example = new AgentUserExample();
		example.setOrderByClause("id desc");
		List<AgentUser> list = agentUserMapper.selectByExample(example);
		return list;
	}
	 /**
         *分页查询
     */
	@Override
	public List<AgentUser> selectAgentUserList(AgentUser agentUser) {
		
		return agentUserMapper.selectAgentUserList(agentUser);
	}
	@Override
	public int insertAg(AgentUser agentUser) {
		return agentUserMapper.insert(agentUser);
	}
	@Override
	public int deleteAgByIds(String ids) throws Exception {
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			agentUserMapper.deleteByPrimaryKey(Integer.parseInt(id[i]));
		}
		return 1;
	}
	@Override
	public int updateAg(AgentUser agentUser) {
		return agentUserMapper.updateByPrimaryKey(agentUser);
	}
	@Override
	public AgentUser selectAgById(Long agentUserId) {
		return agentUserMapper.selectByPrimaryKey(agentUserId.intValue());
	}

	
	
}
