package com.keokim.ncphw.service;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public long join(Member member) {
        validateDuplicateMember(member);

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
}
