package com.keokim.playground;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.PurchaseOrder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

@SpringBootTest
public class EntityManagerFactoryTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testEntityManagerFactory() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();

		try (entityManager) {
			transaction.begin();
			executeTestBusinessLogic(entityManager);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	private void executeTestBusinessLogic(EntityManager entityManager) {
		final Member newMember = new Member();
		newMember.setName("Keo Kim");
		newMember.setPassword("password");
		newMember.setCreatedAt(LocalDateTime.now());

		entityManager.persist(newMember);
		final Member foundMember = entityManager.find(Member.class, 1L);

		final TypedQuery<Member> query = entityManager.createQuery("select m from Member m", Member.class);

		Assertions.assertEquals(newMember, foundMember);
		Assertions.assertEquals(newMember, query.getSingleResult());
	}

	@Test
	public void testEntityManagerFactory2() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();

		Member member = new Member();
		member.setName("Keo Kim");
		member.setPassword("password");
		member.setCreatedAt(LocalDateTime.now());
		PurchaseOrder order = new PurchaseOrder();
		order.setMember(member);

		try {
			transaction.begin();
			entityManager.persist(member);
			entityManager.persist(order);
			final TypedQuery<Member> query = entityManager.createQuery("select m from Member m", Member.class);
			Assertions.assertEquals(member, query.getSingleResult());
			transaction.commit();
		} finally {
			entityManager.close();
		}

	}
}
