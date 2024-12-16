package com.keokim.ncphw.controller;

import com.keokim.ncphw.domain.*;
import com.keokim.ncphw.service.ServerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Controller
public class ServerCreateFormController {

    private final ServerService serverService;

    public ServerCreateFormController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping(value="/server/create")
    public String getCreateServerForm(Model model) {
        try {

            List<Vpc> vpcList = serverService.getVpcList();
            List<List<Subnet>> vpcSubnetsList = serverService.getAllSubnetList(vpcList);
            List<List<AccessControlGroup>> vpcAcgList = serverService.getAllAcgList(vpcList);
            List<Product> productList = serverService.getServerImageProductList();

            model.addAttribute("vpcSubnetsList", vpcSubnetsList);
            model.addAttribute("vpcList", vpcList);
            model.addAttribute("vpcAcgList", vpcAcgList);
            model.addAttribute("productList", productList);

        } catch (GeneralSecurityException generalSecurityException) {
            generalSecurityException.printStackTrace();
            model.addAttribute("errorText", "Failed to get information required to create server.");
            return "error";
        }

        return "createServerForm";
    }

    @PostMapping(value="/server/create")
    public String createServer(CreateServerFormState formState, Model model) {

        try {
            Optional<GetServerInstanceResponse> res = Optional.ofNullable(serverService.createServer(formState).getBody());

            if (res.isEmpty()) {
                model.addAttribute("errorText", "Failed to create server.\nEmpty Response.");
                return "error";
            }
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorText", "Failed to create server.\n" + e.getMessage());
            return "error";
        }

        return "redirect:/server";
    }
}
