package com.keokim.ncphw.constant;

public class ApiPath {
    public static final String BASE_URL = "https://ncloud.apigw.ntruss.com";
    public static final String SERVER = "/vserver";
    public static final String VPC = "/vpc";
    public static final String API_VERSION = "/v2";
    public static final String SERVER_API = SERVER + API_VERSION;
    public static final String VPC_API = VPC + API_VERSION;
    public static final String SERVERS_LIST = SERVER_API + "/getServerInstanceList";
    public static final String SERVER_START = SERVER_API + "/startServerInstances";
    public static final String SERVER_STOP = SERVER_API + "/stopServerInstances";
    public static final String SERVER_TERMINATE = SERVER_API + "/terminateServerInstances";
    public static final String SERVER_REBOOT = SERVER_API + "/rebootServerInstances";
    public static final String SERVER_CREATE = SERVER_API + "/createServerInstances";
    public static final String PRODUCT_LIST = SERVER_API + "/getServerImageProductList";
    public static final String ACG_LIST = SERVER_API + "/getAccessControlGroupList";
    public static final String VPC_LIST = VPC_API + "/getVpcList";
    public static final String SUBNET_LIST = VPC_API + "/getSubnetList";
}