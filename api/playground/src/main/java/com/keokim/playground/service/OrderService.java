package com.keokim.playground.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keokim.playground.base.alias.Delivery;
import com.keokim.playground.base.alias.DeliveryStatus;
import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.OrderItem;
import com.keokim.playground.base.alias.PurchaseOrder;
import com.keokim.playground.base.alias.item.Item;
import com.keokim.playground.base.alias.param.OrderSearch;
import com.keokim.playground.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberService memberService;
	private final ItemService itemService;

	@Transactional
	public PurchaseOrder createOrder(long memberId, long itemId, int count) {
		Member member = memberService.findById(memberId).orElseThrow(RuntimeException::new);
		Item item = itemService.findById(itemId).orElseThrow(RuntimeException::new);

		Delivery delivery = new Delivery();
		delivery.setStatus(DeliveryStatus.READY);
		delivery.setAddress(member.getAddress());

		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		PurchaseOrder order = PurchaseOrder.createOrder(member, delivery, orderItem);

		orderRepository.save(order);

		return order;
	}

	@Transactional
	public void cancelOrder(long orderId) {
		PurchaseOrder cancelOrder = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
		cancelOrder.cancel();
	}

	public List<PurchaseOrder> findAll(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}
}
