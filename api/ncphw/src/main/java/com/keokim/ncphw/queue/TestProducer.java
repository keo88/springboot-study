package com.keokim.ncphw.queue;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.ConnectionFactory;

import lombok.RequiredArgsConstructor;

@Component
public class TestProducer extends Producer {

	public TestProducer(ConnectionFactory connectionFactory) {
		super(connectionFactory, new QueueDeclaration("testQueue"));
	}

	public void sendTestMessage(String message) {
		produce(message);
	}

}
