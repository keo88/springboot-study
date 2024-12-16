package com.keokim.ncphw.domain;

public class CreateServerFormState {
    private String serverName;
    private String vpcNo;
    private String subnetNo;
    private String acgNo;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    private String productCode;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getVpcNo() {
        return vpcNo;
    }

    public void setVpcNo(String vpcNo) {
        this.vpcNo = vpcNo;
    }

    public String getSubnetNo() {
        return subnetNo;
    }

    public void setSubnetNo(String subnetNo) {
        this.subnetNo = subnetNo;
    }

    public String getAcgNo() {
        return acgNo;
    }

    public void setAcgNo(String acgNo) {
        this.acgNo = acgNo;
    }
}
