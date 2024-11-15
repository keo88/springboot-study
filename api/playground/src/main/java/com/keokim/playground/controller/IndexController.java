package com.keokim.playground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IndexController {

	@GetMapping("/")
	public String getIndex() {
		return "Hello World";
	}

	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}
}
