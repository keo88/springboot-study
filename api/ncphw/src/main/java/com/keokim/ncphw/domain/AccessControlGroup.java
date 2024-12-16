package com.keokim.ncphw.domain;

public class AccessControlGroup {
    private String accessControlGroupNo;
    private String accessControlGroupName;
    private Boolean isDefault;
    private String vpcNo;
    private CommonCode accessControlGroupStatus;
    private String accessControlGroupDescription;

    public String getAccessControlGroupNo() {
        return accessControlGroupNo;
    }

    public void setAccessControlGroupNo(String accessControlGroupNo) {
        this.accessControlGroupNo = accessControlGroupNo;
    }

    public String getAccessControlGroupName() {
        return accessControlGroupName;
    }

    public void setAccessControlGroupName(String accessControlGroupName) {
        this.accessControlGroupName = accessControlGroupName;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getVpcNo() {
        return vpcNo;
    }

    public void setVpcNo(String vpcNo) {
        this.vpcNo = vpcNo;
    }

    public CommonCode getAccessControlGroupStatus() {
        return accessControlGroupStatus;
    }

    public void setAccessControlGroupStatus(CommonCode accessControlGroupStatus) {
        this.accessControlGroupStatus = accessControlGroupStatus;
    }

    public String getAccessControlGroupDescription() {
        return accessControlGroupDescription;
    }

    public void setAccessControlGroupDescription(String accessControlGroupDescription) {
        this.accessControlGroupDescription = accessControlGroupDescription;
    }
}
