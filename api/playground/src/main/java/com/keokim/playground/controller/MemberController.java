package com.keokim.playground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	@GetMapping("/member")
	public String getMemberInfo() {
		return "Private Member Info";
	}

	@GetMapping("/member/logout")
	public String logout() {
		return "Logout";
	}

}
