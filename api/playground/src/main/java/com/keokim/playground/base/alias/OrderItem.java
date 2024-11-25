package com.keokim.playground.base.alias;

import java.time.LocalDateTime;

import com.keokim.playground.base.alias.item.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "order_item")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;

	@Column
	private Integer orderPrice;

	@Column
	private Integer count;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);

		return orderItem;
	}

	public void cancel() {
		item.addStock(count);
	}

	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

}
