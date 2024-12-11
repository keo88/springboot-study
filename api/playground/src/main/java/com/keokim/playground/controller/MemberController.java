package com.keokim.playground.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/member/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("memberForm", new MemberForm());

		if (error != null) {
			model.addAttribute("error", error);
		}

		return "member/login";
	}

	@GetMapping("/member/new")
	public String createMember(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/createMemberForm";
	}

	@PostMapping("/member/new")
	public String create(@Valid MemberForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return "member/createMemberForm";
		}

		memberService.join(form);
		return "redirect:/";
	}

	@ResponseBody
	@GetMapping("/member")
	public String getList(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);

		return "member/memberList";
	}

}
