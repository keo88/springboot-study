package com.keokim.ncphw.domain;

import java.util.Date;

public class Subnet {
    private String subnetNo;
    private String vpcNo;
    private String zoneCode;
    private String subnetName;
    private String subnet;
    private CommonCode subnetStatus;
    private Date createDate;
    private CommonCode subnetType;
    private CommonCode usageType;
    private String networkAclNo;

    public String getSubnetNo() {
        return subnetNo;
    }

    public void setSubnetNo(String subnetNo) {
        this.subnetNo = subnetNo;
    }

    public String getVpcNo() {
        return vpcNo;
    }

    public void setVpcNo(String vpcNo) {
        this.vpcNo = vpcNo;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public void setSubnetName(String subnetName) {
        this.subnetName = subnetName;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public CommonCode getSubnetStatus() {
        return subnetStatus;
    }

    public void setSubnetStatus(CommonCode subnetStatus) {
        this.subnetStatus = subnetStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CommonCode getSubnetType() {
        return subnetType;
    }

    public void setSubnetType(CommonCode subnetType) {
        this.subnetType = subnetType;
    }

    public CommonCode getUsageType() {
        return usageType;
    }

    public void setUsageType(CommonCode usageType) {
        this.usageType = usageType;
    }

    public String getNetworkAclNo() {
        return networkAclNo;
    }

    public void setNetworkAclNo(String networkAclNo) {
        this.networkAclNo = networkAclNo;
    }
}
