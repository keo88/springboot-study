package com.keokim.playground.base.alias;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@OneToMany(mappedBy = "purchaseOrder")
	private List<OrderItem> orderItems = new ArrayList<>();

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public static PurchaseOrder createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
		PurchaseOrder order = new PurchaseOrder();
		order.setMember(member);
		order.setDelivery(delivery);
		Arrays.stream(orderItems).forEach(order::addOrderItem);
		order.setStatus(OrderStatus.ORDER);

		return order;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setPurchaseOrder(this);
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItem.setPurchaseOrder(this);
		orderItems.add(orderItem);
	}

	public void setMember(Member member) {
		this.member = member;
		member.getPurchaseOrders().add(this);
	}

	public void cancel() {
		if (delivery.getStatus() == DeliveryStatus.COMP) {
			throw new IllegalStateException("Delivery has already been cancelled");
		}

		this.setStatus(OrderStatus.CANCEL);
		orderItems.forEach(OrderItem::cancel);
	}

	public int getTotalPrice() {
		return orderItems.stream()
			.mapToInt(OrderItem::getTotalPrice)
			.sum();
	}

}
