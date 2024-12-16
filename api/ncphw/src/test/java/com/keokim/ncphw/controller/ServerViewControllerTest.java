package com.keokim.ncphw.controller;

import com.keokim.ncphw.domain.CommonCode;
import com.keokim.ncphw.domain.ServerInstance;
import com.keokim.ncphw.service.ServerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerViewControllerTest {

    @Mock
    private ServerService serverService;

    @Mock
    private Model model;

    @InjectMocks
    private ServerViewController serverViewController;

    @Test
    void getServerListSuccess() throws Exception {

        // given
        ServerInstance mockServerInstance = new ServerInstance();
        mockServerInstance.setServerInstanceNo("1");
        mockServerInstance.setServerName("s1");
        mockServerInstance.setServerInstanceStatus(new CommonCode("RUN", "RUNNING"));

        when(serverService.getServerList()).thenReturn(List.of(mockServerInstance));
        // when
        var result = serverViewController.getServerList(model);

        // then
        assertEquals(result, "table");
        verify(serverService).getServerList();
    }

    @Test
    void getServerListFail() throws Exception {
        //given
        String errorMessage = "fake error message";
        when(serverService.getServerList()).thenThrow(new IllegalStateException(errorMessage));

        // when
        var result = serverViewController.getServerList(model);

        // then
        assertEquals(result, "error");
        verify(serverService).getServerList();
    }

    @Test
    void startServer() {
    }

    @Test
    void stopServer() {
    }

    @Test
    void rebootServer() {
    }

    @Test
    void terminateServer() {
    }
}