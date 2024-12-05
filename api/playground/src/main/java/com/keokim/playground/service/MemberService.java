package com.keokim.playground.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.dto.MemberForm;
import com.keokim.playground.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Optional<Member> findById(long id) {
		return memberRepository.findById(id);
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Transactional
	public Optional<Long> join(MemberForm memberForm) {
		Member member = Member.of(memberForm, passwordEncoder);

		if (!isMemberNameTaken(member)) {
			memberRepository.save(member);
			return Optional.ofNullable(member.getId());
		}
		else {
			throw new IllegalStateException("The user name " + member.getName() + " is already taken");
		}
	}

	private boolean isMemberNameTaken(Member newMember) {
		return !memberRepository.findByName(newMember.getName()).isEmpty();
	}
}
