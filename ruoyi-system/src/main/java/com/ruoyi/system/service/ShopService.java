package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Shop;

/**
 * 门店服务层
 * 
 * @author wxl
 */
public interface ShopService
{
	 public Shop selectShopBydeviceId(String deviceId);
	 
	 public int insertShop(Shop shop);
	 
     public List<Shop> selectShopList();
	 
	 public List<Shop> selectShopList(Shop shop);
	 
     public int updateShop(Shop shop);
	 
	 public int deleteShopByIds(String ids) throws Exception;
	 
	 public Shop selectShopById(Long shopId);
}
