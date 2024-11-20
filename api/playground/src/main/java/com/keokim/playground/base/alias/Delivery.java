package com.keokim.playground.base.alias;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	@Embedded
	private Address address;

	@OneToOne(mappedBy = "delivery")
	private PurchaseOrder purchaseOrder;
}
