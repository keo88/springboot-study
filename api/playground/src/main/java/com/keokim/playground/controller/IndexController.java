package com.keokim.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("username", "손님");

		return "home";
	}

	@ResponseBody
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}
}
