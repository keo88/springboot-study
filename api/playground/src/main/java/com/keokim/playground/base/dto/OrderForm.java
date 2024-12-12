package com.keokim.playground.base.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

	@NotNull(message = "회원 아이디는 필수입니다.")
	private Long memberId;

	@NotNull(message = "상품 아이디는 필수입니다.")
	private Long itemId;

	@NotNull(message = "수량은 필수입니다.")
	@Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
	private Integer count;
}
