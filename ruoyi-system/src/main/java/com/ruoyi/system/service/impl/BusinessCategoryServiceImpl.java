package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.BusinessCategory;
import com.ruoyi.system.domain.BusinessCategoryExample;
import com.ruoyi.system.mapper.BusinessCategoryMapper;
import com.ruoyi.system.service.BusinessCategoryService;

/**
 * 类目字典
 * @author wxl
 */
@Service
public class BusinessCategoryServiceImpl implements BusinessCategoryService
{
	
	@Autowired
    private BusinessCategoryMapper businessCategoryMapper;

	@Override
	public List<BusinessCategory> selectOneCategoryList() {
		BusinessCategoryExample example = new BusinessCategoryExample();
		
		example.createCriteria().andOneCategoryNameIsNotNull();
		
		return businessCategoryMapper.selectByExample(example);
	}

	@Override
	public AgentUser selectAgById(Long agentUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessCategory> selectTwoCategoryList(String OneCategoryCode) {
		
        BusinessCategoryExample example = new BusinessCategoryExample();
		
		example.createCriteria().andTwoCategoryCodeLike(OneCategoryCode);
		
		return businessCategoryMapper.selectByExample(example);
	}

	@Override
	public List<BusinessCategory> selectThreeCategoryList(String TwoCategoryCode) {
		
        BusinessCategoryExample example = new BusinessCategoryExample();
		
		example.createCriteria().andThreeCategoryCodeLike(TwoCategoryCode);
		
		return businessCategoryMapper.selectByExample(example);
	}

}
