package com.keokim.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.keokim.playground.base.alias.Member;
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

	@GetMapping("/member/new")
	public String createMember(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/createMemberForm";
	}

	@PostMapping("/member/new")
	public String create(@Valid MemberForm form) {
		memberService.join(Member.of(form));
		return "redirect:/";
	}

}
