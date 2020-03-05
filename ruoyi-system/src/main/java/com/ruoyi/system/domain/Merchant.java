package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

public class Merchant extends BaseEntity{
    private Integer id;

    private String merchantName;

    private String businessCategory;

    private String address;

    private String mobile;

    private String contacts;

    private String phone;

    private String email;

    private String receivingBank;

    private String cardholder;

    private String cardNumber;

    private String businessLicenseUrl;

    private String organizationUrl;

    private String idcardPositiveUrl;

    private String idcardOthersideUrl;

    private String createBy;
    
    private String appid;
    
    private String appAuthToken;
    
    private String agentUserName;
    
    private Integer agentUserId;

    private Date createTime;
    
    private Integer userId;
    
    private String isSigning;

    private BigDecimal siginRate;
    
    private String aliAccount;
    
    private String shopImageUrl;
    
    private String qualificationsImageUrl;

    public String getShopImageUrl() {
		return shopImageUrl;
	}

	public void setShopImageUrl(String shopImageUrl) {
		this.shopImageUrl = shopImageUrl;
	}

	public String getQualificationsImageUrl() {
		return qualificationsImageUrl;
	}

	public void setQualificationsImageUrl(String qualificationsImageUrl) {
		this.qualificationsImageUrl = qualificationsImageUrl;
	}

	public String getAliAccount() {
		return aliAccount;
	}

	public void setAliAccount(String aliAccount) {
		this.aliAccount = aliAccount;
	}

	public String getIsSigning() {
		return isSigning;
	}

	public void setIsSigning(String isSigning) {
		this.isSigning = isSigning;
	}

	public BigDecimal getSiginRate() {
		return siginRate;
	}

	public void setSiginRate(BigDecimal siginRate) {
		this.siginRate = siginRate;
	}

	public String getAgentUserName() {
		return agentUserName;
	}

	public void setAgentUserName(String agentUserName) {
		this.agentUserName = agentUserName;
	}


	public Integer getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Integer agentUserId) {
		this.agentUserId = agentUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getAppAuthToken() {
		return appAuthToken;
	}

	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}

	public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getReceivingBank() {
        return receivingBank;
    }

    public void setReceivingBank(String receivingBank) {
        this.receivingBank = receivingBank == null ? null : receivingBank.trim();
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl == null ? null : businessLicenseUrl.trim();
    }

    public String getOrganizationUrl() {
        return organizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl == null ? null : organizationUrl.trim();
    }

    public String getIdcardPositiveUrl() {
        return idcardPositiveUrl;
    }

    public void setIdcardPositiveUrl(String idcardPositiveUrl) {
        this.idcardPositiveUrl = idcardPositiveUrl == null ? null : idcardPositiveUrl.trim();
    }

    public String getIdcardOthersideUrl() {
        return idcardOthersideUrl;
    }

    public void setIdcardOthersideUrl(String idcardOthersideUrl) {
        this.idcardOthersideUrl = idcardOthersideUrl == null ? null : idcardOthersideUrl.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}