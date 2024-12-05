package com.keokim.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keokim.playground.base.dto.MemberForm;
import com.keokim.playground.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/member/login")
	public String login(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/login";
	}

	@GetMapping("/member/new")
	public String createMember(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/createMemberForm";
	}

	@PostMapping("/member/new")
	public String create(@Valid MemberForm form) {
		memberService.join(form);
		return "redirect:/";
	}

	@ResponseBody
	@GetMapping("/member/list")
	public String getList() {
		return "member/memberList";
	}

}
