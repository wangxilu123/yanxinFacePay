package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.domain.ShopExample;
import com.ruoyi.system.mapper.ShopMapper;
import com.ruoyi.system.service.ShopService;

/**
 * 门店服务
 * @author wxl
 */
@Service
public class ShopServiceImpl implements ShopService
{
	
	@Autowired
    private ShopMapper shopMapper;

	@Override
	public Shop selectShopBydeviceId(Long deviceId) {
		
		ShopExample example = new ShopExample();
		example.createCriteria().andDeviceIdEqualTo(String.valueOf(deviceId));
		
		List<Shop> shopList = shopMapper.selectByExample(example);
		return shopList.get(0);
	}

	@Override
	public int insertShop(Shop shop) {
		return shopMapper.insert(shop);
	}

	@Override
	public List<Shop> selectShopList() {
		ShopExample example = new ShopExample();
		
		example.setOrderByClause("id desc");
		
		List<Shop> shopList = shopMapper.selectByExample(example);
		
		return shopList;
	}

	@Override
	public List<Shop> selectShopList(Shop shop) {
		return shopMapper.selectShopList(shop);
	}

	@Override
	public int updateShop(Shop shop) {
		return shopMapper.updateByPrimaryKey(shop);
	}

	@Override
	public int deleteShopByIds(String ids) throws Exception {
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			shopMapper.deleteByPrimaryKey(Integer.parseInt(id[i]));
		}
		return 1;
	}

	@Override
	public Shop selectShopById(Long shopId) {
		return shopMapper.selectByPrimaryKey(shopId.intValue());
	}

	
	
}
