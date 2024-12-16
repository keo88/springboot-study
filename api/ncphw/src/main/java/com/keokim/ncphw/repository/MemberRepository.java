package com.keokim.ncphw.repository;

import com.keokim.ncphw.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    Optional<Member> findByNameAndPassword(String name, String password);

    List<Member> findAll();
}
