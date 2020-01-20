package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.Advertisement;
import com.ruoyi.system.domain.AdvertisementExample;
import com.ruoyi.system.mapper.AdvertisementMapper;
import com.ruoyi.system.service.AdvertisementService;

/**
 * 广告服务
 * @author wxl
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService
{
	
	@Autowired
    private AdvertisementMapper advertisementMapper;

	@Override
	public List<Advertisement> selectAdverList() {
		
		AdvertisementExample example = new AdvertisementExample();
		example.setOrderByClause("id desc");
		List<Advertisement> list = advertisementMapper.selectByExample(example);
		return list;
	}
	 /**
         *分页查询
     */
	@Override
	public List<Advertisement> selectAdvertisementList(Advertisement advertisement) {
		
		return advertisementMapper.selectAdvertisementList(advertisement);
	}
	@Override
	public int insertAd(Advertisement advertisement) {
		return advertisementMapper.insert(advertisement);
	}
	@Override
	public int updateAd(Advertisement advertisement) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteAdByIds(String ids) throws Exception {
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			advertisementMapper.deleteByPrimaryKey(Integer.parseInt(id[i]));
		}
		return 1;
	}

	
	
}
