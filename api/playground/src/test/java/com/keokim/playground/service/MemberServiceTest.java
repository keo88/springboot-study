package com.keokim.playground.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	@Test
	void testFindById() {
		Member member = new Member();
		member.setId(1L);
		member.setName("Test1");

		when(memberRepository.findById(1)).thenReturn(Optional.of(member));

		assertEquals(member, memberService.findById(1).orElseThrow(RuntimeException::new));
	}

	@Test
	void testFindMembers() {
		Member member1 = new Member();
		member1.setId(1L);
		member1.setName("Test1");

		Member member2 = new Member();
		member2.setId(2L);
		member2.setName("Test2");

		List<Member> members = List.of(member1, member2);

		when(memberRepository.findAll()).thenReturn(members);

		assertEquals(members, memberService.findMembers());
		verify(memberRepository).findAll();
	}

	@Test
	void testJoin() {
		String presentName = "Test1";
		String newName = "Test2";

		Member presentMember = new Member();
		presentMember.setId(1L);
		presentMember.setName(presentName);

		Member newMember = new Member();
		newMember.setId(2L);
		newMember.setName(newName);

		when(memberRepository.findByName(newName)).thenReturn(new ArrayList<>());

		memberService.join(newMember);

		verify(memberRepository, times(1)).findByName(newName);
	}

	@Test
	void testJoinWithDuplicateName() {
		String duplicatedName = "Test1";

		Member presentMember = new Member();
		presentMember.setId(1L);
		presentMember.setName(duplicatedName);

		Member newMember = new Member();
		newMember.setId(2L);
		newMember.setName(duplicatedName);

		when(memberRepository.findByName(duplicatedName)).thenReturn(List.of(presentMember));

		assertThrows(IllegalStateException.class, () -> memberService.join(newMember));

		verify(memberRepository, times(1)).findByName(duplicatedName);
	}
}