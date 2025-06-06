package com.keokim.ncphw.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitmqConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("223.130.140.56");
		factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("admin");
		return factory;
	}

}
