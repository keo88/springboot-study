package com.keokim.ncphw.domain;

import java.util.Date;

public class Vpc {
    private String vpcNo;
    private String vpcName;
    private String ipv4CidrBlock;
    private CommonCode vpcStatus;
    private String regionCode;
    private Date createDate;

    public String getVpcNo() {
        return vpcNo;
    }

    public void setVpcNo(String vpcNo) {
        this.vpcNo = vpcNo;
    }

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public String getIpv4CidrBlock() {
        return ipv4CidrBlock;
    }

    public void setIpv4CidrBlock(String ipv4CidrBlock) {
        this.ipv4CidrBlock = ipv4CidrBlock;
    }

    public CommonCode getVpcStatus() {
        return vpcStatus;
    }

    public void setVpcStatus(CommonCode vpcStatus) {
        this.vpcStatus = vpcStatus;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
