package com.keokim.ncphw.queue;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.ConnectionFactory;

@Component
public class TestProducer extends Producer {

	public TestProducer(ConnectionFactory connectionFactory) {
		super(connectionFactory, new QueueDeclaration("testQueue"));
	}

	@Override
	public void produce(String message) {
		super.produce(message);
	}

}
