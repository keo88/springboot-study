package com.keokim.ncphw.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.repository.MemberRepository;
import com.keokim.ncphw.security.CustomUserDetails;

@Service
public class MemberService implements UserDetailsService {
	private final MemberRepository repository;
	private final PasswordEncoder passwordEncoder;

	public MemberService(MemberRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public long join(Member member) {
		validateDuplicateMember(member);
		member.setPassword(passwordEncoder.encode(member.getPassword()));

		repository.save(member);
		return member.getUserId();
	}

	public List<Member> findAll() {
		return repository.findAll();
	}

	public Member findById(long id) {
		return repository.findById(id).orElseThrow();
	}

	public Member findByUsernameAndPassword(String username, String password) {
		return repository.findByNameAndPassword(username, password).orElseThrow();
	}

	private void validateDuplicateMember(Member member) {

		repository.findByName(member.getUsername()).ifPresent(m -> {
			throw new IllegalStateException("Duplicate user name detected");
		});
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = repository.findByName(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new CustomUserDetails(member);
	}
}
