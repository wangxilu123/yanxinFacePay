package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Advertisement;

/**
 * 广告 服务层
 * 
 * @author wxl
 */
public interface AdvertisementService
{
	
	 public List<Advertisement> selectAdverList();
	 
	 public List<Advertisement> selectAdvertisementList(Advertisement advertisement);
	 
	 public int insertAd(Advertisement advertisement);
	 
	 public int updateAd(Advertisement advertisement);
	 
	 public int deleteAdByIds(String ids) throws Exception;
}
