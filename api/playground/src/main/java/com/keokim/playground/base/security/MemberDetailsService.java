package com.keokim.playground.base.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username: {}", username);
		Member member = memberRepository.findByName(username).stream()
			.findAny()
			.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

		return new MemberDetails(member);
	}
}
