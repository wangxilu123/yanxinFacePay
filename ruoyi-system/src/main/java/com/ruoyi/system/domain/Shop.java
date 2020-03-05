package com.ruoyi.system.domain;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

public class Shop extends BaseEntity{
    private Integer id;

    private String shopName;
    
    private String merchatName;
    
    private Integer merchatId;

    private String logoUrl;

    private String address;

    private String deviceId;

    private String mobile;

    private String createBy;

    private Date createTime;
    
    private String agentUserName;
    
    private Integer agentUserId;


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

	public String getMerchatName() {
		return merchatName;
	}

	public void setMerchatName(String merchatName) {
		this.merchatName = merchatName;
	}


	public Integer getMerchatId() {
		return merchatId;
	}

	public void setMerchatId(Integer merchatId) {
		this.merchatId = merchatId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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