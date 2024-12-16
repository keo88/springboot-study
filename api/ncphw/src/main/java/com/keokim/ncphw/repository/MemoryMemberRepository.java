package com.keokim.ncphw.repository;

import com.keokim.ncphw.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long seq = 0L;

    @Override
    public Member save(Member member) {
        seq += 1;
        member.setUserId(seq);
        store.put(seq, member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(item -> Objects.equals(item.getUsername(), name)).findAny();
    }

    @Override
    public Optional<Member> findByNameAndPassword(String name, String password) {
        return store.values().stream().filter(item -> Objects.equals(item.getUsername(), name)).filter(item -> Objects.equals(item.getPassword(), password)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
