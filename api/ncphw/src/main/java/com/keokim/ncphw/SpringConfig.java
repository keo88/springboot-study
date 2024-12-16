package com.keokim.ncphw;

import com.keokim.ncphw.constant.CustomHttpHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class SpringConfig {

    @Value("${external.api.accessKey}")
    public String accessKey;

    @Bean
    public RestClient.Builder serverApiRestClient() {
        return RestClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(CustomHttpHeader.accessKeyHeader, accessKey);
                });
//                .requestInterceptor(new LoggingInterceptor())
    }
}
