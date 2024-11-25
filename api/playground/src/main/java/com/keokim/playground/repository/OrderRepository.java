package com.keokim.playground.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.keokim.playground.base.alias.PurchaseOrder;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public PurchaseOrder save(PurchaseOrder order) {
		em.persist(order);
		return order;
	}

	public Optional<PurchaseOrder> findById(long id) {
		return Optional.ofNullable(em.find(PurchaseOrder.class, id));
	}

}
