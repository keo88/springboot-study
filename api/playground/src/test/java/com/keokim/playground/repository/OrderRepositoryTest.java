package com.keokim.playground.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.keokim.playground.base.alias.Address;
import com.keokim.playground.base.alias.Delivery;
import com.keokim.playground.base.alias.DeliveryStatus;
import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.OrderItem;
import com.keokim.playground.base.alias.OrderStatus;
import com.keokim.playground.base.alias.PurchaseOrder;
import com.keokim.playground.base.alias.item.Book;
import com.keokim.playground.base.alias.param.OrderSearch;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private OrderRepository orderRepository;

	private static Member getMember(String name) {
		Member member = new Member();
		member.setName(name);
		member.setPassword("password");
		member.setAddress(new Address("Seoul", "JeongJa", "123-456"));
		return member;
	}

	private static Delivery getDelivery() {
		Delivery delivery = new Delivery();
		delivery.setAddress(new Address("Seoul", "JeongJa", "123-456"));
		delivery.setStatus(DeliveryStatus.READY);
		return delivery;
	}

	private static Book getBook(int price, int stockQuantity) {
		Book book = new Book();
		book.setName("Book");
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);

		return book;
	}

	@Test
	public void testSave() {
		Member member = getMember("Member");

		Delivery delivery = getDelivery();

		Book item = getBook(10000, 100);

		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), item.getStockQuantity());

		PurchaseOrder order = PurchaseOrder.createOrder(member, delivery, orderItem);

		orderRepository.save(order);
		entityManager.flush();

		PurchaseOrder foundOrder = entityManager.find(PurchaseOrder.class, order.getId());
		assertNotNull(foundOrder, "Order should be found.");
		assertEquals(member, order.getMember(), "Member should match.");

	}

	@Test
	public void testFindById() {
		Member member = getMember("Member");

		Delivery delivery = getDelivery();

		Book item = getBook(10000, 100);

		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), item.getStockQuantity());

		PurchaseOrder order = PurchaseOrder.createOrder(member, delivery, orderItem);

		orderRepository.save(order);
		entityManager.flush();

		PurchaseOrder foundOrder = orderRepository.findById(order.getId()).orElse(null);
		assertNotNull(foundOrder, "Order should be found.");
		assertEquals(member, order.getMember(), "Member should match.");
	}

	@Test
	public void testFindAll() {
		Member member1 = getMember("Member1");
		Member member2 = getMember("Member2");

		Delivery delivery1 = getDelivery();
		Delivery delivery2 = getDelivery();

		Book item = getBook(10000, 100);

		OrderItem orderItem1 = OrderItem.createOrderItem(item, item.getPrice(), 1);
		OrderItem orderItem2 = OrderItem.createOrderItem(item, item.getPrice(), 2);

		PurchaseOrder order1 = PurchaseOrder.createOrder(member1, delivery1, orderItem1);
		PurchaseOrder order2 = PurchaseOrder.createOrder(member2, delivery2, orderItem2);


		orderRepository.save(order1);
		orderRepository.save(order2);

		OrderSearch orderSearch1 = new OrderSearch();
		List<PurchaseOrder> result1 = orderRepository.findAll(orderSearch1);

		OrderSearch orderSearch2 = new OrderSearch();
		orderSearch2.setMemberName(member1.getName());
		List<PurchaseOrder> result2 = orderRepository.findAll(orderSearch2);

		OrderSearch orderSearch3 = new OrderSearch();
		orderSearch3.setStatus(OrderStatus.CANCEL);
		List<PurchaseOrder> result3 = orderRepository.findAll(orderSearch3);

		assertEquals(2, result1.size(), "Order should be found.");
		assertEquals(1, result2.size(), "Order should be found.");
		assertEquals(0, result3.size(), "Order should not be found.");


	}
}