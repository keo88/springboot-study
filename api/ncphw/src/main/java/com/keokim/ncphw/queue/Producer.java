package com.keokim.ncphw.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class Producer {

	private final ConnectionFactory connectionFactory;
	private final QueueDeclaration queueDeclaration;

	public void produce(String message) {
		try (Connection connection = connectionFactory.newConnection();
			 Channel channel = connection.createChannel()) {
			channel.queueDeclare(queueDeclaration.queueName(), false, false, false, null);
			channel.basicPublish("", queueDeclaration.queueName(), null, message.getBytes());
			log.info("Producer: message sent: {}", message);
		} catch (Exception e) {
			log.error("Producer: Failed to send message: {}", e.getMessage(), e);
		}
	}
}
