package com.ruoyi.web.controller.facePay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.Advertisement;
import com.ruoyi.system.service.AdvertisementService;

/**
 * 广告接口
 * @author wxl
 */
@Controller
@RequestMapping("/facePay/advertisement")
public class AdvertisementController extends BaseController
{
    @Autowired
    private AdvertisementService advertisementService;
    /**
         * 广告列表
     */
    @GetMapping("/list")
    @ResponseBody
    public List<Advertisement> list()
    {
        List<Advertisement> list = advertisementService.selectAdverList();
        return list;
    }

}
