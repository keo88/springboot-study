package com.keokim.playground.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.keokim.playground.base.alias.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Optional<Member> findById(long id) {
		return Optional.ofNullable(em.find(Member.class, id));
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name)
			.getResultList();
	}

}
