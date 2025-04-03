package com.keokim.vanilla.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SomeClass {
	private final String name;

	public SomeClass(String name) {
		this.name = name;
	}
}
