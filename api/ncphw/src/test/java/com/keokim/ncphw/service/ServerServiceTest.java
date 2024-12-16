package com.keokim.ncphw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keokim.ncphw.domain.GetServerInstanceResponse;
import com.keokim.ncphw.domain.ServerInstance;
import com.keokim.ncphw.domain.ServerInstanceList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static com.keokim.ncphw.constant.ApiPath.*;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(value={ServerService.class, PropertiesService.class})
@ExtendWith(MockitoExtension.class)
class ServerServiceTest {

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ServerService serverService;

    @Test
    void getServerList() throws Exception {
        //given
        ServerInstance instance = new ServerInstance();
        instance.setServerInstanceNo("10");
        instance.setServerName("test-server");
        ServerInstanceList serverInstanceList = new ServerInstanceList();
        serverInstanceList.setServerInstanceList(List.of(instance));
        GetServerInstanceResponse response = new GetServerInstanceResponse();
        response.setServerList(serverInstanceList);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);

        mockServer
                .expect(requestTo(matchesPattern(BASE_URL + SERVERS_LIST + ".*")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        //when
        List<ServerInstance> serverList = serverService.getServerList();

        //then
        assertEquals(1, serverList.size());
        assertEquals(serverList.get(0).getServerInstanceNo(), instance.getServerInstanceNo());
    }

    @Test
    void getServerInstance() throws Exception {

    }
}