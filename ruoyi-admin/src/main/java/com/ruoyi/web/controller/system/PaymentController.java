package com.ruoyi.web.controller.system;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.ruoyi.common.utils.CodeUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.service.MerchantService;
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
	
	@Autowired
	private MerchantService merchantService;

	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	// 支付宝配置
	public static final String alipay_appId = "2021001108678896";// 支付宝APP应用ID

	public static final String alipay_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDBwcIwmqtuTmGpIcwiKIzBWXdfSbCBWukbDBMJUN19eYdvMV79/lJMYWLY9o+8hs0JfYJkL5ckHB5BIxOISY4zgM5NV17dj7CenAnGsyTy9P7cmMAh+FyVk7G+GTvncapzEZ1ZJLmVwcO0tlkyVCYtUuXZ0cUSNHWFYeHMzmgO0VgGUBhJiOPFDtwvBApllJ3cV2O0aiM5hkXHfqmckVv9UgQ/G5fW9UyhBZaqOh81xnZ2qePFPAuubJCYZyZ6K4l/V31eI0XA/wRVjNAviP8NDHctixnQPfkrafLkd9LbqMxrhFuW/3oNFrwsKuMTbG9x/OPu5vRWWeRNxFVsnJxXAgMBAAECggEALaaKK0t10qJzDhdhcjbdmvyKTJAfHwsdWvsITSyZUuPcTg1y1SMjns1fLcFB8mhMTM0eeJ5h3OcnFa++/WBfObFCaiSIvGkLcwNOpls0/G1O0wjGU/qhTbijqydk3s4AG+ZtjUBC3Y7unlhMQbhj2k/qydxLs1Na0RdTzR5glATDKKCaDNaB3ToOj8TihO8bbt7I8G3ZdfL+VCvU55A62TDu/AJjmGgesrLBT9evt8neKDf3PadqA/eKCu0aimCNjIB7G7kbsn7rJsbkn4u6I5QKGN8WpomjlsqYQN+jzWrxSWzQjX8i3IUjLGkdMnQBV5VtvjfJX8tPYl2wHbIs2QKBgQD62Aqm8633DH3F1R1COug6jytlUXB1vdYC4FmhakbugM9M11kGOrzHeT74/6RsWFONp/EEBIdbCV6hZKnUInWnsqoKiE6oXWW7L15u0pmATj/WbiYoFxoz0yLxZpr/CZeZSTCHcozgDA3RW70eoY780O/onz83MaGszqZS89PVDQKBgQDFvVIl04Q8nudFCou9k1gkiGfly8rxT+lsBEWPKVXxhDT9dBr50Gq8piseeiPn9eB/wpFKsfS9uC18CPZyKrJAnYJVGvZc9/1DrtwTCRJqi/bkzo+tRhM0xEm9m8X4U7zVOSOIoqdqxobXdQXOM1dkNkSERzDn14gqzV66HRil8wKBgCmWAYYWJgQsFPnkT/p366IxtB8S9lL+yuklKpbhGKtXvYZeuUqYGE31ouQC9c5kgk2cxw9EdPA5yG93UdRydhD8RMaEPI4bFc+Hld9HmN01m82/6yGAWv62hTeCLDYV8AUgpiP+cK6AMRZBYEQgwqTVOsDVDdTnrIcKp3ZfKAJNAoGAOKa82kl3IUcFSypyTqXgItdRrOxuACS84012AEX/cWpHJDYXZGrpqZKR1/F2SAaRgwjjR1skmxYhMd//e0XwO8LkjC1lV81Uqgd21Z1LwWrIGVV0pFfnOL7jwYbXeQXEm/H61DKFdHncN+4285SR/QvvJVagFEwTnu+nq/qaDTECgYB7F5eRKAnNo914oA4JFWncrkyxVBDugWAPh1Rrrrk8I877cw93Q5Ht/Ps5AMo0ArtdLy5Ab2+jd0J0X1Gdey2vB7OdlWtgRwEe0zrpKJTpWJMb8dHPCo6QupHUhRwx0fOgLF4oC5+mnUBQu87mpNIO58whZaWYtqeM6ISxgKYjMw==";// 支付宝私钥

	public static final String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyU9n+qqBG1U9GbvmJsFC4wQIhRI5OsWfZxMxR3DPzyCx+2tPuHvkXZuMrb25WHkwHIddtxFjp2O3YWcwmuNTYHUQSP0twRGVReaw6hEC1i7m9LcoFDKGKjArHIL/bIf4JO8kN/cVUYe51q8bFXp7ktorLVhQ9TguTFOswvjiBT3rTt/yR2T1i+JV45pC6CGLNGKgO/jZn0kVkkuqTXWMi9G5/PpyxLBlF17HZpHMEMyaLNcWiY1u5S/peWaqmk1t/+vT9M+uVPwICRYCoH1NECJPUmoRQC9XJZdFgS+5nPYf1vyz3P5DI4Jkzkxa5Bih2l+Z/nsXfnK8sLZe3BA9QIDAQAB";// 支付宝公钥

	/**
	 * 支付宝统支付接口
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "支付宝支付接口",nickname = "支付宝支付接口")
	@RequestMapping(value = "/aliPay", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
    public String aliPay(HttpServletRequest request,HttpServletResponse response) throws Exception{
			    //实例化客户端
		        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId, alipay_private_key , "json", "UTF-8", alipay_public_key, "RSA2");
			    
		        AlipayTradePayRequest aliRequest = new AlipayTradePayRequest();
		        
		        String orderNum = DateUtils.dateTimeNow() + CodeUtil.genCodes(6);
		        
		        String product_code= request.getParameter("product_code");
		        
		        String deviceId= request.getParameter("deviceId");
		        
		
		        String payType= request.getParameter("payType");
		  
		        String orderAmount= request.getParameter("orderAmount");
		 
		        
		        Shop shop = shopService.selectShopBydeviceId(deviceId);
		        if(shop!=null) {
		        	Merchant merchant = merchantService.selectMerchantById(Long.valueOf(shop.getMerchatId()));
		        	
		        	aliRequest.setBizContent("{" +
			        		"\"out_trade_no\":\""+orderNum+"\"," +
			        		"\"scene\":\"bar_code\"," +
			        		"\"auth_code\":\""+product_code+"\"," +
			        		"\"subject\":\"Iphone6 16G\"," +
			        		"\"total_amount\":\""+orderAmount+"\"" +
			        		"  }");
			        		AlipayTradePayResponse aliresponse = alipayClient.execute(aliRequest,null,merchant!=null?merchant.getAppAuthToken():null);
			        		if(aliresponse.isSuccess()){
			        		System.out.println("调用成功");
			        		
			        		OrderFlowing orderFlowing = new OrderFlowing();
			        		
			        		orderFlowing.setMerchantName(shop.getMerchatName());
			        		orderFlowing.setMerchantId(shop.getMerchatId());
			        		orderFlowing.setAgentUserName(shop.getAgentUserName());
			        		orderFlowing.setAgentUserId(shop.getAgentUserId());
			        		orderFlowing.setOrderAmount(new BigDecimal(orderAmount));
			        		orderFlowing.setOrderNum(orderNum);
			        		orderFlowing.setPayType(payType);
			        		orderFlowing.setServiceCharge(new BigDecimal(Float.parseFloat(orderAmount)*0.002));
			        		orderFlowing.setBenefitProfit((merchant.getSiginRate().subtract(new BigDecimal(0.002))).multiply(new BigDecimal(orderAmount)));
			        		orderFlowing.setShopId(shop.getId());
			        		orderFlowing.setShopName(shop.getShopName());
			        		orderFlowing.setCreateTime(new Date());
			        		
			        		orderFlowingService.insertOrderFlowing(orderFlowing);
			        		
			        		} else {
			        		System.out.println("调用失败");
			        		}
							
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
	/**
	 * 支付宝授权回调
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "支付宝支付接口",nickname = "支付宝支付接口")
	@RequestMapping(value = "/notifyurl", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	 public String alipayBusinessLogin(HttpServletRequest request) throws AlipayApiException {
	        String appId = request.getParameter("app_id");
	        String source = request.getParameter("source");
	        String appAuthCode = request.getParameter("app_auth_code");
	        // 使用app_auth_code换取app_auth_token
	        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId, alipay_private_key , "json", "UTF-8", alipay_public_key, "RSA2");
	        
	        AlipayOpenAuthTokenAppRequest aoataRequest = new AlipayOpenAuthTokenAppRequest();
	        // 设置请求参数 使用app_auth_code换取app_auth_token
	        aoataRequest.setBizContent("{\"grant_type\":\"authorization_code\",\"code\":\"" + appAuthCode + "\"}");
	        // 发送请求得到响应
	        AlipayOpenAuthTokenAppResponse aoataResponse = alipayClient.execute(aoataRequest);
	        if (!aoataResponse.isSuccess()) {
	            throw new RuntimeException("获取app_auth_token失败！" + aoataResponse.getSubMsg());
	        }
	        // 根据appAuthToken换取用户信息
	        AlipayOpenAuthTokenAppQueryRequest aoataqRequest = new AlipayOpenAuthTokenAppQueryRequest();
	        aoataqRequest.setBizContent("{\"app_auth_token\":\"" + aoataResponse.getAppAuthToken() + "\"}");
	        AlipayOpenAuthTokenAppQueryResponse appQueryResponse = alipayClient.execute(aoataqRequest);
	        if (!appQueryResponse.isSuccess()) {
	            throw new RuntimeException("获取用户授权信息失败！" + appQueryResponse.getSubMsg());
	        }
	        // 用户授权成功 获取授权信息
	        String userId = appQueryResponse.getUserId();
	        String appID = appQueryResponse.getAuthAppId();
	        String body = appQueryResponse.getBody();
	        System.out.println(userId);
	        System.out.println(appID);
	        System.out.println(body);
	        System.out.println("token"+aoataResponse.getAppAuthToken());
	        
	        //处理自身业务绑定appid和授权app_auth_token
	        Merchant merchant = merchantService.selectMerchantByappId(appID);
	        
	        if(merchant!=null) {
	        	merchant.setAppAuthToken(aoataResponse.getAppAuthToken());
		        
		        merchantService.updateMerchant(merchant);
	        }
	        return "授权成功！";
	    }
}
