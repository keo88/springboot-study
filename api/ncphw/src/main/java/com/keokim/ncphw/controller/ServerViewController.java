package com.keokim.ncphw.controller;

import com.keokim.ncphw.domain.ServerInstance;
import com.keokim.ncphw.service.ServerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;

@Controller
public class ServerViewController {

    private final ServerService serverService;

    public ServerViewController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getServerList(Model model) {
        try {
            model.addAttribute("serverList", serverService.getServerList());
            return "table";

        } catch (Exception e) {
            model.addAttribute("errorText", e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @GetMapping(value = "/server/{instanceId}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerInstance startServer(@PathVariable long instanceId) throws GeneralSecurityException {
        return serverService.startServer(instanceId);
    }

    @ResponseBody
    @GetMapping(value = "/server/{instanceId}/stop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerInstance stopServer(@PathVariable long instanceId) throws GeneralSecurityException {
        return serverService.stopServer(instanceId);
    }

    @ResponseBody
    @GetMapping(value = "/server/{instanceId}/reboot", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerInstance rebootServer(@PathVariable long instanceId) throws GeneralSecurityException {
        return serverService.rebootServer(instanceId);
    }

    @ResponseBody
    @GetMapping(value = "/server/{instanceId}/terminate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerInstance terminateServer(@PathVariable long instanceId) throws GeneralSecurityException {
        return serverService.terminateServer(instanceId);
    }
}
