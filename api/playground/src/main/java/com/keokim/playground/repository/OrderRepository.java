package com.keokim.playground.repository;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.keokim.playground.base.alias.OrderStatus;
import com.keokim.playground.base.alias.PurchaseOrder;
import com.keokim.playground.base.alias.QMember;
import com.keokim.playground.base.alias.QPurchaseOrder;
import com.keokim.playground.base.alias.param.OrderSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private static final QPurchaseOrder order = QPurchaseOrder.purchaseOrder;
	private static final QMember member = QMember.member;

	private final EntityManager em;

	public PurchaseOrder save(PurchaseOrder order) {
		em.persist(order);
		return order;
	}

	public Optional<PurchaseOrder> findById(long id) {
		return Optional.ofNullable(em.find(PurchaseOrder.class, id));
	}

	public List<PurchaseOrder> findAll(OrderSearch orderSearch) {

		JPAQuery<PurchaseOrder> query = new JPAQuery<>(em);

		return query
			.select(order)
			.from(order)
			.join(order.member, member)
			.where(eqMemberName(orderSearch.getMemberName()), eqStatus(orderSearch.getStatus()))
			.limit(1000)
			.fetch();
	}

	private BooleanExpression eqMemberName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}

		return member.name.eq(name);
	}

	private BooleanExpression eqStatus(OrderStatus status) {
		if (status == null) {
			return null;
		}

		return order.status.eq(status);
	}

}
