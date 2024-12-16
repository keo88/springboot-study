package com.keokim.ncphw.service;

import static com.keokim.ncphw.constant.ApiPath.*;
import static com.keokim.ncphw.util.SignatureBuilder.Method.*;
import static com.keokim.ncphw.util.Validator.isValidServerName;
import static com.keokim.ncphw.util.Validator.isValidCreateServerFormState;

import com.keokim.ncphw.constant.CustomHttpHeader;
import com.keokim.ncphw.domain.*;
import com.keokim.ncphw.util.SignatureBuilder;
import com.keokim.ncphw.util.TimestampRecorder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class ServerService {

    private final RestClient restClient;

    private final String accessKey;

    private final String secretKey;

    public ServerService(RestClient.Builder restClientBuilder, PropertiesService propertiesService) {
        this.restClient = restClientBuilder.build();
        this.accessKey = propertiesService.getAccessKey();
        this.secretKey = propertiesService.getSecretKey();
    }

    private <T> ResponseEntity<T> get(Class<T> responseType, String path, Map<String, String> params) throws GeneralSecurityException {
        String currentTimeStamp = Long.toString(TimestampRecorder.getUnixTimestamp());

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("responseFormatType", "json");

        params.forEach(queryParams::add);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BASE_URL + path)
                .queryParams(queryParams).build();

        return restClient.get().uri(uri.toUri())
                .header(CustomHttpHeader.timestampHeader, currentTimeStamp)
                .header(CustomHttpHeader.signatureHeader, SignatureBuilder.makeSignature(GET, uri.getPath() + "?" + uri.getQuery(), currentTimeStamp, accessKey, secretKey))
                .retrieve()
                .toEntity(responseType);
    }

    private <T> ResponseEntity<T> get(Class<T> responseType, String path) throws GeneralSecurityException {
        return get(responseType, path, new HashMap<>());
    }

    public List<ServerInstance> getServerList() throws GeneralSecurityException {

        Optional<GetServerInstanceResponse> res = Optional.ofNullable(get(GetServerInstanceResponse.class, SERVERS_LIST).getBody());

        if (res.isPresent()) {
            return res.get().getServerList().getServerInstanceList();
        }

        throw new IllegalStateException("Empty server instance list.");
    }

    private ServerInstance operateServer(String path, long instanceNumber) throws GeneralSecurityException {
        HashMap<String, String> map = new HashMap<>();
        map.put("serverInstanceNoList.1", Long.toString(instanceNumber));
        var res = get(GetServerInstanceResponse.class, path, map).getBody();

        if (res == null) {
            throw new IllegalStateException("Invalid server instance list.");
        }

        try {
            return res.getServerList().getServerInstanceList().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Invalid server instance list.");
        }
    }

    public ServerInstance startServer(long instanceNumber) throws GeneralSecurityException {
        return operateServer(SERVER_START, instanceNumber);
    }

    public ServerInstance stopServer(long instanceNumber) throws GeneralSecurityException {
        return operateServer(SERVER_STOP, instanceNumber);
    }

    public ServerInstance rebootServer(long instanceNumber) throws GeneralSecurityException {
        return operateServer(SERVER_REBOOT, instanceNumber);
    }

    public ServerInstance terminateServer(long instanceNumber) throws GeneralSecurityException {
        return operateServer(SERVER_TERMINATE, instanceNumber);
    }

    public List<Vpc> getVpcList() throws GeneralSecurityException {

        Optional<GetVpcListResponse> res = Optional.ofNullable(get(GetVpcListResponse.class, VPC_LIST).getBody());
        if (res.isEmpty()) {
            return new ArrayList<>();
        }

        return res.get().getGetVpcListResponse().getVpcList();
    }

    public List<List<Subnet>> getAllSubnetList(List<Vpc> vpcList) throws GeneralSecurityException {
        List<String> vpcNoList = vpcList.stream().map(Vpc::getVpcNo).toList();

        List<List<Subnet>> vpcSubnetsList = new ArrayList<>();

        for (String vpcNo : vpcNoList) {
            vpcSubnetsList.add(getSubnetList(vpcNo));
        }
        return vpcSubnetsList;
    }

    public List<Subnet> getSubnetList(String vpcNo) throws GeneralSecurityException {
        HashMap<String, String> map = new HashMap<>();
        map.put("vpcNo", vpcNo);

        var subnetRes = Optional.ofNullable(get(GetSubnetListResponse.class, SUBNET_LIST, map).getBody());

        if (subnetRes.isEmpty()) {
            return new ArrayList<>();
        }

        List<Subnet> subnetList = subnetRes.get().getGetSubnetListResponse().getSubnetList();

        return subnetList.stream().filter(subnet -> subnet.getUsageType().getCode().equals("GEN")).toList();
    }

    public List<List<AccessControlGroup>> getAllAcgList(List<Vpc> vpcList) throws GeneralSecurityException {
        List<String> vpcNoList = vpcList.stream().map(Vpc::getVpcNo).toList();

        List<List<AccessControlGroup>> vpcAcgList = new ArrayList<>();

        for (String vpcNo : vpcNoList) {
            vpcAcgList.add(getAcgList(vpcNo));
        }
        return vpcAcgList;
    }

    public List<AccessControlGroup> getAcgList(String vpcNo) throws GeneralSecurityException {
        HashMap<String, String> map = new HashMap<>();
        map.put("vpcNo", vpcNo);

        Optional<GetAccessControlGroupListResponse> res = Optional.ofNullable(get(GetAccessControlGroupListResponse.class, ACG_LIST, map).getBody());
        if (res.isEmpty()) {
            return new ArrayList<>();
        }

        return res.get().getGetAccessControlGroupListResponse().getAccessControlGroupList();
    }

    public List<Product> getServerImageProductList() throws GeneralSecurityException {
        Optional<GetServerImageProductListResponse> res = Optional.ofNullable(get(GetServerImageProductListResponse.class, PRODUCT_LIST).getBody());

        if (res.isEmpty()) {
            return new ArrayList<>();
        }

        return res.get().getGetServerImageProductListResponse().getProductList();
    }

    public ResponseEntity<GetServerInstanceResponse> createServer(CreateServerFormState formState) throws GeneralSecurityException {
        if (!isValidServerName(formState.getServerName()) || !isValidCreateServerFormState(formState)) {
            throw new IllegalArgumentException("잘못된 form 이 기입되었습니다.");
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("serverName", formState.getServerName());
        map.put("serverImageProductCode", formState.getProductCode());
        map.put("vpcNo", formState.getVpcNo());
        map.put("subnetNo", formState.getSubnetNo());
        map.put("networkInterfaceList.1.networkInterfaceOrder", "0");
        map.put("networkInterfaceList.1.accessControlGroupNoList.1", formState.getAcgNo());
        return get(GetServerInstanceResponse.class, SERVER_CREATE, map);
    }
}
