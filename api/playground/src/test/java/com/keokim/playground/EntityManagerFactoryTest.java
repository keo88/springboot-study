package com.keokim.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keokim.playground.base.alias.Member;

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

		try {
			transaction.begin();
			executeTestBusinessLogic(entityManager);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	private void executeTestBusinessLogic(EntityManager entityManager) {
		final Member newMember = new Member();
		newMember.setName("Keo Kim");
		newMember.setPassword("password");

		entityManager.persist(newMember);
		final Member foundMember = entityManager.find(Member.class, 1L);

		final TypedQuery<Member> query = entityManager.createQuery("select m from Member m", Member.class);

		Assertions.assertEquals(newMember, foundMember);
		Assertions.assertEquals(newMember, query.getSingleResult());
	}
}
