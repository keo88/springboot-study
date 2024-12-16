package com.keokim.ncphw.service;

import com.keokim.ncphw.domain.Member;

import com.keokim.ncphw.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    class MockRepository extends MemoryMemberRepository {
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
            }
            else {
                return Optional.of(savedMember);
            }
        }

        @Override
        public Optional<Member> findById(Long id) {
            if (savedMember == null) {
                return Optional.empty();
            }
            else
                return Optional.of(savedMember);
        }

        public void clear() {
            savedMember = null;
        }
    }

    private final MockRepository repository = new MockRepository();

    private MemberService memberService = new MemberService(repository);

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
    void  duplicateJoin() {
        MockRepository mockRepository = new MockRepository();
        memberService = new MemberService(mockRepository);
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
}