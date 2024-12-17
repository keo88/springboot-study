package com.keokim.ncphw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getPage() {
        return "redirect:/server";
    }

    @GetMapping("/health-check")
    @ResponseBody
    public String healthCheck() {
        return "OK";
    }
}
