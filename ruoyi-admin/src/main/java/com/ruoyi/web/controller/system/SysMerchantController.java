package com.ruoyi.web.controller.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.request.AlipayOpenAgentCreateRequest;
import com.alipay.api.request.AlipayOpenAgentFacetofaceSignRequest;
import com.alipay.api.response.AlipayOpenAgentCreateResponse;
import com.alipay.api.response.AlipayOpenAgentFacetofaceSignResponse;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.FileDownLoadUtil;
import com.ruoyi.common.utils.OSSClientUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.config.Configs;
import com.ruoyi.system.domain.AgentUser;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.OrderFlowing;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.AgentUserService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.MerchantService;

/**
 * 蜻蜓设备商户
 * 
 * @author wxl
 */
@Controller
@RequestMapping("/system/merchant")
public class SysMerchantController extends BaseController {
	private String prefix = "system/merchant";

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private AgentUserService agentUserService;

	@Autowired
	private ISysUserService userService;

	@Autowired
	private SysPasswordService passwordService;

	@Autowired
	private OSSClientUtil ossClient;

	// 支付宝配置
	public static final String alipay_appId = "2021001108678896";// 支付宝APP应用ID

	public static final String alipay_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDBwcIwmqtuTmGpIcwiKIzBWXdfSbCBWukbDBMJUN19eYdvMV79/lJMYWLY9o+8hs0JfYJkL5ckHB5BIxOISY4zgM5NV17dj7CenAnGsyTy9P7cmMAh+FyVk7G+GTvncapzEZ1ZJLmVwcO0tlkyVCYtUuXZ0cUSNHWFYeHMzmgO0VgGUBhJiOPFDtwvBApllJ3cV2O0aiM5hkXHfqmckVv9UgQ/G5fW9UyhBZaqOh81xnZ2qePFPAuubJCYZyZ6K4l/V31eI0XA/wRVjNAviP8NDHctixnQPfkrafLkd9LbqMxrhFuW/3oNFrwsKuMTbG9x/OPu5vRWWeRNxFVsnJxXAgMBAAECggEALaaKK0t10qJzDhdhcjbdmvyKTJAfHwsdWvsITSyZUuPcTg1y1SMjns1fLcFB8mhMTM0eeJ5h3OcnFa++/WBfObFCaiSIvGkLcwNOpls0/G1O0wjGU/qhTbijqydk3s4AG+ZtjUBC3Y7unlhMQbhj2k/qydxLs1Na0RdTzR5glATDKKCaDNaB3ToOj8TihO8bbt7I8G3ZdfL+VCvU55A62TDu/AJjmGgesrLBT9evt8neKDf3PadqA/eKCu0aimCNjIB7G7kbsn7rJsbkn4u6I5QKGN8WpomjlsqYQN+jzWrxSWzQjX8i3IUjLGkdMnQBV5VtvjfJX8tPYl2wHbIs2QKBgQD62Aqm8633DH3F1R1COug6jytlUXB1vdYC4FmhakbugM9M11kGOrzHeT74/6RsWFONp/EEBIdbCV6hZKnUInWnsqoKiE6oXWW7L15u0pmATj/WbiYoFxoz0yLxZpr/CZeZSTCHcozgDA3RW70eoY780O/onz83MaGszqZS89PVDQKBgQDFvVIl04Q8nudFCou9k1gkiGfly8rxT+lsBEWPKVXxhDT9dBr50Gq8piseeiPn9eB/wpFKsfS9uC18CPZyKrJAnYJVGvZc9/1DrtwTCRJqi/bkzo+tRhM0xEm9m8X4U7zVOSOIoqdqxobXdQXOM1dkNkSERzDn14gqzV66HRil8wKBgCmWAYYWJgQsFPnkT/p366IxtB8S9lL+yuklKpbhGKtXvYZeuUqYGE31ouQC9c5kgk2cxw9EdPA5yG93UdRydhD8RMaEPI4bFc+Hld9HmN01m82/6yGAWv62hTeCLDYV8AUgpiP+cK6AMRZBYEQgwqTVOsDVDdTnrIcKp3ZfKAJNAoGAOKa82kl3IUcFSypyTqXgItdRrOxuACS84012AEX/cWpHJDYXZGrpqZKR1/F2SAaRgwjjR1skmxYhMd//e0XwO8LkjC1lV81Uqgd21Z1LwWrIGVV0pFfnOL7jwYbXeQXEm/H61DKFdHncN+4285SR/QvvJVagFEwTnu+nq/qaDTECgYB7F5eRKAnNo914oA4JFWncrkyxVBDugWAPh1Rrrrk8I877cw93Q5Ht/Ps5AMo0ArtdLy5Ab2+jd0J0X1Gdey2vB7OdlWtgRwEe0zrpKJTpWJMb8dHPCo6QupHUhRwx0fOgLF4oC5+mnUBQu87mpNIO58whZaWYtqeM6ISxgKYjMw==";// 支付宝私钥

