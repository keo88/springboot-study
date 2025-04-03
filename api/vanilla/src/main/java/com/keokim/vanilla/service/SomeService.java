package com.keokim.vanilla.service;

import org.springframework.stereotype.Service;

import com.keokim.vanilla.config.SomeClass;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SomeService {

	private final SomeClass someClass;

	public SomeService(SomeClass someClass1) {
		this.someClass = someClass1;
	}

	@PostConstruct
	public void init() {
		log.info("SomeClass: {}", someClass.getName());
	}
}
