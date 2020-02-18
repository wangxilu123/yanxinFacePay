package com.ruoyi.web.controller.system;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.ruoyi.common.utils.CodeUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.service.OrderFlowingService;
import com.ruoyi.system.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付接口
 * 
 * @author wangxilu
 * @version 1.0
 */
@Component
@Api(description = "支付相关接口")
@RequestMapping("/facePay/payments")
public class PaymentController {
	
	@Autowired
	private OrderFlowingService orderFlowingService;
	
	@Autowired
	private ShopService shopService;

	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	// 支付宝配置
	public static final String alipay_appId = "2021001105639700";// 支付宝APP应用ID

	public static final String alipay_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCs0PMXPN0BC6KZ61pcYHFYGOSLuMcrLykTkfipuN74v09TZUej2Q2m+gMt2s9UFmkKwQSpN/bDgJFMPzmqEGYq0zyb1PJ9rCvmL6L37rpP0+eymDcot33uALp3nUPiLxYyE9PpBhQinw6hOqIPiNS3/t0WVkftd1uZnJcn25TA58BI4S+t2Wl1AdNje5DW63YN3pReIuVHqOXN/FqFWz4HX70QCW7j1JH8QMLKjFHPFUftYKDIl7ttoNf68qTl2q5HUikRG2enWg4apxD+mGCM8wzg3+1kp4ZGaTE/cYJYp9Fc75vn5RdtjNPGHQ7Dxti59sxaNEMmnv5h65KdZxpPAgMBAAECggEAXjI+bmokZI98/7zDMhr3bPeUi3waQ7WfBCXKjmhQUPGmQWSxGeQThvI7jWD5JXJZqxzud3YjsQiBKjhELChoJ+uf26jEzC9udjd88vtS3nQ4lKZXWoA5yeLnwI8eE0lrJbydAyTnEL/173fQIfuJRwlKZQR+jDdE3IP3+Ce9+y7w8IMcZNc3R3f/zZJkHYgPsqCK/8EspSzZ9mDf+t7UAzUWuniqq+VQUHPsyrVWZmnTODsplKf0FzKsetUNzpQOnD57trD5Bdxe2i3lQPgYxPfVGRTX7pWuazjSWclDflyiwNZi/CbL35umSI6Z7+vW+1+1/Fwt1NPEQ/2a6QVzgQKBgQDl9sD9MiMKn0G80ZcP253AobZjQib15dY1SVb8EQyYUPkKeJwNk2ULRzDUOZVn2tDSeqMcya13FqMWvGxAekAlVD+t4pWyQ5FIrLDBOcv96JX8B1V1J+L5eoXtNbri/zZClSGHaDU0Y1KY/N9R04GT46rYprve64ypBp0tfq/LoQKBgQDAYdWS1/2L+oxPAGmHnkvLKRsfdp/JNgoiqBXShiVZD1Q1aTyVLlLNMrs+grsjQkehBROWByGWLCULLUkr/oupyLXrag5nTFNdgxVgBByJa1QfIhSEVqQ1L+/WuxmjqpEgCK6QM8RpUnCnBtZv8D5W8ffzjqXaqATWnM77vW+f7wKBgQDNYHynJDnLaMPrADwre3X97e7X4uKsxaFzYZZ/9DINbnOceG7WaQba9a+UOgFHCVNqRwCZ2zxCmUL6MTl0tDnJnN5qS2xqEpIUF7acOyQGFcytk1ctFHxPVq95VH0d8dPhzxJsvhKWQQYpf91qVLzq+W9BolczFac/5ZpGYVhbQQKBgQC4YUvLKB1F+kRdNxztMMadmPD4z71Gn/dIzeXUVmMXHzzqz8iThckwB040FCF9Inn6Fh52bLFA92AtfSKi+pwqhkXssUHKuPkxJ3/l/SrIfcgq6oPVbiCFJUNtfvaMyN28YPXFGQhGlQ+I1WHR+OQWemPU10OX8+5nLfSmiyGfmwKBgQDCoSCLvucfizCJsDcdbBMsOjCwoQvRdluboQx5FdcqR6Yn/ipLZtB/osAHW/6ndFEs5+Xz0GX91cYXd8+WpB/QUodaXWIl53O/tCaz7Dr/5oCnRWiDE1jTT5uuMfYYwiz+DvnGkGvw2e0h2P02FcwNXKKzgQx7LiKh0HZvbnCtZg==";// 支付宝私钥

