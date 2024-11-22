package com.keokim.playground.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Optional<Member> findById(long id) {
		return memberRepository.findById(id);
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Transactional
	public long join(Member member) {
		if (!isMemberNameTaken(member)) {
			memberRepository.save(member);
			return member.getId();
		}
		else {
			throw new IllegalStateException("The user name " + member.getName() + " is already taken");
		}
	}

	private boolean isMemberNameTaken(Member newMember) {
		return !memberRepository.findByName(newMember.getName()).isEmpty();
	}
}
