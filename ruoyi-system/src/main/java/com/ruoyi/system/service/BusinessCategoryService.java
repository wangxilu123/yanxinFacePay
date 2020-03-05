package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.BusinessCategory;

/**
 * 类目字典
 * 
 * @author wxl
 */
public interface BusinessCategoryService
{
	
	 public List<BusinessCategory> selectOneCategoryList();
	 
	 public List<BusinessCategory> selectTwoCategoryList(String OneCategoryCode);
	 
	 public List<BusinessCategory> selectThreeCategoryList(String TwoCategoryCode);
	 
	 public AgentUser selectAgById(Long agentUserId);
	 
	 
}
