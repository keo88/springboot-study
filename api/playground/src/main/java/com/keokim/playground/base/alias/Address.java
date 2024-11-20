package com.keokim.playground.base.alias;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class Address {
	private String city;
	private String street;
	private String zipcode;

	protected Address() {

	}
}
