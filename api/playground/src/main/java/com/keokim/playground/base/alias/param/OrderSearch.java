package com.keokim.playground.base.alias.param;

import com.keokim.playground.base.alias.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

	private String memberName;
	private OrderStatus status;
}
