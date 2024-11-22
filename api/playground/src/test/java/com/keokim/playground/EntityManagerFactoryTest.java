package com.keokim.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.keokim.playground.base.alias.Delivery;
import com.keokim.playground.base.alias.DeliveryStatus;
import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.PurchaseOrder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@SpringBootTest
public class EntityManagerFactoryTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private EntityManager entityManager;

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
			throw e;
		}
	}

	private void executeTestBusinessLogic(EntityManager entityManager) {
		final Member newMember = new Member();
		newMember.setName("Test1");
		newMember.setPassword("password");

		entityManager.persist(newMember);

		final TypedQuery<Member> query = entityManager.createQuery("select m from Member m where name='Test1'", Member.class);

		Assertions.assertEquals(newMember, query.getSingleResult());
	}

	@Test
	public void testEntityManagerFactory2() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();

		Member member = new Member();
		member.setName("Test2");
		member.setPassword("password");
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

	@Transactional
	@Commit
	@Test
	public void testBiDirectionalEntityMapping() {
		Member member = new Member();
		member.setName("Test3");
		member.setPassword("password");

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setMember(member);

		Delivery delivery = new Delivery();
		delivery.setStatus(DeliveryStatus.READY);

		purchaseOrder.setDelivery(delivery);

		entityManager.persist(purchaseOrder);

		Assertions.assertNotNull(delivery.getId());
		TypedQuery<Delivery> query = entityManager.createQuery("SELECT d FROM Delivery d WHERE d.purchaseOrder = ?1", Delivery.class);
		query.setParameter(1, purchaseOrder);
		Delivery foundDelivery = query.getSingleResult();

		Assertions.assertEquals(delivery, foundDelivery);
		Assertions.assertEquals(purchaseOrder.getDelivery(), foundDelivery);
	}

	@Transactional
	@Test
	public void testCascadingPersistence() {
		Member member = new Member();
		member.setName("Test4");
		member.setPassword("password");

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setMember(member);

		Delivery delivery = new Delivery();
		delivery.setStatus(DeliveryStatus.READY);

		purchaseOrder.setDelivery(delivery);

		entityManager.persist(purchaseOrder);
		entityManager.persist(delivery);

		Assertions.assertNotNull(member.getId());
	}

}
