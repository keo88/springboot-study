package com.keokim.vanilla.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SomeConfig {

	@Bean
	public SomeClass someClass() {
		log.info("init someClass 1");
		return new SomeClass("SomeClass 1");
	}

	@Bean
	@Primary
	@ConditionalOnExpression("!'${spring.profiles.active}'.contains('local')")
	public SomeClass someClass2() {
		log.info("init someClass 2");
		return new SomeClass("SomeClass 2");
	}
}
