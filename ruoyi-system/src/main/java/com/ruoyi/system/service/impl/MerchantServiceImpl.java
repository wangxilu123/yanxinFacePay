package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.MerchantExample;
import com.ruoyi.system.mapper.MerchantMapper;
import com.ruoyi.system.service.MerchantService;

/**
 * 商户服务
 * @author wxl
 */
@Service
public class MerchantServiceImpl implements MerchantService
{
	
	@Autowired
    private MerchantMapper merchantMapper;

	@Override
	public List<Merchant> selectMerchantList() {
		
		MerchantExample example = new MerchantExample();
		example.setOrderByClause("id desc");
		List<Merchant> list = merchantMapper.selectByExample(example);
		return list;
	}
	 /**
         *分页查询
     */
	@Override
	public List<Merchant> selectMerchantList(Merchant merchant) {
		
		return merchantMapper.selectMerchantList(merchant);
	}
	@Override
	public int insertMerchant(Merchant merchant) {
		return merchantMapper.insert(merchant);
	}
	@Override
	public int deleteMerchantByIds(String ids) throws Exception {
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			merchantMapper.deleteByPrimaryKey(Integer.parseInt(id[i]));
		}
		return 1;
	}
	@Override
	public int updateMerchant(Merchant merchant) {
		return merchantMapper.updateByPrimaryKey(merchant);
	}
	@Override
	public Merchant selectMerchantById(Long merchantId) {
		return merchantMapper.selectByPrimaryKey(merchantId.intValue());
	}

	
	
}
