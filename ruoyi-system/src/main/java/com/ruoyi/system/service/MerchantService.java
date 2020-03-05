package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Merchant;

/**
 *商户服务层
 * 
 * @author wxl
 */
public interface MerchantService
{
	
	 public List<Merchant> selectMerchantList();
	 
	 public List<Merchant> selectMerchantList(Merchant merchant);
	 
	 public int insertMerchant(Merchant merchant);
	 
	 public int updateMerchant(Merchant merchant);
	 
	 public int deleteMerchantByIds(String ids) throws Exception;
	 
	 public Merchant selectMerchantById(Long merchantId);
	 
	 public Merchant selectMerchantByappId(String appid);
}