	public static final String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArNDzFzzdAQuimetaXGBxWBjki7jHKy8pE5H4qbje+L9PU2VHo9kNpvoDLdrPVBZpCsEEqTf2w4CRTD85qhBmKtM8m9Tyfawr5i+i9+66T9Pnspg3KLd97gC6d51D4i8WMhPT6QYUIp8OoTqiD4jUt/7dFlZH7XdbmZyXJ9uUwOfASOEvrdlpdQHTY3uQ1ut2Dd6UXiLlR6jlzfxahVs+B1+9EAlu49SR/EDCyoxRzxVH7WCgyJe7baDX+vKk5dquR1IpERtnp1oOGqcQ/phgjPMM4N/tZKeGRmkxP3GCWKfRXO+b5+UXbYzTxh0Ow8bYufbMWjRDJp7+YeuSnWcaTwIDAQAB";// 支付宝公钥

	/**
	 * 支付宝统一下单
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "支付宝支付接口",nickname = "支付宝支付接口")
	@RequestMapping(value = "/aliPay", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String aliPay(HttpServletRequest request,HttpServletResponse response) throws Exception{
			    //实例化客户端
		        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId, alipay_private_key , "json", "UTF-8", alipay_public_key, "RSA2");
			    
		        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		        AlipayTradePayRequest aliRequest = new AlipayTradePayRequest();
		        
		        String orderNum = DateUtils.dateTimeNow() + CodeUtil.genCodes(6);
		        
		        String product_code= request.getParameter("product_code");
		        
		        String deviceId= request.getParameter("deviceId");
		        
		        String payType= request.getParameter("payType");
		        
		        String orderAmount= request.getParameter("orderAmount");
		        
		        Shop shop = shopService.selectShopBydeviceId(Long.parseLong(deviceId));
		        
		        aliRequest.setBizContent("{" +
		        		"\"out_trade_no\":"+orderNum+"," +
		        		"\"scene\":\"bar_code\"," +
		        		"\"auth_code\":"+product_code+"," +
		        		"\"subject\":\"蜻蜓设备交易订单\"," +
		        		"  }");
		        		AlipayTradePayResponse aliresponse = alipayClient.execute(aliRequest);
		        		if(aliresponse.isSuccess()){
		        		System.out.println("调用成功");
		        		
		        		OrderFlowing orderFlowing = new OrderFlowing();
		        		
		        		orderFlowing.setMerchantName(shop.getMerchatName());
		        		orderFlowing.setMerchantId(shop.getMerchatId());
		        		orderFlowing.setOrderAmount(new BigDecimal(orderAmount));
		        		orderFlowing.setOrderNum(orderNum);
		        		orderFlowing.setPayType(payType);
		        		orderFlowing.setServiceCharge(null);
		        		orderFlowing.setBenefitProfit(null);
		        		orderFlowing.setShopId(shop.getId());
		        		orderFlowing.setShopName(shop.getShopName());
		        		
		        		orderFlowingService.insertOrderFlowing(orderFlowing);
		        		
		        		} else {
		        		System.out.println("调用失败");
		        		}
						return null;
	}

	// 将request中的参数转换成Map
	private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();
		Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

		for (Entry<String, String[]> entry : entrySet) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			int valLen = values.length;

			if (valLen == 1) {
				retMap.put(name, values[0]);
			} else if (valLen > 1) {
				StringBuilder sb = new StringBuilder();
				for (String val : values) {
					sb.append(",").append(val);
				}
				retMap.put(name, sb.toString().substring(1));
			} else {
				retMap.put(name, "");
			}
		}

		return retMap;
	}
}
