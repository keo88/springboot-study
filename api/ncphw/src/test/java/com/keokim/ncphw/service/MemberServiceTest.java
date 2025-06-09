package com.keokim.ncphw.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.repository.MemoryMemberRepository;

class MemberServiceTest {

	private final MockRepository repository = new MockRepository();
	private final MemberService memberService = new MemberService(repository, new BCryptPasswordEncoder());

	@BeforeEach
	void beforeEach() {
		repository.clear();
	}

	@Test
	void join() {

		Member member1 = new Member();
		member1.setUsername("member1");

		memberService.join(member1);

		assertEquals(memberService.findById(member1.getUserId()), member1);
	}

	@Test
	void duplicateJoin() {
		Member member1 = new Member();
		member1.setUsername("member1");

		Member member2 = new Member();
		member2.setUsername("member1");

		memberService.join(member1);

		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
	}

	@Test
	void findAll() {
	}

	@Test
	void findById() {
	}

	static class MockRepository extends MemoryMemberRepository {
		private Member savedMember;

		@Override
		public Member save(Member member) {
			savedMember = member;
			savedMember.setUserId(1L);
			return member;
		}

		@Override
		public Optional<Member> findByName(String name) {
			if (savedMember == null) {
				return Optional.empty();
			} else {
				return Optional.of(savedMember);
			}
		}

		@Override
		public Optional<Member> findById(Long id) {
			if (savedMember == null) {
				return Optional.empty();
			} else
				return Optional.of(savedMember);
		}

		public void clear() {
			savedMember = null;
		}
	}
}