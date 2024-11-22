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
		}
	}

	private void executeTestBusinessLogic(EntityManager entityManager) {
		final Member newMember = new Member();
		newMember.setName("Keo Kim");
		newMember.setPassword("password");
		// newMember.setCreatedAt(LocalDateTime.now());

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
		// member.setCreatedAt(LocalDateTime.now());
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
		member.setName("Keo Kim");
		member.setPassword("password");

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setMember(member);

		Delivery delivery = new Delivery();
		delivery.setStatus(DeliveryStatus.READY);
		// delivery.setPurchaseOrder(purchaseOrder);

		purchaseOrder.setDelivery(delivery);

		// entityManager.persist(purchaseOrder);
		entityManager.persist(delivery);

		Assertions.assertNotNull(delivery.getId());
		Delivery foundDelivery = entityManager.createQuery("SELECT d FROM Delivery d", Delivery.class).getSingleResult();
		Assertions.assertEquals(delivery, foundDelivery);
		Assertions.assertEquals(purchaseOrder.getDelivery(), foundDelivery);

		Assertions.assertNull(foundDelivery.getPurchaseOrder());
		Assertions.assertNotNull(purchaseOrder.getDelivery());

	}

	@Transactional
	@Test
	public void testCascadingPersistence() {
		Member member = new Member();
		member.setName("Keo Kim");
		member.setPassword("password");

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setMember(member);

		Delivery delivery = new Delivery();
		delivery.setStatus(DeliveryStatus.READY);
		// delivery.setPurchaseOrder(purchaseOrder);

		purchaseOrder.setDelivery(delivery);

		entityManager.persist(purchaseOrder);
		entityManager.persist(delivery);
		// entityManager.persist(member);

		// Member is initialized.
		// Member foundMember = entityManager.createQuery("SELECT m FROM Member m WHERE m.name = 'Keo Kim'", Member.class).getSingleResult();
		// Assertions.assertEquals(member, foundMember);
		Assertions.assertNotNull(member.getId());
	}

}
