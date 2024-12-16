package com.keokim.ncphw.domain;

public class Product {
    private String productCode;
    private String productName;
    private CommonCode productType;
    private String productDescription;
    private CommonCode infraResourceType;
    private CommonCode infraResourceDetailType;
    private Integer cpuCount;
    private Long memorySize;
    private Long baseBlockStorageSize;
    private CommonCode platformType;
    private String osInformation;
    private CommonCode diskType;
    private String dbKindCode;
    private Long addBlockStorageSize;
    private String generationCode;



    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CommonCode getProductType() {
        return productType;
    }

    public void setProductType(CommonCode productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public CommonCode getInfraResourceType() {
        return infraResourceType;
    }

    public void setInfraResourceType(CommonCode infraResourceType) {
        this.infraResourceType = infraResourceType;
    }

    public CommonCode getInfraResourceDetailType() {
        return infraResourceDetailType;
    }

    public void setInfraResourceDetailType(CommonCode infraResourceDetailType) {
        this.infraResourceDetailType = infraResourceDetailType;
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

    public Long getBaseBlockStorageSize() {
        return baseBlockStorageSize;
    }

    public void setBaseBlockStorageSize(Long baseBlockStorageSize) {
        this.baseBlockStorageSize = baseBlockStorageSize;
    }

    public CommonCode getPlatformType() {
        return platformType;
    }

    public void setPlatformType(CommonCode platformType) {
        this.platformType = platformType;
    }

    public String getOsInformation() {
        return osInformation;
    }

    public void setOsInformation(String osInformation) {
        this.osInformation = osInformation;
    }

    public CommonCode getDiskType() {
        return diskType;
    }

    public void setDiskType(CommonCode diskType) {
        this.diskType = diskType;
    }

    public String getDbKindCode() {
        return dbKindCode;
    }

    public void setDbKindCode(String dbKindCode) {
        this.dbKindCode = dbKindCode;
    }

    public Long getAddBlockStorageSize() {
        return addBlockStorageSize;
    }

    public void setAddBlockStorageSize(Long addBlockStorageSize) {
        this.addBlockStorageSize = addBlockStorageSize;
    }

    public String getGenerationCode() {
        return generationCode;
    }

    public void setGenerationCode(String generationCode) {
        this.generationCode = generationCode;
    }
}
