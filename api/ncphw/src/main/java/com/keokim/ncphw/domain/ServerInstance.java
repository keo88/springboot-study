package com.keokim.ncphw.domain;

import java.util.List;

public class ServerInstance {

    private String serverInstanceNo;
    private String serverName;
    private String serverDescription;
    private Integer cpuCount;
    private Long memorySize;
    private CommonCode platformType;
    private String loginKeyName;
    private String publicIpInstanceNo;
    private String publicIp;
    private CommonCode serverInstanceStatus;
    private CommonCode serverInstanceOperation;
    private String serverInstanceStatusName;
    private String createDate;
    private String uptime;
    private String serverImageProductCode;
    private String serverProductCode;
    private Boolean isProtectServerTermination;
    private String zoneCode;
    private String regionCode;
    private String vpcNo;
    private String subnetNo;
    private List<String> networkInterfaceNoList;
    private String initScriptNo;
    private CommonCode serverInstanceType;
    private CommonCode baseBlockStorageDiskType;
    private CommonCode baseBlockStorageDiskDetailType;
    private String placementGroupNo;
    private String placementGroupName;
    private String memberServerImageInstanceNo;
    private List<BlockDevicePartition> blockDevicePartitionList;
    private CommonCode hypervisorType;
    private String serverImageNo;
    private String serverSpecCode;

    public String getServerInstanceNo() {
        return serverInstanceNo;
    }

    public void setServerInstanceNo(String serverInstanceNo) {
        this.serverInstanceNo = serverInstanceNo;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Long memorySize) {
        this.memorySize = memorySize;
    }

    public CommonCode getPlatformType() {
        return platformType;
    }

    public void setPlatformType(CommonCode platformType) {
        this.platformType = platformType;
    }

    public String getLoginKeyName() {
        return loginKeyName;
    }

    public void setLoginKeyName(String loginKeyName) {
        this.loginKeyName = loginKeyName;
    }

    public String getPublicIpInstanceNo() {
        return publicIpInstanceNo;
    }

    public void setPublicIpInstanceNo(String publicIpInstanceNo) {
        this.publicIpInstanceNo = publicIpInstanceNo;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public CommonCode getServerInstanceStatus() {
        return serverInstanceStatus;
    }

    public void setServerInstanceStatus(CommonCode serverInstanceStatus) {
        this.serverInstanceStatus = serverInstanceStatus;
    }

    public CommonCode getServerInstanceOperation() {
        return serverInstanceOperation;
    }

    public void setServerInstanceOperation(CommonCode serverInstanceOperation) {
        this.serverInstanceOperation = serverInstanceOperation;
    }

    public String getServerInstanceStatusName() {
        return serverInstanceStatusName;
    }

    public void setServerInstanceStatusName(String serverInstanceStatusName) {
        this.serverInstanceStatusName = serverInstanceStatusName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getServerImageProductCode() {
        return serverImageProductCode;
    }

    public void setServerImageProductCode(String serverImageProductCode) {
        this.serverImageProductCode = serverImageProductCode;
    }

    public String getServerProductCode() {
        return serverProductCode;
    }

    public void setServerProductCode(String serverProductCode) {
        this.serverProductCode = serverProductCode;
    }

    public Boolean getProtectServerTermination() {
        return isProtectServerTermination;
    }

    public void setProtectServerTermination(Boolean protectServerTermination) {
        isProtectServerTermination = protectServerTermination;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public List<String> getNetworkInterfaceNoList() {
        return networkInterfaceNoList;
    }

    public void setNetworkInterfaceNoList(List<String> networkInterfaceNoList) {
        this.networkInterfaceNoList = networkInterfaceNoList;
    }

    public String getInitScriptNo() {
        return initScriptNo;
    }

    public void setInitScriptNo(String initScriptNo) {
        this.initScriptNo = initScriptNo;
    }

    public CommonCode getServerInstanceType() {
        return serverInstanceType;
    }

    public void setServerInstanceType(CommonCode serverInstanceType) {
        this.serverInstanceType = serverInstanceType;
    }

    public CommonCode getBaseBlockStorageDiskType() {
        return baseBlockStorageDiskType;
    }

    public void setBaseBlockStorageDiskType(CommonCode baseBlockStorageDiskType) {
        this.baseBlockStorageDiskType = baseBlockStorageDiskType;
    }

    public CommonCode getBaseBlockStorageDiskDetailType() {
        return baseBlockStorageDiskDetailType;
    }

    public void setBaseBlockStorageDiskDetailType(CommonCode baseBlockStorageDiskDetailType) {
        this.baseBlockStorageDiskDetailType = baseBlockStorageDiskDetailType;
    }

    public String getPlacementGroupNo() {
        return placementGroupNo;
    }

    public void setPlacementGroupNo(String placementGroupNo) {
        this.placementGroupNo = placementGroupNo;
    }

    public String getPlacementGroupName() {
        return placementGroupName;
    }

    public void setPlacementGroupName(String placementGroupName) {
        this.placementGroupName = placementGroupName;
    }

    public String getMemberServerImageInstanceNo() {
        return memberServerImageInstanceNo;
    }

    public void setMemberServerImageInstanceNo(String memberServerImageInstanceNo) {
        this.memberServerImageInstanceNo = memberServerImageInstanceNo;
    }

    public List<BlockDevicePartition> getBlockDevicePartitionList() {
        return blockDevicePartitionList;
    }

    public void setBlockDevicePartitionList(List<BlockDevicePartition> blockDevicePartitionList) {
        this.blockDevicePartitionList = blockDevicePartitionList;
    }

    public CommonCode getHypervisorType() {
        return hypervisorType;
    }

    public void setHypervisorType(CommonCode hypervisorType) {
        this.hypervisorType = hypervisorType;
    }

    public String getServerImageNo() {
        return serverImageNo;
    }

    public void setServerImageNo(String serverImageNo) {
        this.serverImageNo = serverImageNo;
    }

    public String getServerSpecCode() {
        return serverSpecCode;
    }

    public void setServerSpecCode(String serverSpecCode) {
        this.serverSpecCode = serverSpecCode;
    }
}