	public static final String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyU9n+qqBG1U9GbvmJsFC4wQIhRI5OsWfZxMxR3DPzyCx+2tPuHvkXZuMrb25WHkwHIddtxFjp2O3YWcwmuNTYHUQSP0twRGVReaw6hEC1i7m9LcoFDKGKjArHIL/bIf4JO8kN/cVUYe51q8bFXp7ktorLVhQ9TguTFOswvjiBT3rTt/yR2T1i+JV45pC6CGLNGKgO/jZn0kVkkuqTXWMi9G5/PpyxLBlF17HZpHMEMyaLNcWiY1u5S/peWaqmk1t/+vT9M+uVPwICRYCoH1NECJPUmoRQC9XJZdFgS+5nPYf1vyz3P5DI4Jkzkxa5Bih2l+Z/nsXfnK8sLZe3BA9QIDAQAB";// 支付宝公钥

	@RequiresPermissions("system:merchant:view")
	@GetMapping()
	public String config() {
		return prefix + "/merchant";
	}

	/**
	 * 商户列表
	 */
	@RequiresPermissions("system:merchant:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Merchant merchant) {
		startPage();
		List<Merchant> list = new ArrayList<Merchant>();
		String roleType = ShiroUtils.getSysUser().getRoles().get(0).getRoleName();

		if ("管理员".equals(roleType)) {
			// 根据代理商查询所有交易流水
			list = merchantService.selectMerchantList(merchant);
		}else {
			merchant.setCreateBy(ShiroUtils.getLoginName());
			list = merchantService.selectMerchantList(merchant);
		}
		return getDataTable(list);
	}

	/**
	 * 新增商户
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存商户
	 * 
	 * @throws Exception
	 */
	@RequiresPermissions("system:merchant:add")
	@Log(title = "商户管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Merchant merchant, @RequestParam("files") MultipartFile[] files) throws Exception {
		// 根据当前登录者查询对应的代理商信息
		AgentUser agentUser = new AgentUser();
		agentUser.setUserId(ShiroUtils.getUserId().intValue());
		List<AgentUser> list = agentUserService.selectAgentUserList(agentUser);
		if (list.size() != 0) {
			merchant.setAgentUserId(list.get(0).getId());
			merchant.setAgentUserName(list.get(0).getAgentName());
		}
		StringBuilder sb = new StringBuilder();
		if (files.length != 0) {
			
			String businessLicenseUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[0]));
			
