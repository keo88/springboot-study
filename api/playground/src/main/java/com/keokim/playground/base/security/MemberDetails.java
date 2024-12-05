package com.keokim.playground.base.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.keokim.playground.base.alias.Member;

import lombok.Getter;

@Getter
public class MemberDetails implements UserDetails {
	private final Member member;
	private final String name;
	private final String password;

	public MemberDetails(Member member) {
		this.member = member;
		this.name = member.getName();
		this.password = member.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("MEMBER"));
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getName();
	}
}
