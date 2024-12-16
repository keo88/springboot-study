package com.keokim.ncphw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PropertiesService {

    @Value("${external.api.accessKey}")
    private String accessKey;

    @Value("${external.api.secretKey}")
    private String secretKey;

    public PropertiesService() {
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