			String organizationUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[1]));
			
			String idcardPositiveUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[2]));
			
			String idcardOthersideUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[3]));
			
			String shopImageUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[4]));
			
			String qualificationsImageUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[5]));
			
			merchant.setBusinessLicenseUrl(businessLicenseUrl);
			merchant.setOrganizationUrl(organizationUrl);
			merchant.setIdcardOthersideUrl(idcardOthersideUrl);
			merchant.setIdcardPositiveUrl(idcardPositiveUrl);
			merchant.setQualificationsImageUrl(qualificationsImageUrl);
			merchant.setShopImageUrl(shopImageUrl);
			
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(merchant.getPhone()))) {
			return error("新增用户'" + merchant.getPhone() + "'失败，登录账号已存在");
		} else {
			SysUser user = new SysUser();

			user.setSalt(ShiroUtils.randomSalt());
			user.setLoginName(merchant.getPhone());
			user.setUserName(merchant.getPhone());
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setCreateTime(new Date());
			user.setPassword(passwordService.encryptPassword(merchant.getPhone(), "123456", user.getSalt()));
			user.setPhonenumber(merchant.getPhone());
			Long[] roleIds = new Long[1];
			roleIds[0] = Long.valueOf(4);
			user.setRoleIds(roleIds);
			userService.insertUser(user);

			merchant.setCreateTime(new Date());
			merchant.setCreateBy(ShiroUtils.getLoginName());
			merchant.setUserId(user.getUserId().intValue());
			merchant.setIsSigning("n");
			return toAjax(merchantService.insertMerchant(merchant));
		}
	}

	/**
	 * 代商户签约
	 */
	@GetMapping("/sign/{merchantId}")
	public String sign(@PathVariable("merchantId") Long merchantId, ModelMap mmap) {
		mmap.put("merchant", merchantService.selectMerchantById(merchantId));
		return prefix + "/sign";
	}

	/**
	 * 签约保存
	 * 
	 * @throws Exception
	 */
	@RequiresPermissions("system:merchant:sign")
	@Log(title = "商户签约", businessType = BusinessType.INSERT)
	@PostMapping("/sign")
	@ResponseBody
	public AjaxResult sign(@RequestParam("siginRate") String siginRate, @RequestParam("merchantId") Integer merchantId)
			throws Exception {
		// 根据商户ID查询商户信息merchantId
		Merchant merchant = merchantService.selectMerchantById(merchantId.longValue());
		merchant.setSiginRate(new BigDecimal(siginRate));

		Configs.init("zfbinfo.properties");

		String appid = Configs.getAppid();

		String batch_no = signCreate(merchant);

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId,
				alipay_private_key, "json", "UTF-8", alipay_public_key, "RSA2");
		AlipayOpenAgentFacetofaceSignRequest request = new AlipayOpenAgentFacetofaceSignRequest();
		request.setBatchNo(batch_no);
		request.setMccCode("A_A03_4582");

		/*
		 * FileItem SpecialLicensePic = new FileItem(
		 * "C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
		 * request.setSpecialLicensePic(SpecialLicensePic);
		 */
		request.setRate(siginRate);
		request.setSignAndAuth(false);

		/*
		 * FileItem BusinessLicensePic = new FileItem(
		 * "C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
		 * request.setBusinessLicensePic(BusinessLicensePic);
		 * 
		 * FileItem BusinessLicenseAuthPic = new FileItem(
		 * "C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
		 * request.setBusinessLicenseAuthPic(BusinessLicenseAuthPic);
		 */

		String ShopSignBoardPicUrl  = FileDownLoadUtil.download(merchant.getShopImageUrl(), "d:\\image\\");
		FileItem ShopSignBoardPic = new FileItem(ShopSignBoardPicUrl);
		request.setShopSignBoardPic(ShopSignBoardPic);
		AlipayOpenAgentFacetofaceSignResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			merchant.setIsSigning("y");
			return toAjax(merchantService.updateMerchant(merchant));
		} else {
			return null;
		}

	}

	public String signCreate(Merchant merchant) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId,
				alipay_private_key, "json", "UTF-8", alipay_public_key, "RSA2");
		AlipayOpenAgentCreateRequest request = new AlipayOpenAgentCreateRequest();
		request.setBizContent("{" + "\"account\":\"" + merchant.getAliAccount() + "\"," + "\"contact_info\":{"
				+ "\"contact_name\":\"" + merchant.getCardholder() + "\"," + "\"contact_mobile\":\""
				+ merchant.getMobile() + "\"," + "\"contact_email\":\"" + merchant.getEmail() + "\"" + "    }" + "  }");
		AlipayOpenAgentCreateResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBatchNo();
		} else {
			return "";
		}

	}

	/**
	 * 修改商户
	 */
	@GetMapping("/edit/{merchantId}")
	public String edit(@PathVariable("merchantId") Long merchantId, ModelMap mmap) {
		mmap.put("merchant", merchantService.selectMerchantById(merchantId));
		return prefix + "/edit";
	}

	/**
	 * 修改保存商户
	 * @throws Exception 
	 */
	@RequiresPermissions("system:merchant:edit")
	@Log(title = "商户管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@Validated Merchant merchant, @RequestParam("files") MultipartFile[] files) throws Exception {
		
        if (files.length != 0) {
        	if(files[0].isEmpty()==false) {
			String businessLicenseUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[0]));
			merchant.setBusinessLicenseUrl(businessLicenseUrl);
        	}
			if(files[1].isEmpty()==false) {
				String organizationUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[1]));
				merchant.setOrganizationUrl(organizationUrl);
			}
			
			if(files[2].isEmpty()==false) {
			String idcardPositiveUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[2]));
			merchant.setIdcardPositiveUrl(idcardPositiveUrl);
			}
			
			if(files[3].isEmpty()==false) {
			String idcardOthersideUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[3]));
			merchant.setIdcardOthersideUrl(idcardOthersideUrl);
			}
			
			if(files[4].isEmpty()==false) {
			String shopImageUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[4]));
			merchant.setShopImageUrl(shopImageUrl);
			}
			
			if(files[5].isEmpty()==false) {
			String qualificationsImageUrl = ossClient.getImgUrl(ossClient.uploadImg2Oss(files[5]));
			merchant.setQualificationsImageUrl(qualificationsImageUrl);
			}
		}
		merchant.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(merchantService.updateMerchant(merchant));
	}

	/**
	 * 删除商户
	 * 
	 * @throws Exception
	 */
	@RequiresPermissions("system:merchant:remove")
	@Log(title = "商户管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) throws Exception {
		return toAjax(merchantService.deleteMerchantByIds(ids));
	}

}
