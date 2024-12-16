package com.keokim.ncphw.repository;

import com.keokim.ncphw.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcMemberRepositoryTest {
    private JdbcMemberRepository jdbcMemberRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcMemberRepository = new JdbcMemberRepository(jdbcTemplate);
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setUsername("keo.test");
        member.setPassword("1234");

        // when
        Member newMember = jdbcMemberRepository.save(member);
        Optional<Member> foundMember = jdbcMemberRepository.findByName("keo.test");

        // then
        assertTrue(foundMember.isPresent());
        assertEquals(newMember.getUserId(), foundMember.get().getUserId());
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByNameAndPassword() {
    }

    @Test
    void findAll() {
    }
}