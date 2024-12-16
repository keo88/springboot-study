package com.keokim.ncphw.controller;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String getMember(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String creatLoginForm(Model model) {
        model.addAttribute("actionUrl", "/members/login");
        model.addAttribute("buttonText", "로그인");
        model.addAttribute("altUrl", "/members/new");
        model.addAttribute("altText", "회원가입");
        return "members/createMemberForm";
    }

    @PostMapping("/members/login")
    public String login(MemberForm memberForm, HttpServletRequest req) {
        try {
            Member member = memberService.findByUsernameAndPassword(memberForm.getUsername(), memberForm.getPassword());
            req.getSession().setAttribute("user", member);
        } catch (NoSuchElementException noSuchElementException) {
            return "redirect:/members/login";
        }

        return "redirect:/";
    }

    @GetMapping("/members/new")
    public String creatSignUpForm(Model model) {
        model.addAttribute("actionUrl", "/members/new");
        model.addAttribute("buttonText", "등록");
        model.addAttribute("altUrl", "/members/login");
        model.addAttribute("altText", "로그인");
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm, HttpServletRequest req) {
        Member member = new Member();
        member.setUsername(memberForm.getUsername());
        member.setPassword(memberForm.getPassword());

        memberService.join(member);

        req.getSession().setAttribute("user", member);
        return "redirect:/";
    }

    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/members/login";
    }
}
